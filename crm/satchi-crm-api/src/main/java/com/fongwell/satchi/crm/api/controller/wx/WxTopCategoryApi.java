package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.base.rest.Payload;
import com.fongwell.base.validate.Validate;
import com.fongwell.satchi.crm.core.category.dto.TopCategoryData;
import com.fongwell.satchi.crm.core.category.query.AdminTopCategoryQueryMapper;
import com.fongwell.satchi.crm.core.category.service.TopCategoryService;
import com.fongwell.satchi.crm.core.common.State;
import com.fongwell.satchi.crm.core.common.query.RequestData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by roman on 18-4-16.
 */
@RestController
@RequestMapping("/api/wx/topCategory")
public class WxTopCategoryApi {

    @Autowired
    private AdminTopCategoryQueryMapper topCategoryQueryMapper;

    @GetMapping
    public Payload findAll(
                           @RequestParam(required = false,defaultValue = "0") int from ,
                           @RequestParam(required = false,defaultValue = "20") int size){
        RequestData request = new RequestData(null,null,null, Collections.singleton(State.enable.name()),from,size);
        return new Payload(topCategoryQueryMapper.findAll(request));
    }

    @GetMapping("/count")
    public Payload count(){
        RequestData request = new RequestData(null,null,null,  Collections.singleton(State.enable.name()),0,0);
        return new Payload(topCategoryQueryMapper.count(request));
    }

    @GetMapping("/{id}")
    public Payload get(@PathVariable Long id){
        return new Payload(topCategoryQueryMapper.get(id));
    }

}
