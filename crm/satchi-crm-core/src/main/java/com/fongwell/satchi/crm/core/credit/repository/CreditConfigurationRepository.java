package com.fongwell.satchi.crm.core.credit.repository;

import com.fongwell.satchi.crm.core.credit.domain.aggregate.CreditConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 5/7/18.
 */
@Repository
public interface CreditConfigurationRepository extends JpaRepository<CreditConfiguration, Long> {
}
