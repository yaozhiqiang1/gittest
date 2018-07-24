package com.fongwell.satchi.crm.core.credit.factory;

import com.fongwell.satchi.crm.core.credit.domain.entity.CreditSourceConfiguration;
import com.fongwell.satchi.crm.core.credit.dto.CreditSourceConfigurationDto;

/**
 * Created by docker on 5/7/18.
 */
public interface CreditSourceConfigurationFactory {

    CreditSourceConfiguration create(long parentId, CreditSourceConfigurationDto data);

    void merge(CreditSourceConfiguration configuration, CreditSourceConfigurationDto data);

    String getType();
}
