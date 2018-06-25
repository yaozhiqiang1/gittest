package com.fongwell.satchi.crm.core.credit.repository;

import com.fongwell.satchi.crm.core.credit.domain.aggregate.CustomerCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 5/22/18.
 */
@Repository
public interface CustomerCreditReposistory extends JpaRepository<CustomerCredit, Long> {
}
