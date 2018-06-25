package com.fongwell.satchi.crm.core.credit.listener;

import com.fongwell.infrastructure.event.DomainEventListener;
import com.fongwell.infrastructure.event.Subscribe;
import com.fongwell.satchi.crm.core.order.event.OrderPaidEvent;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by docker on 5/22/18.
 */
@Transactional
public interface CreditListener extends DomainEventListener {


    @Subscribe(dispatcher = "domainEventDispatcher")
    void onOrderPaidEvent(OrderPaidEvent event);
}
