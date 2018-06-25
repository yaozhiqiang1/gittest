package com.fongwell.satchi.crm.core.search.service;

import com.fongwell.satchi.crm.core.search.mybatis.ProductSearchMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 4/23/18.
 */
@Service("productSearchService")
public class MybatisProductSearchService implements ProductSearchService {

    @Resource(name = "productSearchMapper")
    private ProductSearchMapper productSearchMapper;


    @Override
    public Collection<Map> searchProductsForBookmarks(final Collection<Long> ids, final int from, final int size) {
        return productSearchMapper.searchProductsForBookmarks(ids, from, size);
    }
}
