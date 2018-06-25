package com.fongwell.satchi.crm.core.category.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by roman on 18-4-16.
 */
public class TopCategoryData implements Serializable{
//    @NotBlank(message = "TopCategoryData.name require")
    private String name;
    @NotBlank(message = "TopCategoryData.imageUrl require")
    private String imageUrl;

    private int orderNumber;
    @NotNull(message = "TopCategoryData.categoryId require")
    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
