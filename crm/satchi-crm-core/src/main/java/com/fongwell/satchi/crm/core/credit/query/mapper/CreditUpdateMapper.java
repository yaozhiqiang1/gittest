package com.fongwell.satchi.crm.core.credit.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Date:2018/7/12
 * Author: yaozhiqiang
 * Desc:
 */
@Mapper
public interface CreditUpdateMapper {

    /**
     * 根据id更新积分的值
     * @param id
     * @param credits
     */
    void creditUpdate(@Param("id") Long id, @Param("credits") Integer credits);
}
