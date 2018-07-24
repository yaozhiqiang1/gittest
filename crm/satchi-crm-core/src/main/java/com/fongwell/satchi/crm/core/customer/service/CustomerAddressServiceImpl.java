package com.fongwell.satchi.crm.core.customer.service;

import com.fongwell.satchi.crm.core.customer.domain.aggregate.Addresses;
import com.fongwell.satchi.crm.core.customer.domain.entity.Address;
import com.fongwell.satchi.crm.core.customer.domain.repository.CustomerAddressRepository;
import com.fongwell.satchi.crm.core.customer.dto.AddressDto;
import com.fongwell.support.utils.Assert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by docker on 4/19/18.
 */
@Service("customerAddressService")
public class CustomerAddressServiceImpl implements CustomerAddressService {

    @Resource(name = "customerAddressRepository")
    private CustomerAddressRepository customerAddressRepository;

    @Override
    public Address addAddress(final long customerId, final AddressDto value) {

        Addresses customerAddress = customerAddressRepository.findOne(customerId);
        if (customerAddress == null) {
            customerAddress = new Addresses(customerId);
        }
        Address newAddress = customerAddress.addAddress(value);
        customerAddressRepository.save(customerAddress);

        return newAddress;
    }

    @Override
    public void toggleDefaultAddress(final long customerId, final long addressId) {

        Addresses customerAddress = customerAddressRepository.findOne(customerId);
        Assert.notNull(customerAddress, "Addresses: " + customerId);

        customerAddress.toggleDefaultAddress(addressId);
        customerAddressRepository.save(customerAddress);


    }

    @Override
    public void deleteAddress(final long customerId, final long addressId) {

        Addresses customerAddress = customerAddressRepository.findOne(customerId);
        Assert.notNull(customerAddress, "Addresses: " + customerId);

        if (customerAddress.deleteAddress(addressId) != null) {
            customerAddressRepository.save(customerAddress);
        }
    }

    @Override
    public Address updateAddress(final long customerId, final AddressDto value) {

        Addresses customerAddress = customerAddressRepository.findOne(customerId);
        Assert.notNull(customerAddress, "Addresses: " + customerId);
        Address updated = null;
        if (customerAddress.updateAddress(value) != updated) {
            customerAddressRepository.save(customerAddress);
        }

        return updated;
    }
}
