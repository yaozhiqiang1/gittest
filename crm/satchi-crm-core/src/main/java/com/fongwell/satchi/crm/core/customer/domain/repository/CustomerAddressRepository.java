package com.fongwell.satchi.crm.core.customer.domain.repository;

import com.fongwell.satchi.crm.core.customer.domain.aggregate.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 4/19/18.
 */
@Repository
public interface CustomerAddressRepository extends JpaRepository<Addresses, Long> {
}
