package com.fongwell.satchi.crm.core.series.query;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 3/28/18.
 */
@Mapper
public interface AdminSeriesQueryMapper {

    Collection<Map> listSeries(@Param("from") int from, @Param("size") int size);

    int countSeries();

    Map getSeries(@Param("id") long id);
}
