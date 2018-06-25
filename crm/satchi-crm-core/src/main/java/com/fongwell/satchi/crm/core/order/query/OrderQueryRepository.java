package com.fongwell.satchi.crm.core.order.query;

import com.fongwell.satchi.crm.core.order.domain.value.OrderState;
import com.fongwell.satchi.crm.core.order.query.dto.OrderDetail;

import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 4/23/18.
 */
public interface OrderQueryRepository {

    OrderDetail queryOrderDetail(long id);

    Collection<Map> queryOrders(long customerId, OrderState state, int from, int size);


}
