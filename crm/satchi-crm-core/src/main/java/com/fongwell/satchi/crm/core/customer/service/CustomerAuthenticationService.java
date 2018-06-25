package com.fongwell.satchi.crm.core.customer.service;

import com.fongwell.satchi.crm.core.customer.dto.CustomerAuthenticationDetails;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by docker on 4/27/18.
 */
@Transactional
public interface CustomerAuthenticationService {

    CustomerAuthenticationDetails authenticate(String mobile);

    CustomerAuthenticationDetails authenticate(long id);

}
