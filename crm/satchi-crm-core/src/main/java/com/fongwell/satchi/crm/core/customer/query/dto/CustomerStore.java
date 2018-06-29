package com.fongwell.satchi.crm.core.customer.query.dto;

import java.io.Serializable;

/**
 * Date:2018/6/26
 * Author: yaozhiqiang
 * Desc:
 */
public class CustomerStore implements Serializable{

    private String mobile;
    private Long storeId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "CustomerStore{" +
                "mobile='" + mobile + '\'' +
                ", storeId=" + storeId +
                '}';
    }
}
