package com.fongwell.satchi.crm.core.store.service;

import com.fongwell.satchi.crm.core.common.WriteService;
import com.fongwell.satchi.crm.core.store.domain.aggregate.Store;
import com.fongwell.satchi.crm.core.store.domain.aggregate.entity.StoreImage;
import com.fongwell.satchi.crm.core.store.dto.StoreData;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by roman on 18-3-30.
 */
@Transactional
public interface StoreService extends WriteService<Store, Long, StoreData> {

    void disable(Collection<Long> ids);

    void enable(Collection<Long> ids);

    void delete(Collection<Long> ids);

    /**
     * 根据地址查询所有的门店,包括门店的信息
     * @return
     */
    Collection<Map> addressGetStoreList(String storeAddress);

    /**
     * 查询门店所有的省份
     * @return
     */
    Collection<Map> findAllStoreList();
}
