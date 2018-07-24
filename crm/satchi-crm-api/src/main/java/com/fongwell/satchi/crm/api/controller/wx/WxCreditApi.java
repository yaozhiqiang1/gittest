package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.api.authentication.wx.WxCustomerContext;
import com.fongwell.satchi.crm.core.credit.domain.value.CreditType;
import com.fongwell.satchi.crm.core.credit.query.mapper.CreditQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by docker on 5/23/18.
 */
@RestController
@RequestMapping("/api/wx/credit")
public class WxCreditApi {

    @Autowired
    private CreditQueryMapper creditQueryMapper;

    @GetMapping("/record")
    public Payload records(@RequestParam(required = false, defaultValue = "0") int from,
                           @RequestParam(required = false, defaultValue = "20") int size) {
        return new Payload(creditQueryMapper.queryCreditRecords(WxCustomerContext.getUser().getCustomerId(), CreditType.reward.name(), from, size));
    }
}
