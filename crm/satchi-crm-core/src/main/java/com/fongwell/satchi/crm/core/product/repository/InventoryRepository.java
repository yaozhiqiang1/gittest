package com.fongwell.satchi.crm.core.product.repository;

import com.fongwell.satchi.crm.core.product.domain.aggregate.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Collection;

/**
 * Created by docker on 4/20/18.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query(value = "select v from Inventory v where sku in :skuIds")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Collection<Inventory> findAndLockBySkuIds(@Param("skuIds") Collection<Long> skuIds);
}
