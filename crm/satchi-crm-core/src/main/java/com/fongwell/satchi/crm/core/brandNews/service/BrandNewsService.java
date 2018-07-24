package com.fongwell.satchi.crm.core.brandNews.service;

import com.fongwell.satchi.crm.core.brandNews.domain.aggregate.BrandNews;
import com.fongwell.satchi.crm.core.brandNews.dto.BrandNewsData;
import com.fongwell.satchi.crm.core.common.WriteService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Created by roman on 18-3-23.
 */
@Transactional
public interface BrandNewsService extends WriteService<BrandNews,Long,BrandNewsData>{

    void onCreate(BrandNewsData data);

    void onSort(Long id,int orderNumber);

    void onUpdate(long id,BrandNewsData data);

    void onDelete(long id);

    void onEnable(Collection<Long> ids);

    void onDisable(Collection<Long> ids);
}
