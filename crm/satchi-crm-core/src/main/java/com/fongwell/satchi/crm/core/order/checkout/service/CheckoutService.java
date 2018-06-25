package com.fongwell.satchi.crm.core.order.checkout.service;

import com.fongwell.satchi.crm.core.order.domain.aggregate.Order;
import com.fongwell.satchi.crm.core.order.dto.CartInfo;
import com.fongwell.satchi.crm.core.order.dto.CheckoutItem;
import com.fongwell.satchi.crm.core.order.dto.CheckoutRequest;
import com.fongwell.satchi.crm.core.order.dto.UpdateCartResult;
import com.fongwell.satchi.crm.core.payment.gateway.dto.PaymentGatewayResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by docker on 4/19/18.
 */
@Transactional
public interface CheckoutService {

    Order checkout(long customerId, CheckoutRequest request);

    UpdateCartResult updateCart(long customerId, Collection<CheckoutItem> items);

    CartInfo initCart(long customerId, Collection<CheckoutItem> items);

    void applyPayment(long orderId, PaymentGatewayResponse response);
}
