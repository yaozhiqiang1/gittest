package com.fongwell.satchi.crm.core.order.domain.aggregate;

import com.fongwell.satchi.crm.core.order.domain.entity.OrderHistoryItem;
import com.fongwell.satchi.crm.core.support.id.Snowflake;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by docker on 5/18/18.
 */
@Entity
@Table(name = "crm_order_history", indexes = @Index(name = "crm_order_history_customer_id_idx", columnList = "customer_id"))
public class OrderHistory implements Serializable {

    @Id
    private Long id;

    private BigDecimal price;

    private Integer amount;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "store_id")
    private Long storeId;

    private String source;

    private BigDecimal discount;

    private BigDecimal originalPrice;

    private String paymentGatewayType;

    private Integer credits;


    private Date createdDate;

    @Version
    private Integer version;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "historyId")
    private Collection<OrderHistoryItem> items;

    public OrderHistory(final BigDecimal price, final Integer amount, final Long orderId, final Long customerId, final Long storeId, final String source) {
        this.price = price;
        this.amount = amount;
        this.orderId = orderId;
        this.customerId = customerId;
        this.storeId = storeId;
        this.source = source;
        id = Snowflake.id();
        this.createdDate = new Date();
        items = new LinkedList<>();
    }

    OrderHistory() {
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(final BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(final BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPaymentGatewayType() {
        return paymentGatewayType;
    }

    public void setPaymentGatewayType(final String paymentGatewayType) {
        this.paymentGatewayType = paymentGatewayType;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(final Integer credits) {
        this.credits = credits;
    }

    public Collection<OrderHistoryItem> getItems() {
        return items;
    }

    public Long getId() {
        return id;
    }
}
