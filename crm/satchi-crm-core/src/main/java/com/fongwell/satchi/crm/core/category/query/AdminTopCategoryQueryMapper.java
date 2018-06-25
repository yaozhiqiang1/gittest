package com.fongwell.satchi.crm.core.category.query;

import com.fongwell.satchi.crm.core.common.query.RequestData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Map;

/**
 * Created by roman on 18-4-16.
 */
@Mapper
public interface AdminTopCategoryQueryMapper {

    Collection<Map> findAll(@Param("request")RequestData request);

    int count(@Param("request")RequestData request);

    Map get(@Param("id") Long id);
}
