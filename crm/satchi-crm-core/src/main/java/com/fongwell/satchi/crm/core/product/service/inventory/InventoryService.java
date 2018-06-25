package com.fongwell.satchi.crm.core.product.service.inventory;

import com.fongwell.infrastructure.event.DomainEventListener;
import com.fongwell.infrastructure.event.Subscribe;
import com.fongwell.satchi.crm.core.order.event.OrderCancelledEvent;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 4/20/18.
 */
@Transactional
public interface InventoryService extends DomainEventListener {

    @Transactional(readOnly = true)
    Map<Long, Integer> getInventory(Collection<Long> skuIds);

    void decrementInventoryForCheckout(long orderId, long customerId, Map<Long, Integer> values);

    @Subscribe(dispatcher = "domainEventDispatcher")
    void onOrderCancelledEvent(OrderCancelledEvent event);


}
