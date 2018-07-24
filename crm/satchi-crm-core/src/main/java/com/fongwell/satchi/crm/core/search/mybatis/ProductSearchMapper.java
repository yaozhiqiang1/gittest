package com.fongwell.satchi.crm.core.search.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 4/23/18.
 */
@Mapper
public interface ProductSearchMapper {


    Collection<Map> searchProductsForBookmarks(@Param("ids") final Collection<Long> ids,
                                               @Param("from") final int from, @Param("size") final int size);
}
