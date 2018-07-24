package com.fongwell.satchi.crm.core.product.service;

import com.fongwell.satchi.crm.core.product.dto.ProductData;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by roman on 18-4-3.
 */
@Transactional
public interface ProductService {

    Long onCreate(ProductData data);

    void onUpdate(long id,ProductData data);

    void onDisable(Collection<Long> ids);

    void onEnable(Collection<Long> ids);

    void onDelete(Collection<Long> ids);

    void onSortting(Long id,int orderNumber);
}
