package com.fongwell.satchi.crm.core.order.domain.aggregate;

import com.fongwell.satchi.crm.core.customer.domain.value.AddressValue;
import com.fongwell.satchi.crm.core.order.domain.entity.OrderItem;
import com.fongwell.satchi.crm.core.order.domain.entity.OrderPayment;
import com.fongwell.satchi.crm.core.order.domain.value.OrderState;
import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by docker on 4/19/18.
 */
@Entity
@Table(name = "crm_order", indexes = @Index(name = "crm_order_customer_id_idx", columnList = "customer_id"))
public class Order extends AbstractAggregateRoot {

    @Column(name = "customer_id")
    private long customerId;

    @OneToMany(mappedBy = "orderId", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private Collection<OrderItem> items;

    private BigDecimal price;

    private BigDecimal salePrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", length = 20)
    private OrderState state;

    @OrderColumn(name = "paymentDate")
    @OneToMany(mappedBy = "orderId",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true)
    private Collection<OrderPayment> payments;

    private BigDecimal totalPaid;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "province", column = @Column(name = "shipping_province")),
            @AttributeOverride(name = "city", column = @Column(name = "shipping_city")),
            @AttributeOverride(name = "district", column = @Column(name = "shipping_district")),
            @AttributeOverride(name = "address", column = @Column(name = "shipping_address")),
            @AttributeOverride(name = "postcode", column = @Column(name = "shipping_postcode")),
            @AttributeOverride(name = "contactNumber", column = @Column(name = "shipping_contactNumber")),
            @AttributeOverride(name = "receiver", column = @Column(name = "shipping_receiver"))
    })
    private AddressValue shippingAddress;

    private String paymentGatewayType;


    public Order(final long customerId, final Collection<OrderItem> items, final BigDecimal price, final BigDecimal salePrice, String paymentGatewayType) {
        this.items = items;
        this.price = price;
        this.salePrice = salePrice;
        this.customerId = customerId;
        this.paymentGatewayType = paymentGatewayType;
        this.state = OrderState.pending;
    }

    Order() {
    }

    public void cancel() {

        if (state != OrderState.pending) {
            throw new IllegalStateException("Only pending orders can be cancelled!");
        }
        state = OrderState.cancelled;
    }

    public void complete() {
        if (state != OrderState.shipped) {
            throw new IllegalStateException("Only shipped orders can be completed!");
        }

        state = OrderState.completed;
    }

    public void ship() {
        if (state != OrderState.to_ship) {
            throw new IllegalStateException("Invalid state for shipping");
        }


        state = OrderState.shipped;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(final BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void addPayment(OrderPayment payment) {

        if (payments == null) {
            payments = new LinkedList<>();
        }
        paymentGatewayType = payment.getGatewayType();
        payments.add(payment);
        if (totalPaid == null) {
            totalPaid = BigDecimal.ZERO;
        }

        for (final OrderPayment paymentItem : payments) {
            totalPaid = totalPaid.add(paymentItem.getAmount());
        }
    }


    public void setState(final OrderState state) {
        this.state = state;
    }

    public OrderState getState() {
        return state;
    }

    public long getCustomerId() {
        return customerId;
    }

    public Collection<OrderItem> getItems() {
        return items;
    }

    public AddressValue getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(final AddressValue shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void setItems(Collection<OrderItem> items) {
        this.items = items;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Collection<OrderPayment> getPayments() {
        return payments;
    }

    public void setPayments(Collection<OrderPayment> payments) {
        this.payments = payments;
    }

    public String getPaymentGatewayType() {
        return paymentGatewayType;
    }

    public void setPaymentGatewayType(String paymentGatewayType) {
        this.paymentGatewayType = paymentGatewayType;
    }
}
