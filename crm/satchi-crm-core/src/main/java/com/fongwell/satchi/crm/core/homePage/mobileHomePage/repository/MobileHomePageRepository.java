package com.fongwell.satchi.crm.core.homePage.mobileHomePage.repository;

import com.fongwell.satchi.crm.core.homePage.mobileHomePage.domain.aggregate.MobileHomePage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by roman on 18-4-13.
 */
public interface MobileHomePageRepository extends JpaRepository<MobileHomePage,Long>,JpaSpecificationExecutor<MobileHomePage>{
}
