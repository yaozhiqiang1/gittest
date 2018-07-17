package com.fongwell.satchi.crm.core.product.query;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Date:2018/7/16
 * Author: yaozhiqiang
 * Desc:
 */
@Mapper
public interface ProductMapper {


    void updateStateEnable(@Param("productId") Long productId);

    void updateStateDisable(@Param("productId") Long productId);
}
