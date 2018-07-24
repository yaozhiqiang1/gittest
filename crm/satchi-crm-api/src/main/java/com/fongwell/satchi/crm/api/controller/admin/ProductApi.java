package com.fongwell.satchi.crm.api.controller.admin;

import com.fongwell.base.rest.Payload;
import com.fongwell.base.validate.Validate;
import com.fongwell.satchi.crm.core.common.query.RequestData;
import com.fongwell.satchi.crm.core.product.dto.ProductData;
import com.fongwell.satchi.crm.core.product.query.AdminProductQueryMapper;
import com.fongwell.satchi.crm.core.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by roman on 18-4-4.
 */
@RestController
@RequestMapping("/api/admin/product")
public class ProductApi {

    @Autowired
    private ProductService productService;

    @Autowired
    private AdminProductQueryMapper adminProductQueryMapper;

    @GetMapping
    public Payload findAll(@RequestParam(required = false) String query,
                           @RequestParam(required = false) String state,
                           @RequestParam(required = false) Long categoryId,
                           @RequestParam(required = false) Collection<String> numbers,
                           @RequestParam(required = false,defaultValue = "0") int from,
                           @RequestParam(required = false,defaultValue = "20") int size){
        RequestData request = new RequestData(query,categoryId == null?null:Collections.singleton(categoryId),numbers, StringUtils.isEmpty(state)?null: Collections.singleton(state),from,size);
        return new Payload(adminProductQueryMapper.findAll(request));
    }

    @GetMapping("/count")
    public Payload count(@RequestParam(required = false) String query,
                           @RequestParam(required = false) String state,
                           @RequestParam(required = false) Long categoryId,
                           @RequestParam(required = false) Collection<String> numbers){
        RequestData request = new RequestData(query,categoryId == null?null:Collections.singleton(categoryId),numbers, StringUtils.isEmpty(state)?null: Collections.singleton(state),0,0);
        return new Payload(adminProductQueryMapper.count(request));
    }

    @PutMapping
    @Validate
    @ResponseStatus(HttpStatus.OK)
    public void create(@Valid @RequestBody ProductData data, BindingResult result){
    	productService.onCreate(data);
    }

    @PostMapping("/{id}")
    @Validate
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id,@Valid @RequestBody ProductData data, BindingResult result){
        productService.onUpdate(id,data);
    }

    @PostMapping("/disable")
    @ResponseStatus(HttpStatus.OK)
    public void disable(@RequestParam Collection<Long> ids){
        productService.onDisable(ids);
    }

    @PostMapping("/enable")
    @ResponseStatus(HttpStatus.OK)
    public void enable(@RequestParam Collection<Long> ids){
        productService.onEnable(ids);
    }
    @DeleteMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam Collection<Long> ids){
        productService.onDelete(ids);
    }

    @GetMapping("/{id}")
    public Payload get(@PathVariable Long id){
        return new Payload(adminProductQueryMapper.get(id));
    }
    @PostMapping("/sort/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void sort(@PathVariable Long id,@RequestParam(required = false,defaultValue = "0") Integer orderNumber){
        productService.onSortting(id,orderNumber);
    }
}
