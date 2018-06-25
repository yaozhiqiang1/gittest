package com.fongwell.satchi.crm.wx.oauth.client;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

/**
 * Created by docker on 10/21/17.
 */
public class WxClientDetailsService implements ClientDetailsService {

    private String resourceIds;

    public WxClientDetailsService(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return new WxClientDetails(clientId, resourceIds);
    }
}
