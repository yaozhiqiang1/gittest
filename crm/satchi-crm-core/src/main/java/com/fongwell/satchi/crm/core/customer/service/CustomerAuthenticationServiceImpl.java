package com.fongwell.satchi.crm.core.customer.service;

import com.fongwell.satchi.crm.core.customer.dto.CustomerAuthenticationDetails;
import com.fongwell.satchi.crm.core.customer.query.dto.CustomerDetails;
import com.fongwell.satchi.crm.core.customer.query.repository.CustomerQueryRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Arrays;


/**
 * Created by docker on 4/27/18.
 */
@Service("customerAuthenticationService")
public class CustomerAuthenticationServiceImpl implements CustomerAuthenticationService {


    @Resource(name = "customerQueryRepository")
    private CustomerQueryRepository customerQueryRepository;


    //认证电话号码
    @Override
    public CustomerAuthenticationDetails authenticate(final String mobile) {

        CustomerDetails customer = customerQueryRepository.queryCustomerDetails(mobile);
        if (customer == null) {
            throw new UsernameNotFoundException("Customer not found");
        }

        return new CustomerAuthenticationDetails(customer.getId(), customer.getMobile(), customer.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_CUSTOMER")));

    }
/*

    //认证电话号码
    @Override
    public CustomerAuthenticationDetails authenticate(final String mobile,String code) {

        CustomerDetails customer = customerQueryRepository.queryCustomerDetails(mobile);
        if (customer == null) {
            throw new UsernameNotFoundException("Customer not found");
        }
//
//        String userPassword = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

//        if (code.equals(userPassword)){}

        return new CustomerAuthenticationDetails(customer.getId(), customer.getMobile(), customer.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_CUSTOMER")));

        return null;
    }
*/




    @Override
    public CustomerAuthenticationDetails authenticate(final long id) {


        CustomerDetails customer = customerQueryRepository.queryCustomerDetails(id);
        if (customer == null) {
            throw new UsernameNotFoundException("Customer not found");
        }

        return new CustomerAuthenticationDetails(customer.getId(), customer.getMobile(), customer.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_CUSTOMER")));

    }
}
