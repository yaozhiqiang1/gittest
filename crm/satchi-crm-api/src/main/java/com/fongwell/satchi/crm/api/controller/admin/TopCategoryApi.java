package com.fongwell.satchi.crm.api.controller.admin;

import com.fongwell.base.rest.Payload;
import com.fongwell.base.validate.Validate;
import com.fongwell.satchi.crm.core.category.dto.TopCategoryData;
import com.fongwell.satchi.crm.core.category.query.AdminTopCategoryQueryMapper;
import com.fongwell.satchi.crm.core.category.service.TopCategoryService;
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
@RequestMapping("/api/admin/topCategory")
public class TopCategoryApi {

    @Resource(name = "topCategoryService")
    private TopCategoryService topCategoryService;

    @Autowired
    private AdminTopCategoryQueryMapper topCategoryQueryMapper;

    @GetMapping
    public Payload findAll(@RequestParam(required = false) String state,
                           @RequestParam(required = false,defaultValue = "0") int from ,
                           @RequestParam(required = false,defaultValue = "20") int size){
        RequestData request = new RequestData(null,null,null, StringUtils.isBlank(state)?null: Collections.singleton(state),from,size);
        return new Payload(topCategoryQueryMapper.findAll(request));
    }

    @GetMapping("/count")
    public Payload count(@RequestParam(required = false) String state){
        RequestData request = new RequestData(null,null,null, StringUtils.isBlank(state)?null: Collections.singleton(state),0,0);
        return new Payload(topCategoryQueryMapper.count(request));
    }

    @GetMapping("/{id}")
    public Payload get(@PathVariable Long id){
        return new Payload(topCategoryQueryMapper.get(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Validate
    public void onCreate(@RequestBody @Valid TopCategoryData data){
        topCategoryService.onCreate(data);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Validate
    public void onUpdate(@PathVariable Long id,@RequestBody @Valid TopCategoryData data){
        topCategoryService.onUpdate(id,data);
    }

    @PostMapping("/sort/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void sort(@PathVariable Long id,@RequestParam(required = false,defaultValue = "0") Integer orderNumber){
        topCategoryService.onSort(id,orderNumber);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam Collection<Long> ids){
        topCategoryService.onDelete(ids);
    }

    @PostMapping("/disable")
    @ResponseStatus(HttpStatus.OK)
    public void disable(@RequestParam Collection<Long> ids){
        topCategoryService.onDisable(ids);
    }

    @PostMapping("/enable")
    public Payload enable(@RequestParam Collection<Long> ids){
        String state = topCategoryService.onEnable(ids);
        return new Payload(state);
    }
}
