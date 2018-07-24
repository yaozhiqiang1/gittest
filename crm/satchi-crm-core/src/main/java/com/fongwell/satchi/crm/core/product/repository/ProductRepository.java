package com.fongwell.satchi.crm.core.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fongwell.satchi.crm.core.product.domain.aggregate.Product;

/**
 * Created by roman on 18-4-3.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long>,JpaSpecificationExecutor<Product>{

    Product findByNumber(String number);

}
