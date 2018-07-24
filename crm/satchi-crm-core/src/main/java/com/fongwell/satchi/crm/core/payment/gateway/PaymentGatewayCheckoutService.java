package com.fongwell.satchi.crm.core.payment.gateway;

/**
 * Created by docker on 4/23/18.
 */
public interface PaymentGatewayCheckoutService {

    Object initCheckout(PaymentGatewayCheckoutRequest request);
}
