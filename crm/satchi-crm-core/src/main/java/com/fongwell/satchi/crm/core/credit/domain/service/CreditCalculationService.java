package com.fongwell.satchi.crm.core.credit.domain.service;

import com.fongwell.satchi.crm.core.credit.domain.value.CreditSource;

import java.math.BigDecimal;

/**
 * Created by docker on 5/22/18.
 */
public interface CreditCalculationService {

    Integer calculateCredits(CreditSource source, final long customerId, Object data);
}
