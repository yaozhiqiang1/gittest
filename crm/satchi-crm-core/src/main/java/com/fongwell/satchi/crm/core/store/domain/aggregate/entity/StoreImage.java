package com.fongwell.satchi.crm.core.store.domain.aggregate.entity;

import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by roman on 18-3-30.
 */
@Entity
@Table(name = "crm_store_image")
public class StoreImage extends AbstractAggregateRoot{

    private String imageUrl;

    private Long storeId;

    public StoreImage(){
        createdDate = new Date();
        lastModifiedDate = new Date();
    }

    public void specify(Long storeId){
        this.storeId = storeId;
        this.lastModifiedDate = new Date();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getStoreId() {
        return storeId;
    }
}
