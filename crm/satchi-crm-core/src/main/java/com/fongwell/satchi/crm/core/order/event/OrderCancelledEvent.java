package com.fongwell.satchi.crm.core.order.event;

import com.fongwell.infrastructure.event.AbstractDomainEvent;

import java.util.Collection;

/**
 * Created by docker on 5/9/18.
 */
public class OrderCancelledEvent extends AbstractDomainEvent {

    private long orderId;

    private Collection<OrderEventItem> items;

    public OrderCancelledEvent(final long orderId, final Collection<OrderEventItem> items) {
        this.orderId = orderId;
        this.items = items;
    }

    OrderCancelledEvent() {
    }

    @Override
    public String getId() {
        return String.valueOf(orderId);
    }

    @Override
    public String getAggregateType() {
        return "Order";
    }

    public long getOrderId() {
        return orderId;
    }

    public Collection<OrderEventItem> getItems() {
        return items;
    }

}
