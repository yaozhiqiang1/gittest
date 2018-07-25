package com.fongwell.satchi.crm.core.customer.query.repository;

import com.fongwell.satchi.crm.core.customer.domain.aggregate.Customer;
import com.fongwell.satchi.crm.core.customer.query.dto.CustomerDetails;
import com.fongwell.satchi.crm.core.customer.query.mapper.CustomerQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;

/**
 * Created by docker on 2/25/18.
 */
@Repository("customerQueryRepository")
public class CustomerQueryRepositoryImpl implements CustomerQueryRepository {


    @Autowired
    private CustomerQueryMapper customerQueryMapper;

    @Override
    public CustomerDetails queryCustomerDetails(final String mobile) {
        return customerQueryMapper.queryCustomerDetailsByMobile(mobile);
    }

    @Override
    public CustomerDetails queryCustomerDetails(final long id) {
        return customerQueryMapper.queryCustomerDetails(id);
    }

    @Override
    public void updateCustomer(long customerId,Long storeId) {
        customerQueryMapper.updateCustomer(customerId,storeId);
    }

    @Override
    public long queryCustomerStore(long customerId) {
        return customerQueryMapper.queryCustomerStore(customerId);

    }
}
