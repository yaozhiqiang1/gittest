package com.fongwell.satchi.crm.core.store.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by roman on 18-3-30.
 */
public class StoreData implements Serializable {

    @NotEmpty(message = "StoreData.name require")
    @Length(min = 1, max = 20)
    private String name;

    private String province;

    private String city;

    private String address;

    private String contact;

    private int orderNumber;


    private String open;

    private String close;

    public String getOpen() {
        return open;
    }

    public void setOpen(final String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(final String close) {
        this.close = close;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
