package com.fongwell.satchi.crm.core.order.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by docker on 4/19/18.
 */
public class CheckoutRequest implements Serializable {

    @NotNull(message = "addressId.required")
    private Long addressId;

    private String paymentGatewayType;

    private Collection<CheckoutItem> items;


    public String getPaymentGatewayType() {
        return paymentGatewayType;
    }

    public void setPaymentGatewayType(final String paymentGatewayType) {
        this.paymentGatewayType = paymentGatewayType;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(final Long addressId) {
        this.addressId = addressId;
    }


    public Collection<CheckoutItem> getItems() {
        return items;
    }

    public void setItems(final Collection<CheckoutItem> items) {
        this.items = items;
    }
}
