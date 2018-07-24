package com.fongwell.satchi.crm.core.store.domain.aggregate.service;

import com.fongwell.satchi.crm.core.common.AbstractWriteService;
import com.fongwell.satchi.crm.core.store.domain.aggregate.dto.StoreImageData;
import com.fongwell.satchi.crm.core.store.domain.aggregate.entity.StoreImage;
import com.fongwell.satchi.crm.core.store.domain.aggregate.repository.StoreImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Created by roman on 18-3-30.
 */
@Service("storeImageService")
public class StoreImageServiceImpl extends AbstractWriteService<StoreImage,Long,StoreImageData> implements StoreImageService {

    @Autowired
    private StoreImageRepository storeImageRepository;

    protected StoreImageServiceImpl() {
        super(StoreImage.class);
    }

    @Override
    protected JpaRepository<StoreImage, Long> getRepository() {
        return storeImageRepository;
    }

    @Override
    public void specify(long id, Long storeId) {
        StoreImage one = storeImageRepository.findOne(id);
        if(one != null){
            one.specify(storeId);
            storeImageRepository.save(one);
        }
    }
}
