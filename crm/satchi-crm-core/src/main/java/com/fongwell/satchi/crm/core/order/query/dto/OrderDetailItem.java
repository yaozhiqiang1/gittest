package com.fongwell.satchi.crm.core.order.query.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by docker on 4/23/18.
 */
public class OrderDetailItem implements Serializable {

    private long id;
    private long skuId;
    private long productId;
    private String name;
    private String pcImage;
    private String mobileImage;
    private String imageUrl;
    private int quantity;

    private BigDecimal salePrice;

    private BigDecimal totalSalePrice;
    private String skuNumber;

    private BigDecimal price;
    private BigDecimal totalPrice;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPcImage() {
        return pcImage;
    }

    public void setPcImage(final String pcImage) {
        this.pcImage = pcImage;
    }

    public String getMobileImage() {
        return mobileImage;
    }

    public void setMobileImage(final String mobileImage) {
        this.mobileImage = mobileImage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(final BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(final long skuId) {
        this.skuId = skuId;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(final BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getTotalSalePrice() {
        return totalSalePrice;
    }

    public void setTotalSalePrice(final BigDecimal totalSalePrice) {
        this.totalSalePrice = totalSalePrice;
    }

    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(final String skuNumber) {
        this.skuNumber = skuNumber;
    }
}
