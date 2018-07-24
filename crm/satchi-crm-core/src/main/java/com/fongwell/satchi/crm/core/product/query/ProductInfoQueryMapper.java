package com.fongwell.satchi.crm.core.product.query;

import com.fongwell.satchi.crm.core.product.dto.SkuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * Created by docker on 4/23/18.
 */
@Mapper
public interface ProductInfoQueryMapper {

    Collection<SkuInfo> queryActiveSkuInfos(@Param("skuIds") Collection<Long> skuIds);
}

