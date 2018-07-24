package com.fongwell.satchi.crm.api.authentication.wx;

import com.fongwell.satchi.crm.core.customer.dto.CustomerAuthenticationDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by docker on 3/19/18.
 */
public class WxCustomerContext {

    /**
     * 从 security 认证信息中获取当前登录人信息
     * @return 当前登录人
     */
    public static final CustomerAuthenticationDetails getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication:==" + authentication);
        if (authentication.getPrincipal() instanceof CustomerAuthenticationDetails) {
            return (CustomerAuthenticationDetails) authentication.getPrincipal();
        }
        return null;


    }

    /**
     * 从 security 认证信息中获取当前登录人信息id
     */
    public static final String getWxId() {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof WxCustomerAuthentication) {
            return ((WxCustomerAuthentication) authentication).getWxId();
        }
        return null;

    }


}
