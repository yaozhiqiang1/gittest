package com.fongwell.satchi.crm.core.category.service;

import com.fongwell.satchi.crm.core.category.domain.aggregate.Category;
import com.fongwell.satchi.crm.core.category.dto.CategoryData;
import com.fongwell.satchi.crm.core.common.WriteService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Created by roman on 18-4-8.
 */
@Transactional
public interface CategoryService extends WriteService<Category,Long,CategoryData> {

    void onCreate(CategoryData data);

    void onUpdate(Long id,CategoryData data);

    void onDelete(Collection<Long> id);

    void onSort(long id,int orderNumber);

}
