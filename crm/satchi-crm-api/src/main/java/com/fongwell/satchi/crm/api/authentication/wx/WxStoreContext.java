package com.fongwell.satchi.crm.api.authentication.wx;

import com.fongwell.satchi.crm.core.store.domain.aggregate.dto.StaffAuthenticationDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by docker on 5/28/18.
 */
public class WxStoreContext {

    public static final StaffAuthenticationDetails getStaff() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof StaffAuthenticationDetails) {
            return (StaffAuthenticationDetails) authentication.getPrincipal();
        }
        return null;


    }
}
