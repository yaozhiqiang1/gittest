package com.fongwell.satchi.crm.core.customer.domain.aggregate;

import com.fongwell.satchi.crm.core.customer.domain.entity.Address;
import com.fongwell.satchi.crm.core.customer.dto.AddressDto;
import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by docker on 4/19/18.
 */
@Entity
@Table(name = "crm_addresses")
public class Addresses extends AbstractAggregateRoot {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customerId", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @OrderColumn(name = "displayOrder")
    private List<Address> addresses;

    public Addresses(long customerId) {
        this.id = customerId;
        this.addresses = new LinkedList<>();
    }

    Addresses() {
    }

    public Address addAddress(AddressDto value) {

        boolean isDefaultAddress = value.getDefaultAddress() != null && value.getDefaultAddress();
        if (isDefaultAddress) {
            for (final Address address : addresses) {
                if (address.isDefaultAddress()) {
                    address.setDefaultAddress(false);
                }
            }
        }

        Address newAddress = new Address(this.id, value);
        this.addresses.add(newAddress);
        if (isDefaultAddress) {
            newAddress.setDefaultAddress(true);
        }


        return newAddress;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void toggleDefaultAddress(long addressId) {

        if (addresses == null || addresses.isEmpty()) {
            return;
        }

        if (addresses.size() == 1) {
            addresses.iterator().next().setDefaultAddress(true);
            return;
        }

        int disabled = 0;

        for (final Address address : addresses) {

            boolean isTarget = address.getId() != null && address.getId() == addressId;

            if (isTarget) {
                if (address.isDefaultAddress()) {
                    address.setDefaultAddress(false);
                    disabled = addresses.size();
                    break;
                } else {
                    address.setDefaultAddress(true);
                }
            } else if (address.isDefaultAddress()) {
                address.setDefaultAddress(false);
                disabled++;
            } else {
                disabled++;
            }
        }

        if (disabled == addresses.size()) {
            addresses.iterator().next().setDefaultAddress(true);
        }

    }

    public Address updateAddress(AddressDto value) {

        if (addresses == null || addresses.isEmpty()) {
            return null;
        }

        Address target = null;

        for (final Address address : addresses) {

            if (address.getId() != null && address.getId().equals(value.getId())) {
                target = address;
                target.setProperties(value);
            }
        }
        return target;
    }

    public Address deleteAddress(long addressId) {


        if (addresses == null || addresses.isEmpty()) {
            return null;
        }

        Address removed = null;

        for (final Address address : addresses) {
            if (address.getId() != null && address.getId() == addressId) {
                removed = address;
                break;
            }
        }
        if (removed != null) {
            addresses.remove(removed);
        }
        if (addresses.size() == 1) {
            addresses.iterator().next().setDefaultAddress(true);
        }
        return removed;

    }
}
