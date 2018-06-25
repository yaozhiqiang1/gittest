package com.fongwell.satchi.crm.core.brandNews.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by roman on 18-4-16.
 */
public class BrandNewsData implements Serializable{
    @NotBlank(message = "BrandNewData.title require")
    protected String title;
    @Length(max = 500,message = "BrandNewData.introduction long length")
    protected String introduction;

    private String coverUrl;
    private String topCoverUrl;

    private String videoCoverUrl;
    private String videoUrl;

    private boolean sticky;

    private int orderNumber;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getTopCoverUrl() {
        return topCoverUrl;
    }

    public void setTopCoverUrl(String topCoverUrl) {
        this.topCoverUrl = topCoverUrl;
    }

    public String getVideoCoverUrl() {
        return videoCoverUrl;
    }

    public void setVideoCoverUrl(String videoCoverUrl) {
        this.videoCoverUrl = videoCoverUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public boolean isSticky() {
        return sticky;
    }

    public void setSticky(boolean sticky) {
        this.sticky = sticky;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
