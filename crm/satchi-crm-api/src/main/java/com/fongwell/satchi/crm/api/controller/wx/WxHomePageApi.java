package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.base.rest.Payload;
import com.fongwell.satchi.crm.core.common.query.RequestData;
import com.fongwell.satchi.crm.core.homePage.mobileHomePage.query.AdminMobileHomePageMapper;
import com.fongwell.satchi.crm.core.homePage.mobileHomePage.query.WxMobileHomePageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;

/**
 * Created by roman on 18-4-13.
 */
@RestController
@RequestMapping("/api/wx/homePage")
public class WxHomePageApi {

    @Autowired
    private WxMobileHomePageMapper wxMobileHomePageMapper;

    @GetMapping
    public Payload findAll(){
        Collection<Map> all = wxMobileHomePageMapper.findAll(new RequestData(0, 0));

        return new Payload(all);
    }
}
