package com.fongwell.satchi.crm.core.store.domain.aggregate.query;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Map;

/**
 * Created by roman on 18-3-30.
 */
@Mapper
public interface AdminStoreImageQueryMapper {

    Collection<Map> listStoreImage(@Param("from") int from , @Param("size") int size);

    int countStoreImage();

    Map get(@Param("id") long id);

    /**
     * 根据用户id查询所有的商店详情图片
     * @param storeid
     * @return
     */
    Collection<Map> findByIdStoreImageList(@Param("storeid") Long storeid);
}
