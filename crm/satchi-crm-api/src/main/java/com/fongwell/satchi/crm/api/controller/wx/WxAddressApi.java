package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.api.authentication.wx.WxCustomerContext;
import com.fongwell.satchi.crm.core.customer.dto.AddressDto;
import com.fongwell.satchi.crm.core.customer.query.repository.CustomerAddressQueryRepository;
import com.fongwell.satchi.crm.core.customer.service.CustomerAddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by docker on 4/23/18.
 */
@RestController
@RequestMapping("/api/wx/address")
public class WxAddressApi {

    @Resource(name = "customerAddressQueryRepository")
    private CustomerAddressQueryRepository customerAddressQueryRepository;

    @Resource(name = "customerAddressService")
    private CustomerAddressService customerAddressService;

    @GetMapping("")
    public Payload addresses() {

        return new Payload(customerAddressQueryRepository.queryAddreses(WxCustomerContext.getUser().getCustomerId()));
    }

    @PutMapping("")
    public Payload addAddress(@RequestBody AddressDto address) {
        return new Payload(customerAddressService.addAddress(WxCustomerContext.getUser().getCustomerId(), address).getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAddress(@PathVariable long id) {
        customerAddressService.deleteAddress(WxCustomerContext.getUser().getCustomerId(), id);
    }

    @PostMapping("/toggleDefault/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void toggleDefault(@PathVariable long id) {
        customerAddressService.toggleDefaultAddress(WxCustomerContext.getUser().getCustomerId(), id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAddress(@PathVariable long id, @RequestBody AddressDto address) {
        address.setId(id);
        customerAddressService.updateAddress(WxCustomerContext.getUser().getCustomerId(), address);
    }


}
