package com.fongwell.satchi.crm.core.credit.domain.aggregate;

import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by docker on 5/8/18.
 */

@Entity
@Table(name = "crm_customer_credit")
public class CustomerCredit extends AbstractAggregateRoot {

    private Integer credits;


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
}
