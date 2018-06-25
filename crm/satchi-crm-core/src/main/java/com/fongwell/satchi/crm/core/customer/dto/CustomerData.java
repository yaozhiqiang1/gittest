package com.fongwell.satchi.crm.core.customer.dto;

import com.fongwell.satchi.crm.core.customer.domain.value.Sex;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by docker on 5/22/18.
 */
public class CustomerData implements Serializable {

    private Long id;

    private Date dob;
    @NotEmpty(message = "name.required")
    private String name;
    @NotEmpty(message = "mobile.required")
    private String mobile;
    private String note;
    private Sex sex;
    private Long storeId;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(final Date dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    public String getNote() {
        return note;
    }

    public void setNote(final String note) {
        this.note = note;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(final Sex sex) {
        this.sex = sex;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(final Long storeId) {
        this.storeId = storeId;
    }
}
