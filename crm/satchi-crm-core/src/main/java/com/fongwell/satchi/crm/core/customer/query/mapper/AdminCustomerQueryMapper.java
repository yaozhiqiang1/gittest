package com.fongwell.satchi.crm.core.customer.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 5/22/18.
 */
@Mapper
public interface AdminCustomerQueryMapper {

    Collection<Map> queryCustomers(@Param("query") String query, @Param("from") int from, @Param("size") int size);

    int countCustomers(@Param("query") String query);

    Map getCustomer(@Param("id") long id);


}
