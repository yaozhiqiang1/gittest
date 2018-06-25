package com.fongwell.satchi.crm.core.store.repository;

import com.fongwell.satchi.crm.core.store.domain.aggregate.Store;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by roman on 18-3-30.
 */
public interface StoreRepository extends JpaRepository<Store,Long> {
}
