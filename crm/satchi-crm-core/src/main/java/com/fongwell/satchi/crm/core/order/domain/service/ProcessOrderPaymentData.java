package com.fongwell.satchi.crm.core.order.domain.service;

import com.fongwell.satchi.crm.core.order.domain.aggregate.Order;
import com.fongwell.satchi.crm.core.payment.gateway.dto.PaymentGatewayResponse;

/**
 * Created by docker on 4/24/18.
 */
public class ProcessOrderPaymentData {

    private final Order order;
    private final PaymentGatewayResponse payment;

    public ProcessOrderPaymentData(final Order order, final PaymentGatewayResponse payment) {
        this.order = order;
        this.payment = payment;
    }

    public Order getOrder() {
        return order;
    }

    public PaymentGatewayResponse getPayment() {
        return payment;
    }
}
