package com.fongwell.satchi.crm.core.order.query.mapper;

import com.fongwell.satchi.crm.core.order.domain.value.OrderState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by docker on 5/7/18.
 */
@Mapper
public interface AdminOrderQueryMapper {

    Collection<Map> queryOrders(
            @Param("states") Collection<OrderState> states,
            @Param("start") Date start, @Param("end") Date end,
            @Param("from") int from, @Param("size") int size);

    int countOrders(
            @Param("states") Collection<OrderState> states,
            @Param("start") Date start, @Param("end") Date end);

    Map findOrder(@Param("id") long id);
}
