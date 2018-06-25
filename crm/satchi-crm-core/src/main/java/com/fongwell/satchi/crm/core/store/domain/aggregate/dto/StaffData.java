package com.fongwell.satchi.crm.core.store.domain.aggregate.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by docker on 5/21/18.
 */
public class StaffData implements Serializable {

    private Long id;

    private Long storeId;

    @NotEmpty(message = "name.required")
    private String name;

    @NotEmpty(message = "number.required")
    private String number;

    @NotEmpty(message = "mobile.required")
    private String mobile;

    private String note;
    @NotEmpty(message = "position.required")
    private String position;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(final Long storeId) {
        this.storeId = storeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(final String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
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
}
