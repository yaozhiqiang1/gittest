package com.fongwell.satchi.crm.api.controller.admin;

import com.fongwell.base.rest.Payload;
import com.fongwell.base.validate.Validate;
import com.fongwell.satchi.crm.core.store.domain.aggregate.dto.StoreImageData;
import com.fongwell.satchi.crm.core.store.domain.aggregate.query.AdminStoreImageQueryMapper;
import com.fongwell.satchi.crm.core.store.domain.aggregate.service.StoreImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

/**
 * Created by roman on 18-3-30.
 */
@RestController
@RequestMapping("/api/admin/storeImage")
public class StoreImageApi {

    @Resource(name = "storeImageService")
    private StoreImageService storeImageService;

    @Autowired
    private AdminStoreImageQueryMapper adminStoreImageQueryMapper;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        storeImageService.delete(id);
    }

    @PostMapping("/{imageId}/{storeId}")
    public Payload update(@PathVariable Long storeId ,@PathVariable Long imageId){
        storeImageService.specify(imageId,storeId);
        return Payload.empty;
    }

    @GetMapping("")
    public Payload findAll(@RequestParam(required = false,defaultValue = "0") int from,
                           @RequestParam(required = false,defaultValue = "20") int size){
        return new Payload(adminStoreImageQueryMapper.listStoreImage(from,size));
    }

    @GetMapping("/count")
    public Payload count(){
        return new Payload(adminStoreImageQueryMapper.countStoreImage());
    }

    @GetMapping("/{id}")
    public Payload get(@PathVariable Long id){
        return new Payload(adminStoreImageQueryMapper.get(id));
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    @Validate
    public void create(@RequestBody@Valid StoreImageData data, BindingResult result){
        storeImageService.create(data);
    }

}
