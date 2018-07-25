package com.fongwell.satchi.crm.core.category.service;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import com.fongwell.base.rest.Payload;
import com.fongwell.satchi.crm.core.category.domain.aggregate.Category;
import com.fongwell.satchi.crm.core.category.dto.CategoryData;
import com.fongwell.satchi.crm.core.common.WriteService;

/**
 * Created by roman on 18-4-8.
 */
@Transactional
public interface CategoryService extends WriteService<Category,Long,CategoryData> {

    void onCreate(CategoryData data);

    void onUpdate(Long id,CategoryData data);

    Payload onDelete(Collection<Long> id);

    void onSort(long id,int orderNumber);

}
