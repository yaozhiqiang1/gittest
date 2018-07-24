package com.fongwell.satchi.crm.core.product.query;

import com.fongwell.satchi.crm.core.search.service.ProductSearchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Created by docker on 4/23/18.
 */
@Service("bookmarkQueryService")
public class BookmarkQueryServiceImpl implements BookmarkQueryService {

    @Resource(name = "bookmarkProductQueryMapper")
    private BookmarkProductQueryMapper bookmarkProductQueryMapper;

    @Resource(name = "productSearchService")
    private ProductSearchService productSearchService;


    @Override
    public Collection<Map> queryBookmarkProducts(final long customerId, final int from, final int size) {


        Collection<Long> ids = bookmarkProductQueryMapper.queryProductIds(customerId, from, size);
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }


        return productSearchService.searchProductsForBookmarks(ids, from, size);
    }

    @Override
    public boolean isBookmarked(final long customerId, final long productId) {
        return bookmarkProductQueryMapper.isBookmarked(customerId, productId);
    }

    @Override
    public Collection<Long> checkBookmarked(final long customerId, final Collection<Long> productIds) {
        return bookmarkProductQueryMapper.checkBookmarked(customerId, productIds);
    }
}
