package com.fongwell.satchi.crm.core.product.query;

import com.fongwell.satchi.crm.core.common.query.RequestData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Map;

/**
 * Created by roman on 18-4-3.
 */
@Mapper
public interface AdminProductQueryMapper {

    Collection<Map> findAll(@Param("request")RequestData request);

    int count(@Param("request")RequestData request);

    Map get(@Param("id") Long id);
}
