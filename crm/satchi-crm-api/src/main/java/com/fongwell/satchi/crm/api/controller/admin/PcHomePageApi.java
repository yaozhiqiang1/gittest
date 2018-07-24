package com.fongwell.satchi.crm.api.controller.admin;

import com.fongwell.base.rest.Payload;
import com.fongwell.base.validate.Validate;
import com.fongwell.satchi.crm.core.common.query.RequestData;
import com.fongwell.satchi.crm.core.homePage.pcHomePage.domain.aggregate.PcHomePage;
import com.fongwell.satchi.crm.core.homePage.pcHomePage.domain.dto.PcHomePageData;
import com.fongwell.satchi.crm.core.homePage.pcHomePage.query.AdminPcHomePageMapper;
import com.fongwell.satchi.crm.core.homePage.pcHomePage.service.PcHomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * Created by roman on 18-3-24.
 */
@RestController
@RequestMapping("/api/admin/pcHomePage")
public class PcHomePageApi {
    @Resource(name = "homePageService")
    private PcHomePageService pageService;

    @Autowired
    private AdminPcHomePageMapper adminPcHomePageMapper;

    @GetMapping("")
    public Payload findAll(@RequestParam(required = false,defaultValue = "0")int from,@RequestParam(required = false,defaultValue = "20")int size ){
        return new Payload(adminPcHomePageMapper.findAll(new RequestData(from,size)));
    }

    @GetMapping("/count")
    public Payload count(){
        return new Payload(adminPcHomePageMapper.count(new RequestData(0,0)));
    }


    @PostMapping("/sort/{id}")
    public Payload sort(@PathVariable Long id,@RequestParam int orderNumber){
        pageService.onSort(id,orderNumber);
        return Payload.empty;
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Validate
    public void create(@RequestBody@Valid PcHomePageData data, BindingResult result){
        pageService.onCreate(data);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Validate
    public void update(@PathVariable Long id,@RequestBody@Valid PcHomePageData data, BindingResult result){
        pageService.onUpdate(id,data);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam Collection<Long> ids){
        pageService.onDelete(ids);
    }

    @GetMapping("/{id}")
    public Payload get(@PathVariable Long id){
        return new Payload(adminPcHomePageMapper.get(id));
    }

    @PostMapping("/enable")
    @ResponseStatus(HttpStatus.OK)
    public void enable(@RequestParam List<Long> ids){
        pageService.onEnable(ids);
    }

    @PostMapping("/disable")
    @ResponseStatus(HttpStatus.OK)
    public void disable(@RequestParam List<Long> ids){
        pageService.onDisable(ids);
    }

}
