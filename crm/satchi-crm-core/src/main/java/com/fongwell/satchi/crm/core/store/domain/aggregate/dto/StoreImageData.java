package com.fongwell.satchi.crm.core.store.domain.aggregate.dto;

import java.io.Serializable;

/**
 * Created by roman on 18-3-30.
 */
public class StoreImageData implements Serializable {

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
