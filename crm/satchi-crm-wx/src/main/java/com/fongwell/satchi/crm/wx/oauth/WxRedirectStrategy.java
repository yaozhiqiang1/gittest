package com.fongwell.satchi.crm.wx.oauth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by docker on 4/27/18.
 */
public interface WxRedirectStrategy {

    void redirect(String client, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
