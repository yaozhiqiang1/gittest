package com.fongwell.satchi.crm.core.order.query.mapper;

import com.fongwell.satchi.crm.core.common.KeyValue;
import com.fongwell.satchi.crm.core.order.query.dto.OrderStats;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;


/**
 * Created by docker on 5/18/18.
 */
@Mapper
public interface OrderStatsQueryMapper {


    OrderStats queryStats(@Param("customerId") long customerId);

    Collection<Map> queryOrderItemHistory(@Param("customerId") long customerId,
                                          @Param("from") int from,
                                          @Param("size") int size);

    Collection<KeyValue> queryTotalExpenses(@Param("customerIds") Collection<Long> customerIds);

    Collection<Map> queryOrderHistory(@Param("customerId") long customerId,
                                          @Param("from") int from,
                                          @Param("size") int size);

}
