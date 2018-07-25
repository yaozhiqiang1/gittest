package com.fongwell.satchi.crm.core.credit.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by docker on 5/7/18.
 */
@Mapper
public interface CreditConfigurationQueryMapper {

    Map queryConfiguration(@Param("id") long id);

    Map queryCreditConfigurationExpiration(@Param("id") Long id);
}
