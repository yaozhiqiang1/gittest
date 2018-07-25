package com.fongwell.satchi.crm.api.controller.admin;

import java.util.Collection;
import java.util.Collections;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fongwell.base.rest.Payload;
import com.fongwell.base.validate.Validate;
import com.fongwell.satchi.crm.core.category.dto.CategoryData;
import com.fongwell.satchi.crm.core.category.query.AdminCategoryQueryMapper;
import com.fongwell.satchi.crm.core.category.service.CategoryService;
import com.fongwell.satchi.crm.core.common.query.RequestData;

/**
 * Created by roman on 18-4-8.
 */
@RestController
@RequestMapping("/api/admin/category")
public class CategoryApi {

    @Resource(name = "categoryService")
    private CategoryService categoryService;
    @Autowired
    private AdminCategoryQueryMapper adminCategoryQueryMapper;

    @GetMapping
    public Payload findAll(@RequestParam(required = false) String state,
                           @RequestParam(required = false, defaultValue = "0") int from,
                           @RequestParam(required = false, defaultValue = "20") int size) {
        RequestData request = new RequestData(null, null, null, StringUtils.isBlank(state) ? null : Collections.singleton(state), from, size);
        return new Payload(adminCategoryQueryMapper.findAll(request));
    }

    @GetMapping("/count")
    public Payload count(@RequestParam(required = false) String state) {
        RequestData request = new RequestData(null, null, null, StringUtils.isBlank(state) ? null : Collections.singleton(state), 0, 0);
        return new Payload(adminCategoryQueryMapper.count(request));
    }

    @GetMapping("/{id}")
    public Payload get(@PathVariable Long id) {
        return new Payload(adminCategoryQueryMapper.get(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Validate
    public void onCreate(@RequestBody @Valid CategoryData data) {
        categoryService.onCreate(data);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Validate
    public void onUpdate(@PathVariable Long id, @RequestBody @Valid CategoryData data) {
        categoryService.onUpdate(id, data);
    }

    @PostMapping("/sort/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void sort(@PathVariable Long id, @RequestParam(required = false, defaultValue = "0") Integer orderNumber) {
        categoryService.onSort(id, orderNumber);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Payload delete(@RequestParam Collection<Long> ids) {
    	System.out.println("---------------------:CategoryApi.delete");
        return categoryService.onDelete(ids);
    }

}
