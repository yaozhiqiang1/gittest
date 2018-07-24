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
    private String source; //来源
    @Column(name = "createdDate", columnDefinition = "date")
    private Date createdDate; //创建时间

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "customerid")
    private Long customerid;

    @Column(name = "orderid")
    private Long orderid;

    private BigDecimal price;

    private int credits;


    @Column(name = "type", length = 10)
    @Enumerated(EnumType.STRING)
    private CreditType type;

    public CustomerCreditRecord(final long customerId, final Long orderid, final BigDecimal price, final int credits, final CreditType type, final String source) {
        this.customerId = customerId;
        this.orderid = orderid;
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

    public Long getOrderid() {
        return orderid;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public CreditType getType() {
        return type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setType(CreditType type) {
        this.type = type;
    }
}
