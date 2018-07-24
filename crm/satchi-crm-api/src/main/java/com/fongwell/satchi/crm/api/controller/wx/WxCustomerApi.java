package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.base.validate.Validate;
import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.api.authentication.wx.WxCustomerContext;
import com.fongwell.satchi.crm.core.common.error.DataNotFoundException;
import com.fongwell.satchi.crm.core.customer.domain.aggregate.Customer;
import com.fongwell.satchi.crm.core.customer.dto.CustomerAuthenticationDetails;
import com.fongwell.satchi.crm.core.customer.dto.CustomerRegisterRequest;
import com.fongwell.satchi.crm.core.customer.query.dto.CustomerDetails;
import com.fongwell.satchi.crm.core.customer.query.mapper.WxCustomerQueryMapper;
import com.fongwell.satchi.crm.core.customer.query.repository.CustomerQueryRepository;
import com.fongwell.satchi.crm.core.customer.service.CustomerRegistrationService;
import com.fongwell.satchi.crm.wx.user.binding.WxUserBindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by docker on 2/25/18.
 */
@RestController
@RequestMapping("/api/wx")
public class WxCustomerApi {


    @Autowired
    private CustomerRegistrationService customerRegistrationService;

    @Autowired
    private WxCustomerQueryMapper wxCustomerQueryMapper;

    @Resource(name = "wxUserBindingService")
    private WxUserBindingService wxUserBindingService;


    @Resource(name = "customerQueryRepository")
    private CustomerQueryRepository customerQueryRepository;


    @GetMapping("/profile")
    public Payload profile() {
        return new Payload(wxCustomerQueryMapper.queryProfile(WxCustomerContext.getUser().getCustomerId()));
    }
    
    //获取用户登录信息
    @GetMapping("/info")
    public Payload info() {

        Map map = new HashMap(1, 2f);


        CustomerAuthenticationDetails user = WxCustomerContext.getUser();
        map.put("loggedin", user != null);
        return new Payload(map);

    }
    
    //登录
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestParam String mobile) {	
        CustomerDetails customer = customerQueryRepository.queryCustomerDetails(mobile);
        if (customer == null) {
            throw new DataNotFoundException("customer not found: " + mobile);

        } else {
            wxUserBindingService.bind(WxCustomerContext.getWxId(), customer.getId());
        }
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void unbind() {	
        if (WxCustomerContext.getWxId() != null) {
            wxUserBindingService.unbind(WxCustomerContext.getWxId());
        }

    }
    
    //注册
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    @Validate
    public void register(@Valid @RequestBody CustomerRegisterRequest request, BindingResult result) {

        Customer newCustomer = customerRegistrationService.register(request);
        if (WxCustomerContext.getWxId() != null) {
            wxUserBindingService.bind(WxCustomerContext.getWxId(), newCustomer.getId());
        }


    }

}
