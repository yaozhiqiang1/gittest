package com.fongwell.satchi.crm.core.product.service.inventory.exception;

import java.util.Collection;

/**
 * Created by docker on 4/20/18.
 */
public class InsufficientInventoryException extends RuntimeException {

    private Collection<Long> skuIds;

    public InsufficientInventoryException(final String message, final Collection<Long> skuIds) {
        super(message);
        this.skuIds = skuIds;
    }

    public Collection<Long> getSkuIds() {
        return skuIds;
    }
}
