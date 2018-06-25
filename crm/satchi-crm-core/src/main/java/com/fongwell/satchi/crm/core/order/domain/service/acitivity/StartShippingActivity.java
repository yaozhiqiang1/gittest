package com.fongwell.satchi.crm.core.order.domain.service.acitivity;

import com.fongwell.base.workflow.BaseActivity;
import com.fongwell.base.workflow.WorkflowContext;
import com.fongwell.base.workflow.WorkflowException;
import com.fongwell.satchi.crm.core.order.domain.aggregate.Order;
import com.fongwell.satchi.crm.core.order.domain.aggregate.Shipping;
import com.fongwell.satchi.crm.core.order.domain.service.ProcessOrderPaymentData;
import com.fongwell.satchi.crm.core.order.domain.value.OrderState;
import com.fongwell.satchi.crm.core.order.repository.OrderRepository;
import com.fongwell.satchi.crm.core.order.repository.ShippingRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by docker on 4/24/18.
 */
@Component
public class StartShippingActivity<T extends WorkflowContext<ProcessOrderPaymentData>> extends BaseActivity<T> {

    @Resource(name = "orderRepository")
    private OrderRepository orderRepository;

    @Resource(name = "shippingRepository")
    private ShippingRepository shippingRepository;

    @Override
    public void execute(final T context) throws WorkflowException {

        Order order = context.getData().getOrder();
        order.setState(OrderState.to_ship);
        orderRepository.save(order);

        Shipping shipping = new Shipping(order.getCustomerId(), order.getId(), order.getShippingAddress());
        shippingRepository.save(shipping);


    }

    @Override
    public boolean shouldExecute(final T t) {
        return t.getData().getOrder().getState() == OrderState.pending;
    }
}
