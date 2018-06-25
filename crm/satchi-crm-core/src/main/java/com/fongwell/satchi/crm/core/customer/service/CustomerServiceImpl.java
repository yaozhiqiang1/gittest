package com.fongwell.satchi.crm.core.customer.service;

import com.fongwell.satchi.crm.core.common.error.DuplicateParameterException;
import com.fongwell.satchi.crm.core.customer.domain.aggregate.Customer;
import com.fongwell.satchi.crm.core.customer.domain.aggregate.ImportantCustomer;
import com.fongwell.satchi.crm.core.customer.domain.entity.ImportantCustomerId;
import com.fongwell.satchi.crm.core.customer.domain.repository.ImportantCustomerRepository;
import com.fongwell.satchi.crm.core.customer.dto.CustomerData;
import com.fongwell.satchi.crm.core.customer.factory.CustomerFactory;
import com.fongwell.satchi.crm.core.customer.repository.CustomerRepository;
import com.fongwell.support.utils.Assert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by docker on 5/22/18.
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Resource(name = "customerRepository")
    private CustomerRepository customerRepository;

    @Resource(name = "customerFactory")
    private CustomerFactory customerFactory;

    @Resource(name = "importantCustomerRepository")
    private ImportantCustomerRepository importantCustomerRepository;

    /**
     * 添加会员
     * @param data
     * @return
     */
    @Override
    public Customer createCustomer(final CustomerData data) {

        Customer duplicate = customerRepository.findByMobile(data.getMobile());
        if (duplicate != null) {
            throw new DuplicateParameterException("duplicate customer mobile");
        }
        Customer customer = customerFactory.createCustomer(data);
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void updateCustomer(final long id, final CustomerData data) {

        Customer customer = customerRepository.findOne(id);
        Assert.notNull(customer, "customer");

        Customer duplicate = customerRepository.findByMobile(data.getMobile());
        if (duplicate != null && duplicate.getId() != id) {
            throw new DuplicateParameterException("duplicate customer mobile");
        }

        customer.update(data);
        customerRepository.save(customer);


    }

    @Override
    public void deleteCustomer(final long id) {
        customerRepository.delete(id);
    }

    @Override
    public void markImportant(final long customerId, final long storeId) {

        ImportantCustomer entity = importantCustomerRepository.findOne(new ImportantCustomerId(customerId, storeId));
        if (entity == null) {
            importantCustomerRepository.save(new ImportantCustomer(customerId, storeId));
        }
    }

    @Override
    public void unmarkImportant(final long customerId, final long storeId) {
        
        importantCustomerRepository.delete(new ImportantCustomerId(customerId, storeId));
    }

}
