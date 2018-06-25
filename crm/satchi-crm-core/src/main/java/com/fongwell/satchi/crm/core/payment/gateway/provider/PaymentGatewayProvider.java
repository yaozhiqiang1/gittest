package com.fongwell.satchi.crm.core.payment.gateway.provider;

import com.fongwell.satchi.crm.core.payment.gateway.PaymentGatewayCheckoutRequest;

/**
 * Created by docker on 4/23/18.
 */
public interface PaymentGatewayProvider {

    Object initCheckout(final PaymentGatewayCheckoutRequest request);

    String getSupportedType();
}
