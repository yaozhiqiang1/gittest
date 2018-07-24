package com.fongwell.satchi.crm.core.customer.service;

import com.fongwell.satchi.crm.core.customer.domain.aggregate.Customer;
import com.fongwell.satchi.crm.core.customer.dto.CustomerData;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by docker on 5/22/18.
 */
@Transactional
public interface CustomerService {

    Customer createCustomer(CustomerData data);

    void updateCustomer(long id, CustomerData data);

    void deleteCustomer(long id);

    void markImportant(long customerId, long storeId);

    void unmarkImportant(long customerId, long storeId);

}
