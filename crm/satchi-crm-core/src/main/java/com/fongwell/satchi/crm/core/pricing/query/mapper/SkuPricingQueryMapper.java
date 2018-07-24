package com.fongwell.satchi.crm.core.pricing.query.mapper;

import com.fongwell.satchi.crm.core.pricing.query.dto.SkuPriceItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * Created by docker on 4/23/18.
 */
@Mapper
public interface SkuPricingQueryMapper {

    Collection<SkuPriceItem> queryItems(@Param("skuIds") Collection<Long> skuIds);

}
