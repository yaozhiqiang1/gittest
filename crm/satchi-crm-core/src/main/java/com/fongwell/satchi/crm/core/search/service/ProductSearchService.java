package com.fongwell.satchi.crm.core.search.service;

import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 4/23/18.
 */
public interface ProductSearchService {

    Collection<Map> searchProductsForBookmarks(Collection<Long> ids, int from, int size);
}
