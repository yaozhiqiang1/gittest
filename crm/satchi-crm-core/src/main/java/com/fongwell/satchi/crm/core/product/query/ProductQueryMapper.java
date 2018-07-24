package com.fongwell.satchi.crm.core.product.query;

import com.fongwell.satchi.crm.core.common.query.RequestData;
import com.fongwell.satchi.crm.core.product.domain.aggregate.entity.ProductSettings;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by roman on 18-4-3.
 */
@Mapper
public interface ProductQueryMapper {

    Collection<Map> findAll(@Param("request") RequestData request);

    int count(@Param("request") RequestData request);

    Map get(@Param("id") Long id);

    List<Map> queryProductSettings();

    ProductSettings queryByIdProductSettings(@Param("productId") long productId);
}
