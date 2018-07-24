package com.fongwell.satchi.crm.core.homePage.mobileHomePage.domain.aggregate;

import com.fongwell.satchi.crm.core.common.State;
import com.fongwell.satchi.crm.core.homePage.value.LinkConfig;
import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by roman on 18-4-13.
 */
@Entity
@Table(name = "crm_mobile_home_page", indexes = {@Index(name = "crm_mobile_home_page_title_idx", columnList = "title")})
public class MobileHomePage extends AbstractAggregateRoot {

    private String title;

    @Column(name = "imageUrl", columnDefinition = "text")
    private String imageUrl;

    private int orderNumber;
    @Column(name = "url", columnDefinition = "text")
    private String url;

    @Enumerated(EnumType.STRING)
    private State state = State.disable;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "type", column = @Column(name = "link_type", length = 10)),
            @AttributeOverride(name = "id", column = @Column(name = "link_id"))})
    private LinkConfig linkConfig;

    public MobileHomePage() {
        super();
        createdDate = new Date();
        lastModifiedDate = new Date();
    }

    public void onDisable() {
        this.state = State.disable;
        modifiedDate();
    }

    public void onEnable() {
        this.state = State.enable;
        modifiedDate();
    }

    public void onDelete() {
        this.deleted = true;
        modifiedDate();
    }

    public void onSort(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void modifiedDate() {
        this.lastModifiedDate = new Date();
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public State getState() {
        return state;
    }

    public String getUrl() {
        return url;
    }
}
