package com.fongwell.satchi.crm.core.credit.domain.service;

import com.fongwell.satchi.crm.core.credit.domain.aggregate.CreditConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.value.CreditSource;

/**
 * Created by docker on 5/22/18.
 */
public interface CreditSourceCalculationProvider {

    Integer calculateCredits(CreditSourceCalculationContext context);

    String getType();
}
