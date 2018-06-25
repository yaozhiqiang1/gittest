package com.fongwell.satchi.crm.core.customer.query.repository;

import com.fongwell.satchi.crm.core.customer.query.dto.CustomerDetails;

/**
 * Created by docker on 2/25/18.
 */
public interface CustomerQueryRepository {

    CustomerDetails queryCustomerDetails(String mobile);

    CustomerDetails queryCustomerDetails(long id);

}
