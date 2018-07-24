package com.fongwell.satchi.crm.core.customer.dto;

import java.io.Serializable;

/**
 * Created by docker on 4/19/18.
 */
public class AddressDto implements Serializable {

    private Long id;


    private String receiver;

    private String contactNumber;

    private String province;

    private String district;

    private String address;

    private String postcode;

    private String city;

    private Boolean defaultAddress;

    public Boolean getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(final Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }
}
