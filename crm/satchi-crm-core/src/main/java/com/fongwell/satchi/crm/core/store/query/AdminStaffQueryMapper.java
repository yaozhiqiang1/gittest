package com.fongwell.satchi.crm.core.store.query;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 5/21/18.
 */
@Mapper
public interface AdminStaffQueryMapper {

    Collection<Map> queryStaffs(@Param("storeId") long storeId, @Param("from") int from, @Param("size") int size);


    int countStaffs(@Param("storeId") long storeId);

    Map getStaff(@Param("id") long id);
}


