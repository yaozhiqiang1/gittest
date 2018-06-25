package com.fongwell.satchi.crm.core.product.service;

import com.fongwell.infrastructure.event.DomainEventListener;
import com.fongwell.infrastructure.event.Subscribe;
import com.fongwell.satchi.crm.core.order.event.OrderPaidEvent;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by docker on 5/11/18.
 */
@Transactional
public interface ProductSaleService extends DomainEventListener{

    @Subscribe(dispatcher = "domainEventDispatcher")
    void onOrderPaidEvent(OrderPaidEvent event);
}
