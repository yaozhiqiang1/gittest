package com.fongwell.satchi.crm.core.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by docker on 4/23/18.
 */
public class CartItemInfo implements Serializable {

    private long skuId;

    private long productId;

    private String name;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal salePrice;

    private BigDecimal totalPrice;

    private BigDecimal totalSalePrice;

    private String pcImage;
    private String mobileImage;

    private String imageUrl;
    private String skuNumber;

    private Integer stock;

    public Integer getStock() {
        return stock;
    }

    public void setStock(final Integer stock) {
        this.stock = stock;
    }

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

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(final BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(final BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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
