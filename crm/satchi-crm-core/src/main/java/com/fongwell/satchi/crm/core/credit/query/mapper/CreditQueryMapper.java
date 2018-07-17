package com.fongwell.satchi.crm.core.credit.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by docker on 5/22/18.
 */
@Mapper
public interface CreditQueryMapper {

    Integer queryCredits(@Param("customerId") long customerId);

    Collection<Map> queryCreditRecords(@Param("customerId") long customerId, @Param("type") String type, @Param("from") int from, @Param("size") int size);

    /**
     * 查询客户总积分
     * @param customerId
     * @return
     */
    Integer customerCreditTotal(@Param("customerId") long customerId);

    /**
     * 查看积分设置是否开启
     * @return
     */
    Boolean queryEnabled();


}
