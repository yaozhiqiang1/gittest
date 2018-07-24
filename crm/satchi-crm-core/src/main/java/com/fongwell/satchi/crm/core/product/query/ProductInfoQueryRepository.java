package com.fongwell.satchi.crm.core.product.query;

import com.fongwell.satchi.crm.core.product.dto.SkuInfo;

import java.util.Collection;

/**
 * Created by docker on 4/23/18.
 */
public interface ProductInfoQueryRepository {

    Collection<SkuInfo> queryActiveSkuInfos(Collection<Long> skuIds);
}
