package com.fongwell.satchi.crm.core.credit.domain.entity;

import com.fongwell.satchi.crm.core.credit.domain.value.CreditSource;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by docker on 5/7/18.
 */
@Entity
@DiscriminatorValue("CHECKIN")
public class CheckinConfiguration extends CreditSourceConfiguration {

    public CheckinConfiguration(long parentId) {
        super(parentId, CreditSource.CHECKIN);

    }
    CheckinConfiguration(){}

}
