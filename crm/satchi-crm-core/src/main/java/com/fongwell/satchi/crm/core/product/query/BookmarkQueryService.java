package com.fongwell.satchi.crm.core.product.query;

import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 4/23/18.
 */
public interface BookmarkQueryService {

    Collection<Map> queryBookmarkProducts(long customerId, int from, int size);

    boolean isBookmarked(long customerId, long productId);

    Collection<Long> checkBookmarked(long customerId, Collection<Long> productIds);
}
