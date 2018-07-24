package com.fongwell.satchi.crm.core.order.factory;

import com.fongwell.satchi.crm.core.order.checkout.CheckoutContext;
import com.fongwell.satchi.crm.core.order.domain.aggregate.Order;
import com.fongwell.satchi.crm.core.pricing.PricingResult;

/**
 * Created by docker on 4/23/18.
 */
public interface OrderFactory {

    Order createOrderFromCheckout(CheckoutContext context, PricingResult priceInfo);
}
