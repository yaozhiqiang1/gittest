package com.fongwell.satchi.crm.core.credit.factory;

import com.fongwell.satchi.crm.core.credit.domain.entity.CreditSourceConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.entity.PurchaseConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.value.CreditSource;
import com.fongwell.satchi.crm.core.credit.dto.CreditSourceConfigurationDto;
import org.springframework.stereotype.Component;

/**
 * Created by docker on 5/7/18.
 */
@Component
public class PurchaseConfigurationFactory implements CreditSourceConfigurationFactory {

    /**
     *  参数封装到CreditSourceConfiguration 实体类
     * @param parentId
     * @param data  封装着 CreditSourceConfiguration 实体类的一些数据 credit,enabled,type
     * @return  CreditSourceConfiguration
     */
    @Override
    public CreditSourceConfiguration create(final long parentId, final CreditSourceConfigurationDto data) {


        PurchaseConfiguration config = new PurchaseConfiguration(parentId);
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
        return CreditSource.PURCHASE.getType();
    }
}
