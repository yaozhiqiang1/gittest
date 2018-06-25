package com.fongwell.satchi.crm.core.product.repository;

import com.fongwell.satchi.crm.core.product.domain.aggregate.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by roman on 18-4-3.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long>,JpaSpecificationExecutor<Product>{

    Product findByNumber(String number);

}
