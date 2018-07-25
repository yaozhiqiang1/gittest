package com.fongwell.satchi.crm.api.controller.admin;

import com.fongwell.base.rest.Payload;
import com.fongwell.satchi.crm.core.store.domain.aggregate.Store;
import com.fongwell.satchi.crm.core.store.domain.aggregate.entity.StoreImage;
import com.fongwell.satchi.crm.core.store.dto.StoreData;
import com.fongwell.satchi.crm.core.store.query.AdminStoreQueryMapper;
import com.fongwell.satchi.crm.core.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by roman on 18-3-30.
 */
@RestController
@RequestMapping("/api/admin/store")
public class StoreApi {

    @Resource(name = "storeService")
    private StoreService storeService;

    @Autowired
    private AdminStoreQueryMapper adminStoreQueryMapper;

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam Collection<Long> ids) {
        storeService.delete(ids);
    }

    @PostMapping("/disable")
    @ResponseStatus(HttpStatus.OK)
    public void disable(@RequestParam Collection<Long> ids) {
        storeService.disable(ids);
    }

    @PostMapping("/enable")
    @ResponseStatus(HttpStatus.OK)
    public void enable(@RequestParam Collection<Long> ids) {
        storeService.enable(ids);
    }

    @PutMapping("")
    public Payload create(@RequestBody StoreData data) {
        storeService.create(data);
        return Payload.empty;
    }

    @PostMapping("/{id}")
    public Payload update(@PathVariable Long id, @RequestBody StoreData data) {
        storeService.update(id, data);
        return Payload.empty;
    }

    @GetMapping("")
    public Payload findAll(@RequestParam(required = false, defaultValue = "0") int from,
                           @RequestParam(required = false, defaultValue = "20") int size) {
        return new Payload(adminStoreQueryMapper.listStore(from, size));
    }

    @GetMapping("/count")
    public Payload count() {
        return new Payload(adminStoreQueryMapper.countStore());
    }

    @GetMapping("/{id}")
    public Payload get(@PathVariable Long id) {
        return new Payload(adminStoreQueryMapper.get(id));
    }


}
