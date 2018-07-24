package com.fongwell.satchi.crm.core.customer.factory;


import com.fongwell.satchi.crm.core.customer.domain.aggregate.Customer;
import com.fongwell.satchi.crm.core.customer.dto.CustomerData;

/**
 * Created by docker on 8/1/17.
 */
public interface CustomerFactory {

    Customer createCustomer(CustomerData data);
}
