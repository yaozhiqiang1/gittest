package com.fongwell.satchi.crm.core.order.domain.aggregate;

import com.fongwell.satchi.crm.core.customer.domain.value.AddressValue;
import com.fongwell.satchi.crm.core.order.domain.value.ShippingState;
import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;

import javax.persistence.*;

/**
 * Created by docker on 4/20/18.
 */
@Entity
@Table(name = "crm_shipping", indexes = @Index(name = "crm_shipping_orderId_idx", columnList = "order_id"))
public class Shipping extends AbstractAggregateRoot {

    @Column(name = "order_id")
    private long orderId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "province", column = @Column(name = "province", length = 20)),
            @AttributeOverride(name = "city", column = @Column(name = "city", length = 20)),
            @AttributeOverride(name = "district", column = @Column(name = "district", length = 20)),
            @AttributeOverride(name = "address", column = @Column(name = "address")),
            @AttributeOverride(name = "postcode", column = @Column(name = "postcode", length = 10))
    })
    private AddressValue address;

    @Column(name = "customer_id")
    private long customerId;

    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", length = 10)
    private ShippingState state;


    public Shipping(final long customerId, final long orderId, final AddressValue address) {
        this.customerId = customerId;
        this.address = address;
        this.orderId = orderId;
        state = ShippingState.pending;
    }

    Shipping() {
    }


    public void setState(final ShippingState state) {
        this.state = state;
    }

    public void setTrackingNumber(final String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public AddressValue getAddress() {
        return address;
    }

    public void setAddress(AddressValue address) {
        this.address = address;
    }
}
