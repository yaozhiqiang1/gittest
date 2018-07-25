package com.fongwell.satchi.crm.core.store.service;

import com.fongwell.satchi.crm.core.common.AbstractWriteService;
import com.fongwell.satchi.crm.core.store.domain.aggregate.Store;
import com.fongwell.satchi.crm.core.store.domain.aggregate.entity.StoreImage;
import com.fongwell.satchi.crm.core.store.dto.StoreData;
import com.fongwell.satchi.crm.core.store.query.AdminStoreQueryMapper;
import com.fongwell.satchi.crm.core.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by roman on 18-3-30.
 */
@Service("storeService")
public class StoreServiceImpl extends AbstractWriteService<Store, Long, StoreData> implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private AdminStoreQueryMapper adminStoreQueryMapper;

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

    /**
     * 根据地址查询所有的门店,包括门店的信息
     */
    @Override
    public Collection<Map> addressGetStoreList(String storeAddress) {
        Collection<Map> maps = adminStoreQueryMapper.addressGetStoreList(storeAddress);
        return maps;
    }

    /**
     * 查询门店所有的省份
     * @return
     */
    @Override
    public Collection<Map> findAllStoreList() {

        return adminStoreQueryMapper.findAllStoreList();
    }

}
