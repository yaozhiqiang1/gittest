package com.fongwell.satchi.crm.wx.oauth;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by docker on 10/20/17.
 */
public interface ClientIdExtractor {

    String  extract(HttpServletRequest request);
}
