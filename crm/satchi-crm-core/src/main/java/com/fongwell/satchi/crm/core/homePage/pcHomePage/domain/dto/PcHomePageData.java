package com.fongwell.satchi.crm.core.homePage.pcHomePage.domain.dto;

import com.fongwell.satchi.crm.core.homePage.value.LinkConfig;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by roman on 18-3-28.
 */
public class PcHomePageData implements Serializable{
    @Size(min = 1,max = 24)
    private String title;
    @Size(min = 1,max = 500)
    private String url;
    @Size(max = 240)
    private String introduction;
    @Size(max = 12,message = "HomePage.button long length")
    @NotNull(message = "HomePage.button empty")
    private String button;
    @Size(max = 500,message = "HomePage.imageUrl long length")
    @NotNull(message = "HomePage.imageUrl empty")
    private String imageUrl;
    @Size(max = 500,message = "HomePage.videoUrl long length")
    @NotNull(message = "HomePage.videoUrl empty")
    private String videoUrl;
    @Size(max = 30,message = "HomePage.source long length")
    @NotNull(message = "HomePage.source empty")
    private String source;
    @Min(value = 1,message = "HomePage.opacity smallest")
    @NotNull(message = "HomePage.opacity null")
    private Integer opacity;
    private Long container;
    private int orderNumber;
    private LinkConfig linkConfig;

    public LinkConfig getLinkConfig() {
        return linkConfig;
    }

    public void setLinkConfig(final LinkConfig linkConfig) {
        this.linkConfig = linkConfig;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getOpacity() {
        return opacity;
    }

    public void setOpacity(Integer opacity) {
        this.opacity = opacity;
    }

    public Long getContainer() {
        return container;
    }

    public void setContainer(Long container) {
        this.container = container;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
