package com.fongwell.satchi.crm.core.order.service;

import com.fongwell.infrastructure.event.publish.EventPublisher;
import com.fongwell.satchi.crm.core.order.domain.aggregate.Order;
import com.fongwell.satchi.crm.core.order.domain.aggregate.Shipping;
import com.fongwell.satchi.crm.core.order.domain.dto.ShippingRequest;
import com.fongwell.satchi.crm.core.order.event.OrderCancelledEvent;
import com.fongwell.satchi.crm.core.order.repository.OrderRepository;
import com.fongwell.satchi.crm.core.order.repository.ShippingRepository;
import com.fongwell.satchi.crm.core.order.utils.OrderUtils;
import com.fongwell.support.utils.Assert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by docker on 5/9/18.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource(name = "orderRepository")
    private OrderRepository orderRepository;

    @Resource(name = "eventPublisher")
    private EventPublisher eventPublisher;

    @Resource(name = "shippingRepository")
    private ShippingRepository shippingRepository;

    @Override
    public void shipOrder(final long orderId, final ShippingRequest request) {

        Order order = orderRepository.findOne(orderId);
        Assert.notNull(order, "order");
        order.ship();

        Shipping shipping = shippingRepository.findByOrderId(orderId);

        if (shipping == null) {
            shipping = new Shipping(order.getCustomerId(), orderId, order.getShippingAddress());
        }
        shipping.setTrackingNumber(request.getTrackingNumber());

        shippingRepository.save(shipping);
    }

    @Override
    public void cancelOrder(final long orderId) {

        Order order = orderRepository.findOne(orderId);
        if (order != null) {
            order.cancel();
            orderRepository.save(order);


            eventPublisher.publish(new OrderCancelledEvent(order.getId(), OrderUtils.toEventItems(order)));

        }


    }

    @Override
    public void completeOrder(final long orderId) {
        Order order = orderRepository.findOne(orderId);
        if (order != null) {
            order.complete();
            orderRepository.save(order);

        }


    }
}
