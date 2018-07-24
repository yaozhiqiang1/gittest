package com.fongwell.satchi.crm.core.customer.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Date:2018/7/23
 * Author: yaozhiqiang
 * Desc:
 */
@Mapper
public interface WxUserMapper {
    String getWxPortrait(@Param("wxId") String wxId);
}
