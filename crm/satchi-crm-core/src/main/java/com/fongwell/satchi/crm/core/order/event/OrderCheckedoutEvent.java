package com.fongwell.satchi.crm.core.order.event;

import com.fongwell.satchi.crm.core.order.domain.dto.OrderDto;
import com.fongwell.satchi.crm.core.support.event.CrmEvent;

/**
 * Created by roman on 18-3-23.
 */
public class OrderCheckedoutEvent extends CrmEvent{

    private OrderDto target;

    public OrderCheckedoutEvent(OrderDto target) {
        this.target = target;
    }

    public OrderDto getTarget() {
        return target;
    }

    @Override
    public String getAggregateType() {
        return "OrderCheckedoutEvent";
    }
}
