package com.fongwell.satchi.crm.api.security.oauth2.token.grant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
 * Created by docker on 12/1/17.
 */
public class MultiPlatformGranter extends AbstractTokenGranter {

    private static final Logger LOGGER = LogManager.getLogger(MultiPlatformGranter.class);


    private final Map<String, TokenGranter> granters;

    private String platformParam = "platform";

    public MultiPlatformGranter(Map<String, TokenGranter> granters, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, "");
        this.granters = granters;
    }


    @Override
    public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {

        String platform = tokenRequest.getRequestParameters().get(platformParam);

        if (platform == null) {

            return null;
        }

        TokenGranter tokenGranter = granters.get(platform);

        if (tokenGranter == null) {
            LOGGER.debug("No TokenGranter found for platform: " + platform);
            return null;
        }

        return tokenGranter.grant(grantType, tokenRequest);


    }


}
