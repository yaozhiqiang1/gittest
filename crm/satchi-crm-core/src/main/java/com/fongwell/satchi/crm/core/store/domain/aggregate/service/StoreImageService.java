package com.fongwell.satchi.crm.core.store.domain.aggregate.service;

import com.fongwell.satchi.crm.core.common.WriteService;
import com.fongwell.satchi.crm.core.store.domain.aggregate.dto.StoreImageData;
import com.fongwell.satchi.crm.core.store.domain.aggregate.entity.StoreImage;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by roman on 18-3-30.
 */
@Transactional
public interface StoreImageService extends WriteService<StoreImage,Long,StoreImageData>{

    void specify(long id,Long storeId);

}
