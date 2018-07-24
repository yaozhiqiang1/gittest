package com.fongwell.satchi.crm.core.order.event;

import com.fongwell.infrastructure.event.AbstractDomainEvent;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by docker on 5/11/18.
 */
public class OrderPaidEvent extends AbstractDomainEvent {


    private long orderId;

    private long customerId;

    private BigDecimal price;

    private BigDecimal salePrice;

    private Collection<OrderEventItem> items;

    public OrderPaidEvent(final long orderId, final long customerId, final BigDecimal price, final BigDecimal salePrice, final Collection<OrderEventItem> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.price = price;
        this.salePrice = salePrice;
        this.items = items;
    }

    OrderPaidEvent() {
    }

    public long getCustomerId() {
        return customerId;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public BigDecimal getPrice() {
        return price;
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
