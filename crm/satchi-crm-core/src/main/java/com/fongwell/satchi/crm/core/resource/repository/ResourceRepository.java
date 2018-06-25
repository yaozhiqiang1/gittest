package com.fongwell.satchi.crm.core.resource.repository;

import com.fongwell.satchi.crm.core.resource.domain.aggregate.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 4/24/18.
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
