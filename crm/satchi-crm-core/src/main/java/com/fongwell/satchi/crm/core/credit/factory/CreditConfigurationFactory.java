package com.fongwell.satchi.crm.core.credit.factory;

import com.fongwell.satchi.crm.core.credit.domain.aggregate.CreditConfiguration;
import com.fongwell.satchi.crm.core.credit.dto.CreditConfigurationDto;

/**
 * Created by docker on 5/7/18.
 */
public interface CreditConfigurationFactory {

    CreditConfiguration create(long id, CreditConfigurationDto data);

    void merge(CreditConfiguration configuration, CreditConfigurationDto data);
}
