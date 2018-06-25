package com.fongwell.satchi.crm.core.store.service;

import com.fongwell.satchi.crm.core.common.AbstractWriteService;
import com.fongwell.satchi.crm.core.store.domain.aggregate.Store;
import com.fongwell.satchi.crm.core.store.dto.StoreData;
import com.fongwell.satchi.crm.core.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by roman on 18-3-30.
 */
@Service("storeService")
public class StoreServiceImpl extends AbstractWriteService<Store, Long, StoreData> implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    protected StoreServiceImpl() {
        super(Store.class);
    }

    @Override
    protected JpaRepository<Store, Long> getRepository() {
        return storeRepository;
    }

    @Override
    public void disable(Collection<Long> ids) {
        toggleEnable(ids, false);

    }

    private void toggleEnable(Collection<Long> ids, boolean enable) {

        List<Store> stores = storeRepository.findAll(ids);

        Collection<Store> updated = new ArrayList<>(stores.size());
        for (final Store store : stores) {

            if (enable && store.enable()) {

                updated.add(store);

            } else if (store.disble()) {

                updated.add(store);

            }


        }

        if (!updated.isEmpty()) {
            storeRepository.save(updated);
        }

    }

    @Override
    public void enable(Collection<Long> ids) {
        toggleEnable(ids, true);

    }

    @Override
    public void delete(final Collection<Long> ids) {
        List<Store> stores = storeRepository.findAll(ids);

        for (final Store store : stores) {
            storeRepository.delete(store);
        }

    }
}
