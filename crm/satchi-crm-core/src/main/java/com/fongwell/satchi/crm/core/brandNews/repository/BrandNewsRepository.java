package com.fongwell.satchi.crm.core.brandNews.repository;

import com.fongwell.satchi.crm.core.brandNews.domain.aggregate.BrandNews;
import com.fongwell.satchi.crm.core.common.query.RequestData;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * Created by roman on 18-3-23.
 */
@Component
public interface BrandNewsRepository extends JpaRepository<BrandNews,Long>{


}
