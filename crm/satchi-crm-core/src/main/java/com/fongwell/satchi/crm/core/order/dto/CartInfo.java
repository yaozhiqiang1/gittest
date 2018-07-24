package com.fongwell.satchi.crm.core.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by docker on 4/23/18.
 */
public class CartInfo implements Serializable {

    private Collection<CartItemInfo> items;

    private BigDecimal price;

    private BigDecimal salePrice;
    private Collection<Long> invalidSkus;

    private Collection<Long> outOfStockSkus;

    public Collection<Long> getInvalidSkus() {
        return invalidSkus;
    }

    public void setInvalidSkus(final Collection<Long> invalidSkus) {
        this.invalidSkus = invalidSkus;
    }

    public Collection<Long> getOutOfStockSkus() {
        return outOfStockSkus;
    }

    public void setOutOfStockSkus(final Collection<Long> outOfStockSkus) {
        this.outOfStockSkus = outOfStockSkus;
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

    public Collection<CartItemInfo> getItems() {
        return items;
    }

    public void setItems(final Collection<CartItemInfo> items) {
        this.items = items;
    }
}
