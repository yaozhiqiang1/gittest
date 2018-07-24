package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.satchi.crm.api.Payload;

import com.fongwell.satchi.crm.api.authentication.wx.WxCustomerContext;
import com.fongwell.satchi.crm.core.credit.domain.aggregate.CustomerCredit;

import com.fongwell.satchi.crm.core.credit.domain.entity.CreditSourceConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.value.CreditType;
import com.fongwell.satchi.crm.core.credit.query.mapper.CreditConfigurationQueryMapper;
import com.fongwell.satchi.crm.core.credit.query.mapper.CreditQueryMapper;
import com.fongwell.satchi.crm.core.credit.query.mapper.CreditSourceConfigurationMapper;
import com.fongwell.satchi.crm.core.credit.query.mapper.CreditUpdateMapper;
import com.fongwell.satchi.crm.core.credit.service.CreditService;

import com.fongwell.satchi.crm.core.customer.dto.CustomerAuthenticationDetails;
import com.fongwell.satchi.crm.core.customer.query.mapper.CustomerQueryMapper;

import com.fongwell.satchi.crm.core.order.domain.entity.OrderItem;
import com.fongwell.satchi.crm.core.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * Created by docker on 5/23/18.
 */
@RestController
@RequestMapping("/api/wx/credit")
public class WxCreditApi {

    @Autowired
    private CreditQueryMapper creditQueryMapper;

    @Autowired
    private CreditService creditService;


    @Autowired
    private CreditSourceConfigurationMapper creditSourceConfigurationMapper;

    @Autowired
    private CreditConfigurationQueryMapper creditConfigurationQueryMapper;

    @Autowired
    private CustomerQueryMapper customerQueryMapper;

    @Autowired
    private CreditUpdateMapper creditUpdateMapper;

    @Autowired
    private ProductService productService;


    /**
     *  微信端积分查询
     * @param from
     * @param size
     * @return
     */
    @GetMapping("/record")
    public Payload records(@RequestParam(required = false, defaultValue = "0") int from,
                           @RequestParam(required = false, defaultValue = "20") int size) {
        return new Payload(creditQueryMapper.queryCreditRecords(WxCustomerContext.getUser().getCustomerId(), CreditType.reward.name(), from, size));
    }

    /**
     * 客户查询积分总数,积分时间到期就把客户积分清零
     */
    @GetMapping("/customerCreditTotal")
    public Payload customerCreditTotal(){
        long customerId = WxCustomerContext.getUser().getCustomerId();
        Long creditConfigurationId = -1L;
        Map expiration = creditConfigurationQueryMapper.queryCreditConfigurationExpiration(creditConfigurationId);
        Date configDate = (Date) expiration.get("expiration");
        Date currentDate = new Date();
        if (configDate.compareTo(currentDate) < 0){
            Integer credits = 0;
            creditUpdateMapper.creditUpdate(customerId,credits);
        }
        Integer credits = creditQueryMapper.customerCreditTotal(customerId);
        return new Payload(credits);
    }

    /**
     * 保存客户注册积分
     * @param mobile
     */
    @GetMapping("/registerCredit")
    public Payload registerCreat(@RequestParam String mobile){
        Boolean creditConfigEnabled = creditQueryMapper.queryEnabled();
        if (creditConfigEnabled) {
            String type = "REGISTER";
            CreditSourceConfiguration creditSourceConfiguration = creditSourceConfigurationMapper.queryByType(type);
            Boolean enabled = creditSourceConfiguration.getEnabled();

            Long customerId = customerQueryMapper.queryByMobile(mobile);
            CustomerCredit queryCustomerCredit = creditService.queryById(customerId);
            if (queryCustomerCredit == null) {
                Integer credit = 0;
                if (enabled) {
                    credit = creditSourceConfiguration.getCredit();
                }
                CustomerCredit customerCredit = new CustomerCredit(customerId, credit);
                customerCredit.setCustomerId(customerId);
                creditService.creatCustomerCredit(customerCredit);
                return new Payload(true);
            }
        }

       return new Payload(false);
    }

    /**
     * 客户签到是否超过一天
     * @return
     */
    @GetMapping("/checkinDate")
    public Payload checkinDate(){
        CustomerAuthenticationDetails user = WxCustomerContext.getUser();
        long customerId = user.getCustomerId();
        return new Payload(creditService.queryCheckindate(customerId));
    }

    /**
     * 签到积分
     * @return
     */
    @GetMapping("/checkinCredit")
    public Payload checkinCredit() {
        Boolean creditConfigEnabled = creditQueryMapper.queryEnabled();
        if (creditConfigEnabled) {
            String type = "CHECKIN";
            CreditSourceConfiguration creditSourceConfiguration = creditSourceConfigurationMapper.queryByType(type);
            Boolean enabled = creditSourceConfiguration.getEnabled();
            if (enabled) {
                CustomerAuthenticationDetails user = WxCustomerContext.getUser();
                long customerId = user.getCustomerId();
                CustomerCredit customerCredit = creditService.queryById(customerId);

                if (customerCredit != null) {
                    customerCredit.setCredits(customerCredit.getCredits() + creditSourceConfiguration.getCredit());
                    customerCredit.setCheckinDate(new Date());
                    creditService.updateCustomerCredit(customerCredit);


                    return new Payload(true);
                }
            }
        }
        return new Payload(false);
    }

    /**
     * 限制礼品购买
     * @param orderItem
     * @return
     */
    @PostMapping("/restrictionamountGift")
    public com.fongwell.base.rest.Payload restrictionamountGift(@RequestBody OrderItem orderItem){
        long customerId = WxCustomerContext.getUser().getCustomerId();
        return productService.restrictionamountGift(orderItem,customerId);
    }


}
