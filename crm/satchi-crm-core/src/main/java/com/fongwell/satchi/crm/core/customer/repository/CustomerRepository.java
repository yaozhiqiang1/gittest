package com.fongwell.satchi.crm.core.customer.repository;

import com.fongwell.satchi.crm.core.customer.domain.aggregate.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 2/25/18.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByMobile(String mobile);
}
