package com.fongwell.satchi.crm.core.customer.service;

import com.fongwell.satchi.crm.core.customer.domain.aggregate.Customer;
import com.fongwell.satchi.crm.core.customer.domain.service.GenerateCustomerNumberService;
import com.fongwell.satchi.crm.core.customer.dto.CustomerRegisterRequest;
import com.fongwell.satchi.crm.core.customer.exception.DuplicateCustomerMobileException;
import com.fongwell.satchi.crm.core.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by docker on 3/19/18.
 */
@Service("customerRegistrationService")
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GenerateCustomerNumberService generateCustomerNumberService;

    @Override
    public Customer register(final CustomerRegisterRequest request) {

        Customer customer = customerRepository.findByMobile(request.getMobile());
        if (customer != null) {
            throw new DuplicateCustomerMobileException("Duplicate mobile:" + request.getMobile());
        }

        customer = new Customer(generateCustomerNumberService.generate(), request.getName(), request.getMobile(), request.getPassword() == null ? null : passwordEncoder.encode(request.getPassword()));
        customer.setDob(request.getDob());
        customer.setSex(request.getSex());

        customerRepository.save(customer);

        return customer;

    }
}
