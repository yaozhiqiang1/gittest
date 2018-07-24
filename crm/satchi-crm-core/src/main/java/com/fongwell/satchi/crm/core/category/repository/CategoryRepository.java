package com.fongwell.satchi.crm.core.category.repository;

import com.fongwell.satchi.crm.core.category.domain.aggregate.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by roman on 18-4-8.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>,JpaSpecificationExecutor<Category> {

    Category findByNameAndDeleted(String name,boolean deleted);
}
