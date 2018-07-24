package com.fongwell.satchi.crm.wx.oauth;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by docker on 10/20/17.
 */
public class DefaultClientIdExtractor implements ClientIdExtractor {

    private String clientId;

    public DefaultClientIdExtractor(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String extract(HttpServletRequest request) {
        return clientId;
    }
}
