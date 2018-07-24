package com.fongwell.satchi.crm.core.order.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by docker on 4/24/18.
 */
@Entity
@Table(name = "crm_order_payment", indexes = @Index(name = "crm_order_payment_order_id_idx", columnList = "order_id"))
public class OrderPayment implements Serializable {
    @Id
    private String transactionId;

    @Column(name = "order_id")
    private long orderId;
    private String gatewayType;


    private BigDecimal amount;


    private Date paymentDate;

    public OrderPayment(final String transactionId, final long orderId,
                        final String gatewayType, final BigDecimal amount, final Date paymentDate) {
        this.transactionId = transactionId;
        this.orderId = orderId;
        this.gatewayType = gatewayType;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    OrderPayment() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(final long orderId) {
        this.orderId = orderId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(final String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public String getGatewayType() {
        return gatewayType;
    }

    public void setGatewayType(final String gatewayType) {
        this.gatewayType = gatewayType;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(final Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
