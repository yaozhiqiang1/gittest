package com.fongwell.satchi.crm.core.credit.domain.entity;

import com.fongwell.satchi.crm.core.credit.domain.value.CreditSource;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by docker on 5/7/18.
 */
@Entity
@DiscriminatorValue("PURCHASE")
public class PurchaseConfiguration extends CreditSourceConfiguration {

    public PurchaseConfiguration(long parentId) {
        super(parentId, CreditSource.PURCHASE);

    }

    PurchaseConfiguration() {
    }
}
