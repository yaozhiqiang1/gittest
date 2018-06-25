package com.fongwell.satchi.crm.api.authentication.wx;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by docker on 3/19/18.
 */
public class WxCustomerAuthentication extends AbstractAuthenticationToken {

    private UserDetails user;

    private String wxId;

    public WxCustomerAuthentication(String wxId, UserDetails user) {
        super(user == null ? null : user.getAuthorities());
        this.user = user;
        this.wxId = wxId;
        setAuthenticated(true);
    }

    public WxCustomerAuthentication(final String wxId, final Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.wxId = wxId;
        setAuthenticated(true);
    }

    public String getWxId() {
        return wxId;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }
}
