package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.api.authentication.wx.WxCustomerContext;
import com.fongwell.satchi.crm.core.credit.domain.aggregate.CustomerCreditRecord;
import com.fongwell.satchi.crm.core.credit.domain.value.CreditType;
import com.fongwell.satchi.crm.core.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by roman on 18-3-23.
 */
@RestController
@RequestMapping("/api/wx/credit")
public class WxCustomerCreditRecordApi {

    @Autowired
    private CreditService creditService;

    /**
     * 保存客户每次消费的记录,并且更新客户的总积分
     * @param customerCreditRecord
     */
    @PostMapping("/consumptionCreditRecord")
    public void consumptionCreditRecord(@RequestBody CustomerCreditRecord customerCreditRecord){

        long customerId = WxCustomerContext.getUser().getCustomerId();
        creditService.saveCustomerConsumptionRecord(customerCreditRecord, customerId);
    }

    /**
     * 保存客户每次获取积分记录,并更新客户的总积分
     * @param customerCreditRecord
     * @return
     */
    @PostMapping("/obtainCreditRecord")
    public Integer obtainCreditRecord(@RequestBody CustomerCreditRecord customerCreditRecord){
        long customerId = WxCustomerContext.getUser().getCustomerId();
        Integer obtainCredit = creditService.saveCustomerObtainCreditRecord(customerCreditRecord, customerId);
        return obtainCredit;
    }

    /**
     * 客户兑换的钱
     * @param customerCreditRecord
     * @return
     */
    @PostMapping("/creditExchangeMoney")
    public Map creditExchangeMoney(@RequestBody CustomerCreditRecord customerCreditRecord){
        long customerId = WxCustomerContext.getUser().getCustomerId();
        Map map = creditService.creditExchangeMoney(customerCreditRecord,customerId);
        return map;
    }




}
