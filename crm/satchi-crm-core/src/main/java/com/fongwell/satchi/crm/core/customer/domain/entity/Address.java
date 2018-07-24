package com.fongwell.satchi.crm.core.customer.domain.entity;

import com.fongwell.satchi.crm.core.customer.dto.AddressDto;
import com.fongwell.satchi.crm.core.support.id.Snowflake;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by docker on 4/19/18.
 */
@Entity
@Table(name = "crm_address", indexes = @Index(name = "crm_address_idx", columnList = "customer_id"))
public class Address implements Serializable, Persistable<Long> {

    @Id
    private Long id;

    @Column(name = "customer_id")
    private long customerId;

    private String receiver;

    private String contactNumber;

    private String province;

    private String city;

    private String district;

    private String address;

    private String postcode;

    private boolean defaultAddress;

    @Transient
    private boolean isNew = false;

    public Address(long customerId, AddressDto value) {
        this.customerId = customerId;
        this.defaultAddress = false;
        this.isNew = true;
        setProperties(value);
        this.id = Snowflake.id();
    }

    Address() {
    }

    public void setProperties(AddressDto value) {
        this.receiver = value.getReceiver();
        this.address = value.getAddress();
        this.province = value.getProvince();
        this.city = value.getCity();
        this.district = value.getDistrict();
        this.contactNumber = value.getContactNumber();
        this.postcode = value.getPostcode();

    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @Override
    public Long getId() {
        return id;
    }

    public boolean isDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(final boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(final String receiver) {
        this.receiver = receiver;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(final String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(final String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(final String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(final String postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Address address = (Address) o;

        return id != null ? id.equals(address.id) : address.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
