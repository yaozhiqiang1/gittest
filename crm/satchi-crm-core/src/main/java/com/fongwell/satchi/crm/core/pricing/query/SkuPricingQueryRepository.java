package com.fongwell.satchi.crm.core.pricing.query;

import com.fongwell.satchi.crm.core.pricing.query.dto.SkuPriceItem;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * Created by docker on 4/23/18.
 */
public interface SkuPricingQueryRepository {

    Collection<SkuPriceItem> queryItems(Collection<Long> skuIds);
}
