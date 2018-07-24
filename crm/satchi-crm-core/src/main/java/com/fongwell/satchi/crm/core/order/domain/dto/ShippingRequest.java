package com.fongwell.satchi.crm.core.order.domain.dto;

import java.io.Serializable;

/**
 * Created by docker on 5/9/18.
 */
public class ShippingRequest implements Serializable {

    private String trackingNumber;

    private String trackingName;

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(final String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

	public String getTrackingName() {
		return trackingName;
	}

	public void setTrackingName(String trackingName) {
		this.trackingName = trackingName;
	}
    
    
    
}
