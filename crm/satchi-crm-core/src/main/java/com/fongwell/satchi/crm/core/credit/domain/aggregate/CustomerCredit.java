package com.fongwell.satchi.crm.core.credit.domain.aggregate;

import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by docker on 5/8/18.
 */

@Entity
@Table(name = "crm_customer_credit")
public class CustomerCredit extends AbstractAggregateRoot {

    private Integer credits;

    private long customerId;

    @Column(name = "checkinDate",nullable = true)
    private Date checkinDate;

    public CustomerCredit(final long customerId, final Integer credits) {
        this.credits = credits;
        this.id = customerId;
    }

    CustomerCredit() {
    }


    public Integer getCredits() {
        return credits;
    }

    public Integer increment(Integer value) {
        if (credits == null) {
            credits = 0;
        }
        if (value != null) {
            credits += value;

        }
        return credits;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }
}
