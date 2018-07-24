package com.fongwell.satchi.crm.api.controller.admin;

import com.fongwell.base.rest.Payload;
import com.fongwell.satchi.crm.core.common.query.RequestData;
import com.fongwell.satchi.crm.core.homePage.mobileHomePage.domain.dto.MobileHomePageData;
import com.fongwell.satchi.crm.core.homePage.mobileHomePage.query.AdminMobileHomePageMapper;
import com.fongwell.satchi.crm.core.homePage.mobileHomePage.service.MobileHomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by roman on 18-4-13.
 */
@RestController
@RequestMapping("/api/admin/mobileHomePage")
public class MobileHomePageApi {

    @Resource(name = "mobileHomePageService")
    private MobileHomePageService mobileHomePageService;

    @Autowired
    private AdminMobileHomePageMapper adminMobileHomePageMapper;

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody MobileHomePageData data){
        mobileHomePageService.onCreate(data);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id ,@RequestBody MobileHomePageData data){
        mobileHomePageService.onUpdate(id,data);
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam Collection<Long> ids){
        mobileHomePageService.onDelete(ids);
    }

    @PostMapping("/sort/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void sort(@PathVariable Long id ,@RequestParam int orderNumber){
        mobileHomePageService.onSort(id,orderNumber);
    }

    @PostMapping("/disable")
    @ResponseStatus(HttpStatus.OK)
    public void disable(@RequestParam Collection<Long> ids){
        mobileHomePageService.onDisable(ids);
    }

    @PostMapping("/enable")
    @ResponseStatus(HttpStatus.OK)
    public void enable(@RequestParam Collection<Long> ids){
        mobileHomePageService.onEnable(ids);
    }

    @GetMapping
    public Payload findAll(@RequestParam(required = false) String query,
                           @RequestParam(required = false)String state,
                           @RequestParam(required = false,defaultValue = "0") int from,
                           @RequestParam(required = false,defaultValue = "20") int size){
        return new Payload(adminMobileHomePageMapper.findAll(new RequestData(query,null,null, StringUtils.isEmpty(state)?null:
                Collections.singleton(state),from,size)));
    }

    @GetMapping("/count")
    public Payload count(@RequestParam(required = false) String query,
                           @RequestParam(required = false)String state
                           ){
        return new Payload(adminMobileHomePageMapper.count(new RequestData(query,null,null,StringUtils.isEmpty(state)?null:
                Collections.singleton(state),0,0)));
    }

    @GetMapping("/{id}")
    public Payload get(@PathVariable Long id
    ){
        return new Payload(adminMobileHomePageMapper.get(id));
    }
}
