package com.fongwell.satchi.crm.core.customer.query.mapper;

import com.fongwell.satchi.crm.core.customer.query.dto.CustomerDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by docker on 2/25/18.
 */
@Mapper
public interface CustomerQueryMapper {

    CustomerDetails queryCustomerDetailsByMobile(@Param("mobile") String mobile);

    CustomerDetails queryCustomerDetails(@Param("id") long id);
}
