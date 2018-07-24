package com.fongwell.satchi.crm.core.order.query;

import com.fongwell.satchi.crm.core.order.domain.value.OrderState;
import com.fongwell.satchi.crm.core.order.query.dto.OrderDetail;
import com.fongwell.satchi.crm.core.order.query.mapper.OrderQueryMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 4/23/18.
 */
@Repository("orderQueryRepository")
public class OrderQueryRepositoryImpl implements OrderQueryRepository {

    @Resource(name = "orderQueryMapper")
    private OrderQueryMapper orderQueryMapper;

    @Override
    public OrderDetail queryOrderDetail(final long id) {
        return orderQueryMapper.queryOrderDetail(id);
    }

    @Override
    public Collection<Map> queryOrders(final long customerId, final OrderState state, final int from, final int size) {
        return orderQueryMapper.queryOrders(customerId, state == null ? null : state.name(), from, size);
    }
}
