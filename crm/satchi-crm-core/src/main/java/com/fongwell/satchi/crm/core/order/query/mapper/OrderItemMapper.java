package com.fongwell.satchi.crm.core.order.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Date:2018/7/16
 * Author: yaozhiqiang
 * Desc:
 */
@Mapper
public interface OrderItemMapper {

    Integer queryQuantity(@Param("productId") long productId,@Param("orderId") Long orderId);
}
