package com.fongwell.satchi.crm.api.authentication.wx;

import com.fongwell.satchi.crm.core.customer.dto.CustomerAuthenticationDetails;
import com.fongwell.satchi.crm.core.customer.service.CustomerAuthenticationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by docker on 4/28/18.
 */
@Service
public class WxCustomerDetailsService implements UserDetailsService {

    @Resource(name = "customerAuthenticationService")
    private CustomerAuthenticationService customerAuthenticationService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        CustomerAuthenticationDetails details = customerAuthenticationService.authenticate(username);
        return details;
    }
}
