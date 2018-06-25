package com.fongwell.satchi.crm.core.order.query.dto;

import com.fongwell.satchi.crm.core.customer.domain.value.AddressValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * Created by docker on 4/23/18.
 */
public class OrderDetail implements Serializable {

    private long id;

    private String state;

    private Collection<OrderDetailItem> items;

    private AddressValue shippingAddress;

    private BigDecimal price;

    private BigDecimal salePrice;

    private Date createdDate;

    private BigDecimal totalPaid;

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(final BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public Collection<OrderDetailItem> getItems() {
        return items;
    }

    public void setItems(final Collection<OrderDetailItem> items) {
        this.items = items;
    }

    public AddressValue getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(final AddressValue shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(final BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
}
