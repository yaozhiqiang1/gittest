package com.fongwell.satchi.crm.core.order.domain.service.acitivity;

import com.fongwell.base.workflow.BaseActivity;
import com.fongwell.base.workflow.WorkflowContext;
import com.fongwell.base.workflow.WorkflowException;
import com.fongwell.satchi.crm.core.common.MathUtils;
import com.fongwell.satchi.crm.core.order.domain.aggregate.Order;
import com.fongwell.satchi.crm.core.order.domain.aggregate.OrderHistory;
import com.fongwell.satchi.crm.core.order.domain.entity.OrderHistoryItem;
import com.fongwell.satchi.crm.core.order.domain.entity.OrderItem;
import com.fongwell.satchi.crm.core.order.domain.entity.OrderPayment;
import com.fongwell.satchi.crm.core.order.domain.service.ProcessOrderPaymentData;
import com.fongwell.satchi.crm.core.order.exception.ProcessOrderPaymentException;
import com.fongwell.satchi.crm.core.order.repository.OrderHistoryRepository;
import com.fongwell.satchi.crm.core.order.repository.OrderRepository;
import com.fongwell.satchi.crm.core.payment.gateway.dto.PaymentGatewayResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by docker on 4/24/18.
 */
@Component
public class ApplyOrderPaymentActivity<T extends WorkflowContext<ProcessOrderPaymentData>> extends BaseActivity<T> {

    @Resource(name = "orderRepository")
    private OrderRepository orderRepository;

    @Resource(name = "orderHistoryRepository")
    private OrderHistoryRepository orderHistoryRepository;

    @Override
    public void execute(final T context) throws WorkflowException {

        Order order = context.getData().getOrder();
        PaymentGatewayResponse payment = context.getData().getPayment();

        if (order.getId() != payment.getOrderId()) {
            throw new ProcessOrderPaymentException("Order id does not match orderId of the specified PaymentGatewayResponse!");
        }


        order.addPayment(new OrderPayment(payment.getTransactionId(), order.getId(), payment.getGatewayType(), payment.getAmount(), payment.getPaymentDate()));
        orderRepository.save(order);

        Collection<OrderItem> items = order.getItems();
        int amount = 0;
        for (final OrderItem item : items) {
            amount += item.getQuantity();
        }

        OrderHistory history = new OrderHistory(order.getSalePrice(), amount, order.getId(), order.getCustomerId(), null, "online");

        history.setOriginalPrice(order.getPrice());
        history.setDiscount(MathUtils.divide(order.getSalePrice(), order.getPrice()));
        history.setPaymentGatewayType(payment.getGatewayType());
        for (final OrderItem item : items) {
            history.getItems().add(new OrderHistoryItem(history.getId(), item.getId(), item.getName(), item.getTotalSalePrice(), item.getQuantity(), item.getPcImage()));
        }

        orderHistoryRepository.save(history);
    }

    @Override
    public boolean shouldExecute(final T t) {
        return true;
    }
}
