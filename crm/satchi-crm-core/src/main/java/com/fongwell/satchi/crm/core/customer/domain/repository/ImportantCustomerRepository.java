package com.fongwell.satchi.crm.core.customer.domain.repository;

import com.fongwell.satchi.crm.core.customer.domain.aggregate.ImportantCustomer;
import com.fongwell.satchi.crm.core.customer.domain.entity.ImportantCustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 5/24/18.
 */
@Repository
public interface ImportantCustomerRepository extends JpaRepository<ImportantCustomer, ImportantCustomerId> {
}
