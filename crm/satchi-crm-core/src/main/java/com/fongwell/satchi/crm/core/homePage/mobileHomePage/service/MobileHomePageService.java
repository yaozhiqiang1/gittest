package com.fongwell.satchi.crm.core.homePage.mobileHomePage.service;

import com.fongwell.satchi.crm.core.common.WriteService;
import com.fongwell.satchi.crm.core.homePage.mobileHomePage.domain.aggregate.MobileHomePage;
import com.fongwell.satchi.crm.core.homePage.mobileHomePage.domain.dto.MobileHomePageData;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by roman on 18-4-13.
 */
@Transactional
public interface MobileHomePageService extends WriteService<MobileHomePage,Long,MobileHomePageData>{

    void onCreate(MobileHomePageData data);

    void onUpdate(Long id,MobileHomePageData data);

    void onDisable(Collection<Long> ids);

    void onEnable(Collection<Long> ids);

    void onDelete(Collection<Long> ids);

    void onSort(Long id,int orderNumber);


}
