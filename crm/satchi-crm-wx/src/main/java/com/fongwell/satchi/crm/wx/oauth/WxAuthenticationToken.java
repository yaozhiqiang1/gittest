package com.fongwell.satchi.crm.wx.oauth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by docker on 10/20/17.
 */
public class WxAuthenticationToken extends AbstractAuthenticationToken {

    private String client;
    private final UserDetails userDetails;
    private String code;

    public WxAuthenticationToken(String client, String code) {
        super(null);
        this.userDetails = null;
        this.code = code;
        this.client = client;

        super.setAuthenticated(false);
    }

    public WxAuthenticationToken(UserDetails userDetails) {
        super(userDetails.getAuthorities());
        this.userDetails = userDetails;

        super.setAuthenticated(true);
    }

    public String getClient() {
        return client;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCredentials() {
        return code;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    @Override
    public String getName() {
        return code == null ? userDetails.getUsername() : code;
    }
}
