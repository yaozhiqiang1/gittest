package com.fongwell.satchi.crm.core.category.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by roman on 18-4-8.
 */
public class CategoryData implements Serializable {
    @NotBlank(message = "CategoryData.name require")
    private String name;
//    @NotBlank(message = "CategoryData.pcImageUrl require")
    private String pcImageUrl;
//    @NotBlank(message = "CategoryData.mobileImageUrl require")
    private String mobileImageUrl;

    private int orderNumber;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcImageUrl() {
        return pcImageUrl;
    }

    public void setPcImageUrl(String pcImageUrl) {
        this.pcImageUrl = pcImageUrl;
    }

    public String getMobileImageUrl() {
        return mobileImageUrl;
    }

    public void setMobileImageUrl(String mobileImageUrl) {
        this.mobileImageUrl = mobileImageUrl;
    }
}
