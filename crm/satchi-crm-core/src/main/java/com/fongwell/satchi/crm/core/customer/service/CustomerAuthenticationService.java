package com.fongwell.satchi.crm.core.customer.service;

import com.fongwell.satchi.crm.core.customer.dto.CustomerAuthenticationDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

/**
 * Created by docker on 4/27/18.
 */
@Transactional
public interface CustomerAuthenticationService {

    //认证电话号码
    //CustomerAuthenticationDetails authenticate(String username,String code);

    CustomerAuthenticationDetails authenticate(String username);

    CustomerAuthenticationDetails authenticate(long id);

}
