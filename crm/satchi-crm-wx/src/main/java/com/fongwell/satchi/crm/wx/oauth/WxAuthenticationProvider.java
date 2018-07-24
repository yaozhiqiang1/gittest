package com.fongwell.satchi.crm.wx.oauth;

import com.fongwell.satchi.crm.wx.account.WeixinAccountRepository;
import com.fongwell.satchi.crm.wx.sdk.token.TokenStore;
import com.fongwell.satchi.crm.wx.user.store.WxUserStore;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.mp.WeixinProxy;
import com.foxinmy.weixin4j.mp.api.OauthApi;
import com.foxinmy.weixin4j.mp.model.OauthToken;
import com.foxinmy.weixin4j.mp.model.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by docker on 10/20/17.
 */
public class WxAuthenticationProvider implements AuthenticationProvider {

    private WeixinAccountRepository weixinAccountRepository;

    private TokenStore wxTokenStore;

    private WxUserStore wxUserStore;

    public WxAuthenticationProvider(final WeixinAccountRepository weixinAccountRepository, final TokenStore wxTokenStore, final WxUserStore wxUserStore) {
        this.weixinAccountRepository = weixinAccountRepository;
        this.wxTokenStore = wxTokenStore;
        this.wxUserStore = wxUserStore;
    }

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String code = (String) authentication.getCredentials();


        WxAuthenticationToken token = (WxAuthenticationToken) authentication;

        OauthApi oauthApi = new WeixinProxy(weixinAccountRepository.findAccount(token.getClient()), wxTokenStore).getOauthApi();

        OauthToken oauthToken;
        try {
            oauthToken = oauthApi.getAuthorizationToken(code);
        } catch (WeixinException e) {
            throw new BadCredentialsException("Unable to get authorization token", e);
        }
        User user;
        try {
            user = oauthApi.getAuthorizationUser(oauthToken);
        } catch (WeixinException e) {
            throw new BadCredentialsException("Unable to get authorization user", e);
        }

        User existingUser = wxUserStore.findUser(user.getOpenId());
        if (existingUser == null) {
            wxUserStore.createUser(user);
        } else {
            if (user.isSubscribe()) {
                wxUserStore.saveUser(user);
            }
        }

        return new WxAuthenticationToken(new WxUserDetails(user));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return WxAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
