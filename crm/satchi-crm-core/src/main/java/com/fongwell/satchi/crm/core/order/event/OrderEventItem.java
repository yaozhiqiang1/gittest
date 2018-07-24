package com.fongwell.satchi.crm.core.order.event;

import java.io.Serializable;

/**
 * Created by docker on 5/11/18.
 */
public class OrderEventItem implements Serializable {

    private long productId;
    private long skuId;
    private int quantity;

    public OrderEventItem(final long productId, final long skuId, final int quantity) {
        this.productId = productId;
        this.skuId = skuId;
        this.quantity = quantity;
    }

    OrderEventItem() {
    }

    public long getProductId() {
        return productId;
    }

    public long getSkuId() {
        return skuId;
    }

    public int getQuantity() {
        return quantity;
    }
}
