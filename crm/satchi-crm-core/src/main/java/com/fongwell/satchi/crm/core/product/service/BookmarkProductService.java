package com.fongwell.satchi.crm.core.product.service;

/**
 * Created by docker on 4/23/18.
 */
public interface BookmarkProductService {

    void bookmark(long customerId, long productId);

    void unmark(long customerId, long productId);

}
