package com.fongwell.satchi.crm.api.controller.wx;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.api.authentication.wx.WxCustomerContext;
import com.fongwell.satchi.crm.core.customer.dto.AddressDto;
import com.fongwell.satchi.crm.core.customer.query.repository.CustomerAddressQueryRepository;
import com.fongwell.satchi.crm.core.customer.service.CustomerAddressService;

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
        Collection<AddressDto> addressDtos = customerAddressQueryRepository.queryAddreses(WxCustomerContext.getUser().getCustomerId());
        for (AddressDto addressDto : addressDtos) {
            if (!StringUtils.isNotBlank(addressDto.getDistrict())){
                addressDto.setDistrict("");
            }
        }
        return new Payload(addressDtos);
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
