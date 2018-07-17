package com.fongwell.satchi.crm.api.controller.admin;

import com.fongwell.base.validate.Validate;
import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.core.credit.domain.aggregate.CreditConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.entity.CreditSourceConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.value.CreditSource;
import com.fongwell.satchi.crm.core.credit.dto.CreditConfigurationDto;
import com.fongwell.satchi.crm.core.credit.query.mapper.CreditConfigurationQueryMapper;
import com.fongwell.satchi.crm.core.credit.query.mapper.CreditSourceConfigurationMapper;
import com.fongwell.satchi.crm.core.credit.service.CreditConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by docker on 5/7/18.
 */
@RestController
@RequestMapping("/api/admin/credit")
public class CreditApi {

    @Resource(name = "creditConfigurationService")
    private CreditConfigurationService creditConfigurationService;

    @Autowired
    private CreditConfigurationQueryMapper creditConfigurationQueryMapper;

    @Autowired
    private CreditSourceConfigurationMapper creditSourceConfigurationMapper;


    /**
     * 积分设置
     * @param data
     * @param result
     */
    @PostMapping("")
    @Validate
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestBody @Valid CreditConfigurationDto data, BindingResult result) {
        creditConfigurationService.saveConfiguration(data);
    }

    /**
     * 查询积分
     * @return
     */
    @GetMapping("")
    public Payload config() {
        return new Payload(creditConfigurationQueryMapper.queryConfiguration(CreditConfiguration.DEFAULT_ID));
    }

 /*   @GetMapping("/test")
    public void test(){
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        CreditSourceConfiguration creditSourceConfiguration = new CreditSourceConfiguration();
        creditSourceConfiguration.setType("asdf");
        creditSourceConfiguration.setCredit(10);
        creditSourceConfiguration.setEnabled(true);
        creditSourceConfiguration.setParentId(-1);

        creditSourceConfigurationMapper.saveCreditSourceConfiguration(creditSourceConfiguration);

    }*/

}
