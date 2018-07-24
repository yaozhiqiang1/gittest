package com.fongwell.satchi.crm.core.category.service;

import com.fongwell.satchi.crm.core.category.domain.aggregate.TopCategory;
import com.fongwell.satchi.crm.core.category.dto.TopCategoryData;
import com.fongwell.satchi.crm.core.common.WriteService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by roman on 18-4-16.
 */
@Transactional
public interface TopCategoryService extends WriteService<TopCategory,Long,TopCategoryData>{

    void onCreate(TopCategoryData data);

    void onUpdate(Long id,TopCategoryData data);

    String onEnable(Collection<Long> ids);

    void onDisable(Collection<Long> ids);

    void onDelete(Collection<Long> ids);

    void onSort(Long id,int orderNumber);

    boolean deleteValidate(Collection<Long> categoryIds);

    void deleteByParent(Collection<Long> parents);
}
