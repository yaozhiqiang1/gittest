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

    Customer queryCustomerStoreId(long customerId, Long storeId);

    void updateCustomer(long customerId,Long storeId);

    long queryCustomerStore(long customerId);
}
