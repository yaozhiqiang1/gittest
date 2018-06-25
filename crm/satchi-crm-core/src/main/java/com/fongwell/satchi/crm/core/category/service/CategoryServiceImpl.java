package com.fongwell.satchi.crm.core.category.service;

import com.fongwell.satchi.crm.core.category.domain.aggregate.Category;
import com.fongwell.satchi.crm.core.category.dto.CategoryData;
import com.fongwell.satchi.crm.core.category.repository.CategoryRepository;
import com.fongwell.satchi.crm.core.common.AbstractWriteService;
import com.fongwell.satchi.crm.core.common.error.DataNotFoundException;
import com.fongwell.satchi.crm.core.common.error.DuplicateParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * Created by roman on 18-4-8.
 */
@Service("categoryService")
public class CategoryServiceImpl extends AbstractWriteService<Category, Long, CategoryData> implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Resource(name = "topCategoryService")
    private TopCategoryService topCategoryService;

    public CategoryServiceImpl() {
        super(Category.class);
    }

    @Override
    public void onCreate(CategoryData data) {
        Category category = categoryRepository.findByNameAndDeleted(data.getName().trim(), false);
        if (category != null) {
            throw new DuplicateParameterException("CategoryData.name duplicae :" + data.getName());
        }
        create(data);
    }

    @Override
    public void onUpdate(Long id, CategoryData data) {
        Category nameCategory = categoryRepository.findByNameAndDeleted(data.getName().trim(), false);
        if (nameCategory != null && nameCategory.getId() != id) {
            throw new DuplicateParameterException("CategoryData.name duplicate :" + data.getName());
        }
        findOne(id, true);
        update(id, data);
    }

    @Override
    public void onDelete(Collection<Long> ids) {

        List<Category> all = categoryRepository.findAll(ids);
        for (Category next : all) {
            next.onDelete();
            categoryRepository.save(next);
        }
        topCategoryService.deleteByParent(ids);

    }

    @Override
    public void onSort(long id, int orderNumber) {
        Category one = findOne(id, true);
        one.sort(orderNumber);
        categoryRepository.save(one);
    }

    private Category findOne(Long id, boolean check) {
        Category category = categoryRepository.findOne(id);
        if (category == null && check) {
            throw new DataNotFoundException("Category not found :" + id);
        }
        return category;
    }

    @Override
    protected JpaRepository<Category, Long> getRepository() {
        return categoryRepository;
    }
}
