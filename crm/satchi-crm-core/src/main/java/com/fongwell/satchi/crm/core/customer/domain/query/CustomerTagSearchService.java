package com.fongwell.satchi.crm.core.customer.domain.query;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 5/18/18.
 */
@Transactional(readOnly = true)
public interface CustomerTagSearchService {

    Map<Long, Collection<String>> searchTags(Collection<Long> customerIds);

    Collection<Long> searchCustomersWithTags(Collection<String> tags);
}
