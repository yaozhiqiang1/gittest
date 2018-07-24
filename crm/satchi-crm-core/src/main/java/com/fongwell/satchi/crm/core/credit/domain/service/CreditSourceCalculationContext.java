package com.fongwell.satchi.crm.core.credit.domain.service;

import com.fongwell.satchi.crm.core.credit.domain.entity.CreditSourceConfiguration;

/**
 * Created by docker on 5/22/18.
 */
public class CreditSourceCalculationContext {

    private CreditSourceConfiguration configuration;

    private Long customerId;

    private Object data;

    public CreditSourceCalculationContext(final CreditSourceConfiguration configuration, final Long customerId, final Object data) {
        this.configuration = configuration;
        this.customerId = customerId;
        this.data = data;
    }

    public CreditSourceConfiguration getConfiguration() {
        return configuration;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Object getData() {
        return data;
    }
}
