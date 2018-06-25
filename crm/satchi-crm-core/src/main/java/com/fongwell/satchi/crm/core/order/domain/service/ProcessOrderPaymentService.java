package com.fongwell.satchi.crm.core.order.domain.service;

import com.fongwell.satchi.crm.core.order.domain.aggregate.Order;
import com.fongwell.satchi.crm.core.payment.gateway.dto.PaymentGatewayResponse;

/**
 * Created by docker on 4/24/18.
 */
public interface ProcessOrderPaymentService {

    void processOrderPayment(Order order, PaymentGatewayResponse payment);
}
