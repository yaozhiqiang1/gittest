package com.fongwell.satchi.crm.core.homePage.pcHomePage.service;

import com.fongwell.satchi.crm.core.common.WriteService;
import com.fongwell.satchi.crm.core.homePage.pcHomePage.domain.aggregate.PcHomePage;
import com.fongwell.satchi.crm.core.homePage.pcHomePage.domain.dto.PcHomePageData;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Created by roman on 18-3-23.
 */
@Transactional
public interface PcHomePageService extends WriteService<PcHomePage,Long,PcHomePageData>{

    void onCreate(PcHomePageData data);

    void onUpdate(Long id, PcHomePageData data);

    void onDelete(Collection<Long> ids);

    void onDisable(Collection<Long> ids);

    void onEnable(Collection<Long> ids);

    void onSort(Long id,int orderNumber);
}
