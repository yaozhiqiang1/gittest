package com.fongwell.satchi.crm.core.credit.factory;

import com.fongwell.satchi.crm.core.credit.domain.entity.CheckinConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.entity.CreditSourceConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.value.CreditSource;
import com.fongwell.satchi.crm.core.credit.dto.CreditSourceConfigurationDto;
import org.springframework.stereotype.Component;

/**
 * Created by docker on 5/7/18.
 */
@Component
public class CheckinConfigurationFactory implements CreditSourceConfigurationFactory {

    @Override
    public CreditSourceConfiguration create(long parentId, final CreditSourceConfigurationDto data) {

        CheckinConfiguration config = new CheckinConfiguration(parentId);
        config.setCredit(data.getCredit());
        config.setEnabled(data.getEnabled());
        return config;
    }

    @Override
    public void merge(final CreditSourceConfiguration configuration, final CreditSourceConfigurationDto data) {
        configuration.setCredit(data.getCredit());
        configuration.setEnabled(data.getEnabled());
    }

    @Override
    public String getType() {
        return CreditSource.CHECKIN.getType();
    }
}
