package com.fongwell.satchi.crm.api.authentication.wx;

import com.fongwell.satchi.crm.core.customer.dto.CustomerAuthenticationDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by docker on 3/19/18.
 */
public class WxCustomerContext {
    public static final CustomerAuthenticationDetails getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof CustomerAuthenticationDetails) {
            return (CustomerAuthenticationDetails) authentication.getPrincipal();
        }
        return null;


    }

    public static final String getWxId() {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof WxCustomerAuthentication) {
            return ((WxCustomerAuthentication) authentication).getWxId();
        }
        return null;

    }


}
