package com.fongwell.satchi.crm.core.homePage.mobileHomePage.domain.dto;

import com.fongwell.satchi.crm.core.homePage.value.LinkConfig;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by roman on 18-4-13.
 */
public class MobileHomePageData implements Serializable{

    @NotBlank(message = "MobileHomePageData.title require")
    private String title;
    @NotBlank(message = "MobileHomePageData.imageUrl require")
    private String imageUrl;

    private int orderNumber;

    private String url;

    private LinkConfig linkConfig;

    public LinkConfig getLinkConfig() {
        return linkConfig;
    }

    public void setLinkConfig(final LinkConfig linkConfig) {
        this.linkConfig = linkConfig;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
