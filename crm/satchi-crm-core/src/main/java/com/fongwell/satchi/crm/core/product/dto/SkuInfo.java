package com.fongwell.satchi.crm.core.product.dto;

import java.io.Serializable;

/**
 * Created by docker on 4/23/18.
 */
public class SkuInfo implements Serializable {

    private long id;
    private long productId;
    private String mobileImage;
    private String pcImage;
    private String name;
    private String skuNumber;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMobileImage() {
        return mobileImage;
    }

    public void setMobileImage(final String mobileImage) {
        this.mobileImage = mobileImage;
    }

    public String getPcImage() {
        return pcImage;
    }

    public void setPcImage(final String pcImage) {
        this.pcImage = pcImage;
    }

    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(final String skuNumber) {
        this.skuNumber = skuNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(final long productId) {
        this.productId = productId;
    }


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final SkuInfo skuInfo = (SkuInfo) o;

        return id == skuInfo.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
