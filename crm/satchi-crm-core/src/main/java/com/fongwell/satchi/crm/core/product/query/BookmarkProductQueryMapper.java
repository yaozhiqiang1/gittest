package com.fongwell.satchi.crm.core.product.query;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * Created by docker on 4/23/18.
 */
@Mapper
public interface BookmarkProductQueryMapper {

    Collection<Long> queryProductIds(@Param("customerId") long customerId, @Param("from") int from, @Param("size") int size);

    boolean isBookmarked(@Param("customerId") final long customerId, @Param("productId") final long productId);

    Collection<Long> checkBookmarked(@Param("customerId") final long customerId, @Param("productIds") Collection<Long> productIds);
}
