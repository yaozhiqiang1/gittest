package com.fongwell.satchi.crm.core.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by docker on 4/23/18.
 */
public class CartItemPrice implements Serializable {

    private long skuId;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal salePrice;

    private BigDecimal totalPrice;

    private BigDecimal totalSalePrice;

    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(final long skuId) {
        this.skuId = skuId;
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
}
