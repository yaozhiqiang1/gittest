package com.fongwell.satchi.crm.core.customer.query.mapper;

import com.fongwell.satchi.crm.core.customer.domain.aggregate.Customer;
import com.fongwell.satchi.crm.core.customer.query.dto.CustomerDetails;
import com.fongwell.satchi.crm.core.customer.query.dto.CustomerStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by docker on 2/25/18.
 */
@Mapper
public interface CustomerQueryMapper {

    CustomerDetails queryCustomerDetailsByMobile(@Param("mobile") String mobile);

    CustomerDetails queryCustomerDetails(@Param("id") long id);

    Customer queryCustomerStoreId(@Param("mobile")String mobile, @Param("storeId")Long storeId);

    void updateCustomer(@Param("mobile")String mobile,@Param("storeId")Long storeId);

    Long queryByMobile(@Param("mobile")String mobile);

    Date queryCheckindate(@Param("customerId") long customerId);
}
