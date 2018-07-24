package com.fongwell.satchi.crm.core.order.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by docker on 4/23/18.
 */
public class CartRequest implements Serializable {

    @NotEmpty(message = "items.required")
    private Collection<CheckoutItem> items;

    public Collection<CheckoutItem> getItems() {
        return items;
    }

    public void setItems(final Collection<CheckoutItem> items) {
        this.items = items;
    }
}
