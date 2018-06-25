package com.fongwell.satchi.crm.core.brandNews.domain.aggregate;

import com.fongwell.satchi.crm.core.common.State;
import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;
import com.fongwell.satchi.crm.core.support.ddd.Auditable;
import com.fongwell.satchi.crm.core.support.ddd.Sorter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by roman on 18-3-23.
 */
@Entity
@Table(name = "crm_brand_news", indexes = {@Index(name = "brand_news_title_idx", columnList = "title")})
public class BrandNews extends AbstractAggregateRoot {

    @Column(name = "title", length = 30)
    protected String title;
    @Column(name = "introduction", columnDefinition = "text")
    protected String introduction;

    @Column(name = "coverUrl", columnDefinition = "text")
    private String coverUrl;
    @Column(name = "topCoverUrl", columnDefinition = "text")
    private String topCoverUrl;

    @Column(name = "videoCoverUrl", columnDefinition = "text")
    private String videoCoverUrl;
    @Column(name = "videoUrl", columnDefinition = "text")
    private String videoUrl;

    private boolean sticky;

    private int orderNumber;

    @Enumerated(EnumType.STRING)
    private State state = State.disable;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    BrandNews() {
        super();
        this.createdDate = new Date();
        this.lastModifiedDate = new Date();
    }

    public void onEnable() {
        this.state = State.enable;
        modifiedDate();
    }

    public void onDisable() {
        this.state = State.disable;
        modifiedDate();
    }

    public void onSort(int orderNumber) {
        this.orderNumber = orderNumber;
        modifiedDate();
    }

    public void onDelete(){
        this.deleted = true;
        modifiedDate();
    }

    public void modifiedDate() {
        this.lastModifiedDate = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getTopCoverUrl() {
        return topCoverUrl;
    }

    public boolean isSticky() {
        return sticky;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getContent() {
        return content;
    }

    public String getVideoCoverUrl() {
        return videoCoverUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public State getState() {
        return state;
    }

    @Override
    public int hashCode() {
        int hashCode = id.hashCode();
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null && obj.getClass() != this.getClass()) {
            return false;
        }
        BrandNews that = (BrandNews) obj;
        if (this.hashCode() == that.hashCode()) {
            return true;
        }
        return false;
    }
}
