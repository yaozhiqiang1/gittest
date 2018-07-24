package com.fongwell.satchi.crm.core.pricing.query;

import com.fongwell.satchi.crm.core.pricing.query.dto.SkuPriceItem;
import com.fongwell.satchi.crm.core.pricing.query.mapper.SkuPricingQueryMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by docker on 4/23/18.
 */
@Repository("skuPricingQueryRepository")
public class SkuPricingQueryRepositoryImpl implements SkuPricingQueryRepository {

    @Resource(name = "skuPricingQueryMapper")
    private SkuPricingQueryMapper skuPricingQueryMapper;

    @Override
    public Collection<SkuPriceItem> queryItems(final Collection<Long> skuIds) {

        if (skuIds.isEmpty()) {
            return Collections.emptyList();
        }

        return skuPricingQueryMapper.queryItems(skuIds);


    }
}
