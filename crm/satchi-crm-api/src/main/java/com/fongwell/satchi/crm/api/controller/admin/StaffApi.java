package com.fongwell.satchi.crm.api.controller.admin;

import com.fongwell.base.validate.Validate;
import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.core.store.domain.aggregate.dto.StaffData;
import com.fongwell.satchi.crm.core.store.domain.aggregate.service.StaffService;
import com.fongwell.satchi.crm.core.store.query.AdminStaffQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by docker on 5/21/18.
 */
@RestController
@RequestMapping("/api/admin/staffs")
public class StaffApi {

    @Resource(name = "staffService")
    private StaffService staffService;

    @Autowired
    private AdminStaffQueryMapper adminStaffQueryMapper;

    @GetMapping("")
    public Payload staffs(
            @RequestParam long storeId,
            @RequestParam(required = false, value = "query") String query,
            @RequestParam(required = false, defaultValue = "0") int from, @RequestParam(required = false, defaultValue = "20") int size) {

        return new Payload(adminStaffQueryMapper.queryStaffs(storeId, from, size));
    }

    @GetMapping("/count")
    public Payload count(
            @RequestParam long storeId,
            @RequestParam(required = false, value = "query") String query
    ) {

        return new Payload(adminStaffQueryMapper.countStaffs(storeId));
    }


    @PostMapping("/create")
    @Validate
    public Payload create(@Valid @RequestBody StaffData data, BindingResult result) {
        return new Payload(staffService.createStaff(data.getStoreId(), data).getId());
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @Validate
    public void update(@Valid @RequestBody StaffData data, BindingResult result) {
        staffService.updateStaff(data.getId(), data);
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam long id) {
        staffService.deleteStaff(id);
    }
}
