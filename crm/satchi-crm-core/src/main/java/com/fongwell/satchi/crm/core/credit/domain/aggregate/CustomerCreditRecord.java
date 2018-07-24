package com.fongwell.satchi.crm.core.credit.domain.aggregate;

import com.fongwell.satchi.crm.core.credit.domain.value.CreditType;
import com.fongwell.satchi.crm.core.support.id.Snowflake;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by docker on 3/19/18.
 */
@Entity
@Table(name = "crm_customer_credit_record", indexes = {@Index(name = "crm_customer_credit_record_customerid_idx", columnList = "customer_id")})
public class CustomerCreditRecord {

    @Id
    private long id;

    @Column(name = "source", length = 30)
    private String source;
    @Column(name = "createdDate", columnDefinition = "date")
    private Date createdDate;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "orderId")
    private Long orderId;

    private BigDecimal price;

    private int credits;


    @Column(name = "type", length = 10)
    @Enumerated(EnumType.STRING)
    private CreditType type;

    public CustomerCreditRecord(final long customerId, final Long orderId, final BigDecimal price, final int credits, final CreditType type, final String source) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.price = price;
        this.credits = credits;
        this.type = type;
        this.source = source;
        this.id = Snowflake.id();
        this.createdDate = new Date();
    }

    CustomerCreditRecord() {
    }

    public int getCredits() {
        return credits;
    }

    public long getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public long getCustomerId() {
        return customerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public CreditType getType() {
        return type;
    }


}
