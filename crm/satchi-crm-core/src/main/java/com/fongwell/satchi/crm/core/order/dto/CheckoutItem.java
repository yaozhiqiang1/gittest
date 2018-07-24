package com.fongwell.satchi.crm.core.order.dto;

import java.io.Serializable;

/**
 * Created by docker on 4/19/18.
 */
public class CheckoutItem implements Serializable {

    private Long skuId;
    private Integer quantity;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(final Long skuId) {
        this.skuId = skuId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }
}
