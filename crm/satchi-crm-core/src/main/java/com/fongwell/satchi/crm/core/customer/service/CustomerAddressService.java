package com.fongwell.satchi.crm.core.customer.service;

import com.fongwell.satchi.crm.core.customer.domain.entity.Address;
import com.fongwell.satchi.crm.core.customer.dto.AddressDto;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by docker on 4/19/18.
 */
@Transactional
public interface CustomerAddressService {

    Address addAddress(long customerId, AddressDto value);

    void toggleDefaultAddress(long customerId, long addressId);

    void deleteAddress(long customerId, long addressId);

    Address updateAddress(long customerId, AddressDto value);


}
