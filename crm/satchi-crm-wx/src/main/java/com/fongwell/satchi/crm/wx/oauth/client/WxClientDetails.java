package com.fongwell.satchi.crm.wx.oauth.client;

import org.springframework.security.oauth2.provider.client.BaseClientDetails;

/**
 * Created by docker on 10/21/17.
 */
public class WxClientDetails extends BaseClientDetails {


    public WxClientDetails(String clientId, String resourceIds) {
        super(clientId, resourceIds, null, null, null, null);
    }


}
