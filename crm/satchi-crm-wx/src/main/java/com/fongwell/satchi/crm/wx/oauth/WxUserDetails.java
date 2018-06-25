package com.fongwell.satchi.crm.wx.oauth;

import com.foxinmy.weixin4j.mp.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by docker on 10/20/17.
 */

public class WxUserDetails implements UserDetails {

    private static final long serialVersionUID = 2016100101L;

    private final User user;

    public WxUserDetails(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_WX_USER"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPassword() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return this.getUser().getOpenId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getUser().toString();
    }

}