package com.fongwell.satchi.crm.core.order.service;

import com.fongwell.satchi.crm.core.order.domain.dto.ShippingRequest;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by docker on 5/7/18.
 */
@Transactional
public interface OrderService {

    void shipOrder(long orderId, ShippingRequest request);

    void cancelOrder(long orderId);

    void completeOrder(long orderId);
}
