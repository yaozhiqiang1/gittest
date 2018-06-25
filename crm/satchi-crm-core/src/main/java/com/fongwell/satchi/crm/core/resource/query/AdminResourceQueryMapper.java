package com.fongwell.satchi.crm.core.resource.query;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 4/24/18.
 */
@Mapper
public interface AdminResourceQueryMapper {

    Collection<Map> queryResources(@Param("type") String type, @Param("from") int from, @Param("size") int size);

    int countResources(@Param("type") String type);

}
