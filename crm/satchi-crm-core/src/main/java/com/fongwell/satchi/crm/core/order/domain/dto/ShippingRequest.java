package com.fongwell.satchi.crm.core.order.domain.dto;

import java.io.Serializable;

/**
 * Created by docker on 5/9/18.
 */
public class ShippingRequest implements Serializable {

    private String trackingNumber;

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(final String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
