package com.fongwell.satchi.crm.core.customer.service;

import com.fongwell.satchi.crm.core.customer.domain.aggregate.Customer;
import com.fongwell.satchi.crm.core.customer.dto.CustomerRegisterRequest;

/**
 * Created by docker on 3/19/18.
 */
public interface CustomerRegistrationService {

    Customer register(CustomerRegisterRequest request);

    Customer queryMobile(String mobile);
}
