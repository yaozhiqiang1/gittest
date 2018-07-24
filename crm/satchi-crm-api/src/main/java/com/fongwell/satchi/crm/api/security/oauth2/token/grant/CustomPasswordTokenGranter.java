package com.fongwell.satchi.crm.api.security.oauth2.token.grant;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

/**
 * Created by docker on 5/21/18.
 */
public class CustomPasswordTokenGranter extends ResourceOwnerPasswordTokenGranter {


    public CustomPasswordTokenGranter(final AuthenticationManager authenticationManager,
                                      final AuthorizationServerTokenServices tokenServices,
                                      final ClientDetailsService clientDetailsService,
                                      final OAuth2RequestFactory requestFactory, final String grantType) {
        super(authenticationManager, tokenServices, clientDetailsService, requestFactory, grantType);
    }
}
