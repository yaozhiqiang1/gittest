package com.fongwell.satchi.crm.core.customer.domain.repository;

import com.fongwell.satchi.crm.core.customer.domain.aggregate.CustomerTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 5/17/18.
 */
@Repository
public interface CustomerTagsRepository extends JpaRepository<CustomerTags, Long> {
}
