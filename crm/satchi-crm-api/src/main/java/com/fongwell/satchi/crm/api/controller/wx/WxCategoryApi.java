package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.base.rest.Payload;
import com.fongwell.satchi.crm.core.category.query.AdminCategoryQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by roman on 18-4-8.
 */
@RestController
@RequestMapping("/api/wx/category")
public class WxCategoryApi {

    @Autowired
    private AdminCategoryQueryMapper categoryQueryMapper;

    @GetMapping
    public Payload findAll(){
        return new Payload(categoryQueryMapper.generalList());
    }
}
