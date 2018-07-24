package com.fongwell.satchi.crm.core.category.repository;

import com.fongwell.satchi.crm.core.category.domain.aggregate.TopCategory;
import com.fongwell.satchi.crm.core.common.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;

/**
 * Created by roman on 18-4-16.
 */
public interface TopCategoryRepository extends JpaRepository<TopCategory,Long>,JpaSpecificationExecutor<TopCategory>{

    TopCategory findByName(String name);

    List<TopCategory> findByState(State state);

    List<TopCategory> findByCategoryIdIn(Collection<Long> categoryIds);

}
