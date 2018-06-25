package com.fongwell.satchi.crm.core.category.domain.aggregate;

import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by roman on 18-4-3.
 */
@Entity
@Table(name = "crm_category",indexes = {@Index(name = "crm_category_name_idx",columnList = "name")})
public class Category extends AbstractAggregateRoot {

    private String name;

    private String pcImageUrl;

    private String mobileImageUrl;

    private int orderNumber;

    public Category() {
        super();
        createdDate = new Date();
        lastModifiedDate = new Date();
    }

    public Category(String name,String pcImageUrl,String mobileImageUrl,int orderNumber) {
        super();
        this.name = name;
        this.pcImageUrl = pcImageUrl;
        this.mobileImageUrl = mobileImageUrl;
        this.orderNumber = orderNumber;
        modifiedDate();
    }

    public void onDelete(){
        deleted = true;
        modifiedDate();
    }

    public void onUpdate(String name,String pcImageUrl,String mobileImageUrl,int orderNumber){
        this.name = name;
        this.pcImageUrl = pcImageUrl;
        this.mobileImageUrl = mobileImageUrl;
        this.orderNumber = orderNumber;
        modifiedDate();
    }

    public void sort(int orderNumber){
        this.orderNumber = orderNumber;
        modifiedDate();
    }

    public void modifiedDate(){
        this.lastModifiedDate = new Date();
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

    public String getMobileImageUrl() {
        return mobileImageUrl;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
}
