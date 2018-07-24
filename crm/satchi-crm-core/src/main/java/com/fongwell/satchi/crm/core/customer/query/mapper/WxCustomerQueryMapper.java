package com.fongwell.satchi.crm.core.customer.query.mapper;

import com.fongwell.satchi.crm.core.customer.domain.aggregate.Customer;
import com.fongwell.satchi.crm.core.customer.query.dto.CustomerStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 3/19/18.
 */
@Mapper
public interface WxCustomerQueryMapper {

    Map queryProfile(@Param("id") long id);


    Collection<Map> queryStoreCustomers(@Param("storeId") Long storeId,
                                        @Param("ids") Collection<Long> ids,
                                        @Param("query") String query,
                                        @Param("from") int from,
                                        @Param("size") int size);

    Map queryDetail(@Param("id") long id);

<<<<<<< HEAD
<<<<<<< HEAD

=======
    Customer queryMobile(String mobile);
>>>>>>> remotes/crm/master
=======
    Customer queryMobile(String mobile);
>>>>>>> 1e9c47c378b92c957a3b4d33a2d332283afda506



}
