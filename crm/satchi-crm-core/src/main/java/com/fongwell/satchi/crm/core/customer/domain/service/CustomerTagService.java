package com.fongwell.satchi.crm.core.customer.domain.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

/**
 * Created by docker on 5/17/18.
 */
@Transactional
public interface CustomerTagService {

    void saveTags(long customerId, Set<String> tags);
}
