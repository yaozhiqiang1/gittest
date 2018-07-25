package com.fongwell.satchi.crm.core.store.query;

import com.fongwell.satchi.crm.core.store.domain.aggregate.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Map;

/**
 * Created by roman on 18-3-30.
 */
@Mapper
public interface AdminStoreQueryMapper {

    Collection<Map> listStore(@Param("from") int from,@Param("size") int size);

    int countStore();

    Map get(@Param("id")long id);

    Collection<Map> addressGetStoreList(@Param("storeAddress")String storeAddress);

    Collection<Map> findAllStoreList();
}
