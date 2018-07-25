package com.fongwell.satchi.crm.core.order.service;

import org.springframework.transaction.annotation.Transactional;

import com.fongwell.satchi.crm.core.order.domain.dto.ShippingRequest;

/**
 * Created by docker on 5/7/18.
 */
@Transactional
public interface OrderService {

    void shipOrder(long orderId, ShippingRequest request);

    void cancelOrder(long orderId);

    void completeOrder(long orderId);

	void unship(long orderId);
	
}
