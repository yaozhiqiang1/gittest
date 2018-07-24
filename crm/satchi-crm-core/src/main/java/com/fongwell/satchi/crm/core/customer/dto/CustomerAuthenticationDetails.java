package com.fongwell.satchi.crm.core.customer.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by docker on 4/27/18.
 */
public class CustomerAuthenticationDetails extends User {

    private long customerId;

    public CustomerAuthenticationDetails(long customerId, final String username, final String password, final Collection<? extends GrantedAuthority> authorities) {
        super(username, password == null ? "" : password, authorities);
        this.customerId = customerId;
    }

    public long getCustomerId() {
        return customerId;
    }
}
