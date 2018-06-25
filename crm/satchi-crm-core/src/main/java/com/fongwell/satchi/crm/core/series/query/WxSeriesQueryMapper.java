package com.fongwell.satchi.crm.core.series.query;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Map;

/**
 * Created by roman on 18-4-23.
 */
@Mapper
public interface WxSeriesQueryMapper {

    Collection<Map> findAll(@Param("from") int from, @Param("size") int size);

    Map queryDetail(@Param("id") long id);

    int count();

}
