package com.fongwell.satchi.crm.core.order.utils;

import com.fongwell.satchi.crm.core.order.domain.aggregate.Order;
import com.fongwell.satchi.crm.core.order.domain.entity.OrderItem;
import com.fongwell.satchi.crm.core.order.event.OrderEventItem;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by docker on 5/11/18.
 */
public class OrderUtils {

    public static Collection<OrderEventItem> toEventItems(Order order) {

        Collection<OrderItem> items = order.getItems();

        Collection<OrderEventItem> eventItems = new ArrayList<>(items.size());

        for (final OrderItem item : items) {
            eventItems.add(new OrderEventItem(item.getProductId(), item.getSkuId(), item.getQuantity()));
        }

        return eventItems;

    }
}
