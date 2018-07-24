package com.fongwell.satchi.crm.wx.oauth;

import com.fongwell.satchi.crm.wx.account.WeixinAccountRepository;
import com.fongwell.satchi.crm.wx.sdk.token.TokenStore;
import com.foxinmy.weixin4j.mp.WeixinProxy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by docker on 4/27/18.
 */
@Component("wxRedirectStrategy")
@ConditionalOnProperty(name = "wx.enabled", havingValue = "true")
public class DefaultWxRedirectStrategy implements WxRedirectStrategy {


    @Resource(name = "weixinAccountRepository")
    private WeixinAccountRepository weixinAccountRepository;
    @Resource(name = "wxTokenStore")
    private TokenStore wxTokenStore;


    @Override
    public void redirect(String client, final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        String redirectUri = request.getParameter("redirect_uri");
        if (redirectUri == null) {
            throw new BadCredentialsException("Missing redirect_uri!");
        }

        String state = request.getParameter("state");

        if (state == null) {
            state = "";
        }
        String scope = request.getParameter("scope");
        if (scope == null) {
            scope = "snsapi_userinfo";
        }


        String url = new WeixinProxy(weixinAccountRepository.findAccount(client), wxTokenStore).getOauthApi().getUserAuthorizationURL(redirectUri, state, scope);
        response.sendRedirect(url);

    }
}
