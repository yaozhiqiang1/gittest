package com.fongwell.satchi.crm.wx.oauth.token;

import com.fongwell.satchi.crm.wx.oauth.ClientIdExtractor;
import com.fongwell.satchi.crm.wx.oauth.WxAuthenticationToken;
import com.fongwell.satchi.crm.wx.oauth.client.WxClientDetailsService;
import com.fongwell.support.utils.Assert;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by docker on 10/20/17.
 */
public class WxUserTokenController implements InitializingBean {


    protected ClientIdExtractor clientIdExtractor;

    protected WxClientDetailsService wxClientDetailsService;

    protected AuthorizationServerTokenServices tokenServices;

    public WxUserTokenController(ClientIdExtractor clientIdExtractor, WxClientDetailsService wxClientDetailsService) {

        this.wxClientDetailsService = wxClientDetailsService;
        this.clientIdExtractor = clientIdExtractor;
    }

    public void setTokenServices(AuthorizationServerTokenServices tokenServices) {
        this.tokenServices = tokenServices;
    }

    @RequestMapping(value = "")
    public OAuth2AccessToken getAccessToken(ServletRequest request) throws IOException, ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof WxAuthenticationToken) {

            WxAuthenticationToken wxAuthenticationToken = (WxAuthenticationToken) authentication;
            if (wxAuthenticationToken.isAuthenticated()) {
                String clientId = clientIdExtractor.extract((HttpServletRequest) request);
                if (clientId == null) {
                    throw new BadCredentialsException("Unable to extract clientId from request!");
                }

                ClientDetails clientDetails = wxClientDetailsService.loadClientByClientId(clientId);

                OAuth2Request oAuth2Request = new OAuth2Request(Collections.<String, String>emptyMap(), clientDetails.getClientId(), clientDetails.getAuthorities(), true, clientDetails.getScope(),
                        clientDetails.getResourceIds(), null, null, null);

                OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, wxAuthenticationToken);

                return tokenServices.createAccessToken(oAuth2Authentication);


            }


        }
        return null;


    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(tokenServices, "tokenServices");
    }
}
