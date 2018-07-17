package com.fongwell.satchi.crm.core.product.service;

import com.fongwell.base.rest.Payload;
import com.fongwell.satchi.crm.core.order.domain.entity.OrderItem;
import com.fongwell.satchi.crm.core.product.dto.ProductData;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

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

    /**
     * 更新礼品状态
     * @param date
     */
    void updateProductStatus(Date date);

    /**
     * 限制礼品的购买
     * @param orderItem
     */
    Payload restrictionamountGift(OrderItem orderItem);
}
