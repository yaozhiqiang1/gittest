package com.fongwell.satchi.crm.core.product.query;

import com.fongwell.satchi.crm.core.product.dto.SkuInfo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by docker on 4/23/18.
 */
@Repository("productInfoQueryRepository")
public class ProductInfoQueryRepositoryImpl implements ProductInfoQueryRepository {

    @Resource(name = "productInfoQueryMapper")
    private ProductInfoQueryMapper productInfoQueryMapper;

    @Override
    public Collection<SkuInfo> queryActiveSkuInfos(final Collection<Long> skuIds) {
        return productInfoQueryMapper.queryActiveSkuInfos(skuIds);
    }
}
