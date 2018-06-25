package com.fongwell.satchi.crm.core.order.exception;

import java.util.Collection;

/**
 * Created by docker on 4/24/18.
 */
public class InvalidSkusException extends CheckoutException {

    private Collection<Long> skuIds;

    public InvalidSkusException(final Collection<Long> skuIds, final String message) {
        super(message);
        this.skuIds = skuIds;
    }

    public Collection<Long> getMissingSkuIds() {
        return skuIds;
    }
}
