package com.fongwell.satchi.crm.core.payment.gateway;

import com.fongwell.satchi.crm.core.order.domain.aggregate.Order;
import com.fongwell.satchi.crm.core.order.repository.OrderRepository;
import com.fongwell.satchi.crm.core.payment.gateway.provider.PaymentGatewayProvider;
import com.fongwell.support.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by docker on 4/23/18.
 */
@Service("paymentGatewayCheckoutService")
public class PaymentGatewayCheckoutServiceImpl implements PaymentGatewayCheckoutService {

    @Autowired
    private Map<String, PaymentGatewayProvider> gatewayProviders;

    @Resource(name = "orderRepository")
    private OrderRepository orderRepository;


    @Override
    public Object initCheckout(final PaymentGatewayCheckoutRequest request) {

        Order order = orderRepository.findOne(request.getOrderId());
        Assert.notNull(order, "order");
        request.setAmount(order.getSalePrice());
        System.out.println("PaymentGatewayCheckoutServiceImpl.initCheckout");
      
        for (final PaymentGatewayProvider paymentGatewayProvider : gatewayProviders.values()) {
        	System.out.println("-----------------------------paymentGatewayProvider.getSupportedType():"+paymentGatewayProvider.getSupportedType());
        	System.out.println("----------------------------                 -request.getGatewayType():"+request.getGatewayType());
            if (paymentGatewayProvider.getSupportedType().equals(request.getGatewayType())) {
                return paymentGatewayProvider.initCheckout(request);
            }
        }

        throw new IllegalArgumentException("No PaymentGatewayProvider for type: " + request.getGatewayType());

    }
}
