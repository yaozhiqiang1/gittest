package com.fongwell.satchi.crm.core.store.service;

import com.fongwell.satchi.crm.core.common.WriteService;
import com.fongwell.satchi.crm.core.store.domain.aggregate.Store;
import com.fongwell.satchi.crm.core.store.dto.StoreData;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by roman on 18-3-30.
 */
@Transactional
public interface StoreService extends WriteService<Store, Long, StoreData> {

    void disable(Collection<Long> ids);

    void enable(Collection<Long> ids);

    void delete(Collection<Long> ids);
}
