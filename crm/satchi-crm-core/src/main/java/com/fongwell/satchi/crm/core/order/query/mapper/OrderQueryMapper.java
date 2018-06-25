package com.fongwell.satchi.crm.core.order.query.mapper;

import com.fongwell.satchi.crm.core.order.query.dto.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 4/23/18.
 */
@Mapper
public interface OrderQueryMapper {

    OrderDetail queryOrderDetail(@Param("id") long id);

    Collection<Map> queryOrders(@Param("customerId") long customerId,
                                @Param("state") String state,
                                @Param("from") int from,
                                @Param("size") int size);
}
