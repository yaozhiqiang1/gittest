package com.fongwell.satchi.crm.core.credit.query.mapper;

import com.fongwell.satchi.crm.core.credit.domain.entity.CreditConsumeConfiguration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Date:2018/7/13
 * Author: yaozhiqiang
 * Desc:
 */
@Mapper
public interface CreditConsumeConfigurationMapper {


    CreditConsumeConfiguration queryCreditConsumeConfiguration(@Param("id") Long id);
}