package com.fongwell.satchi.crm.api.controller.admin;

import com.fongwell.base.rest.Payload;
import com.fongwell.base.validate.Validate;
import com.fongwell.satchi.crm.core.brandNews.domain.aggregate.BrandNews;
import com.fongwell.satchi.crm.core.brandNews.dto.BrandNewsData;
import com.fongwell.satchi.crm.core.brandNews.query.AdminBrandNewsQueryMapper;
import com.fongwell.satchi.crm.core.brandNews.service.BrandNewsService;
import com.fongwell.satchi.crm.core.common.query.RequestData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by roman on 18-3-24.
 */
@RestController
@RequestMapping("/api/admin/brand/news")
public class BrandNewsApi {

    @Resource(name = "brandNewsService")
    private BrandNewsService newsService;

    @Autowired
    private AdminBrandNewsQueryMapper adminBrandNewsQueryMapper;

    @GetMapping("")
    public Payload findAll(@RequestParam(required = false) String state,
                           @RequestParam(required = false,defaultValue = "0") int from ,
                           @RequestParam(required = false,defaultValue = "20") int size){
        RequestData request = new RequestData(null,null,null, StringUtils.isBlank(state)?null:Collections.singleton(state),from,size);
        return new Payload(adminBrandNewsQueryMapper.findAll(request));
    }

    @GetMapping("/count")
    public Payload count(@RequestParam(required = false) String state){
        RequestData request = new RequestData(null,null,null, StringUtils.isBlank(state)?null:Collections.singleton(state),0,0);
        return new Payload(adminBrandNewsQueryMapper.count(request));
    }

    @PostMapping("/sort/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void sort(@PathVariable Long id,@RequestParam(required = false,defaultValue = "0") Integer orderNumber){
        newsService.onSort(id,orderNumber);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Validate
    public void create(@RequestBody@Valid BrandNewsData data){
        newsService.onCreate(data);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Validate
    public void update(@PathVariable Long id,@RequestBody@Valid BrandNewsData data){
        newsService.onUpdate(id,data);
    }

    @PostMapping("/enable")
    @ResponseStatus(HttpStatus.OK)
    public void enable(@RequestParam List<Long> ids){
        newsService.onEnable(ids);
    }

    @PostMapping("/disable")
    @ResponseStatus(HttpStatus.OK)
    public void disable(@RequestParam List<Long> ids){
        newsService.onDisable(ids);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        newsService.onDelete(id);
    }

    @GetMapping("/{id}")
    public Payload get(@PathVariable Long id){
        return new Payload(adminBrandNewsQueryMapper.get(id));
    }
}
