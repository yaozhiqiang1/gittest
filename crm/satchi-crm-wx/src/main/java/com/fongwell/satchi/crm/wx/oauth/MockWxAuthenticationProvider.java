package com.fongwell.satchi.crm.wx.oauth;

import com.fongwell.satchi.crm.wx.user.store.WxUserStore;
import com.foxinmy.weixin4j.mp.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by docker on 4/28/18.
 */
public class MockWxAuthenticationProvider implements AuthenticationProvider {

    private WxUserStore wxUserStore;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        String wxId = (String) authentication.getPrincipal();

        User user = wxUserStore.findUser(wxId);

        if (user == null) {
            throw new UsernameNotFoundException("User not found:" + wxId);
        }
        return new WxAuthenticationToken(new WxUserDetails(user));
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return WxAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
