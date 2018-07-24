package com.fongwell.satchi.crm.core.product.repository;

import com.fongwell.satchi.crm.core.product.domain.aggregate.entity.InventoryAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 4/20/18.
 */
@Repository
public interface InventoryAuditRepository extends JpaRepository<InventoryAudit, Long> {


}
