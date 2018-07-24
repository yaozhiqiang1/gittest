package com.fongwell.satchi.crm.core.credit.query.mapper;

import com.fongwell.satchi.crm.core.credit.domain.entity.CreditSourceConfiguration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Date:2018/7/6
 * Author: yaozhiqiang
 * Desc:
 */
@Mapper
public interface CreditSourceConfigurationMapper {
    void saveCreditSourceConfiguration(CreditSourceConfiguration creditSourceConfiguration);

    void updateCreditSourceConfiguration(CreditSourceConfiguration creditSourceConfiguration);

    /**
     * 根据类别查询积分来源
     * @param type
     * @return
     */
    CreditSourceConfiguration queryByType(@Param("type") String type);
}
