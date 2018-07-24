package com.fongwell.satchi.crm.wx.oauth;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.BadCredentialsException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by docker on 4/27/18.
 */
@ConditionalOnProperty(name = "wx.enabled", havingValue = "false")
public class TestWxRedirectStrategy implements WxRedirectStrategy {
    @Override
    public void redirect(final String client, final HttpServletRequest request, final HttpServletResponse response) throws IOException {

        String redirectUri = request.getParameter("redirect_uri");
        if (redirectUri == null) {
            throw new BadCredentialsException("Missing redirect_uri!");
        }

        String state = request.getParameter("state");

        if (state == null) {
            state = "";
        }

        String user = request.getParameter("user");

        try {
            URIBuilder builder = new URIBuilder(redirectUri);
            builder.addParameter("state", state);
            builder.addParameter("code", user);

            response.sendRedirect(builder.build().toString());

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


    }
}
