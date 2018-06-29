package com.fongwell.satchi.crm.core.customer.query.repository;

import com.fongwell.satchi.crm.core.customer.domain.aggregate.Customer;
import com.fongwell.satchi.crm.core.customer.query.dto.CustomerDetails;

import javax.servlet.http.HttpSession;

/**
 * Created by docker on 2/25/18.
 */
public interface CustomerQueryRepository {

    CustomerDetails queryCustomerDetails(String mobile);

    CustomerDetails queryCustomerDetails(long id);

    Customer queryCustomerStoreId(String mobile, Long storeId);

    void updateCustomer(Long storeId, String mobile);

}
