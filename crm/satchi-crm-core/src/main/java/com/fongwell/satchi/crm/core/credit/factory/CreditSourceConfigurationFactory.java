package com.fongwell.satchi.crm.core.credit.factory;

import com.fongwell.satchi.crm.core.credit.domain.entity.CreditSourceConfiguration;
import com.fongwell.satchi.crm.core.credit.dto.CreditSourceConfigurationDto;

/**
 * Created by docker on 5/7/18.
 */
public interface CreditSourceConfigurationFactory {

    /**
     *  参数封装到CreditSourceConfiguration 实体类
     * @param parentId
     * @param data  封装着 CreditSourceConfiguration 实体类的一些数据 credit,enabled,type
     * @return  CreditSourceConfiguration
     */
    CreditSourceConfiguration create(long parentId, CreditSourceConfigurationDto data);

    void merge(CreditSourceConfiguration configuration, CreditSourceConfigurationDto data);

    String getType();
}
