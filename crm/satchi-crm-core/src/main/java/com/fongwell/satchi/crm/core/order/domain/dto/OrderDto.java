package com.fongwell.satchi.crm.core.order.domain.dto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * Created by roman on 18-3-23.
 */
public class OrderDto {

    private long id;

    private long storeId;

    private long customerId;

    private String type;

    private BigDecimal totalPrice;

    private int amount;

    private Date payDate;

    private Date orderDate;

    private Collection<OrderItemDto> items;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Collection<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(Collection<OrderItemDto> items) {
        this.items = items;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
