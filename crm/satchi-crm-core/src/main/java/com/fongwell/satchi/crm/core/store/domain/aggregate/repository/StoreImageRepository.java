package com.fongwell.satchi.crm.core.store.domain.aggregate.repository;

import com.fongwell.satchi.crm.core.store.domain.aggregate.entity.StoreImage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by roman on 18-3-30.
 */
public interface StoreImageRepository extends JpaRepository<StoreImage,Long>{
}
