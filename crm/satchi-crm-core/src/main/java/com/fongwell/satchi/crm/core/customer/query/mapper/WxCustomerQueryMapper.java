package com.fongwell.satchi.crm.core.customer.query.mapper;

import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    

    Collection<Map> querySC(@Param("storeId") Long storeId,
                                        @Param("ids") Collection<Long> ids,
                                        @Param("query") String query,
                                        @Param("sort") String sort,
                                        @Param("sortBy") String sortBy,
                                        @Param("from") int from,
                                        @Param("size") int size
                                        );

    Map queryDetail(@Param("id") long id);


	String getOpenId(@Param("id") Long customerId);
    
	void updateType(@Param("id") Long id);


	int checkMobile(@Param("mobile") String mobile);
    

}
