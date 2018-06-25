package com.fongwell.satchi.crm.wx.oauth.filter;

import com.fongwell.satchi.crm.wx.oauth.WxAuthenticationToken;
import com.fongwell.satchi.crm.wx.oauth.WxRedirectStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by docker on 10/20/17.
 */
public class WxAuthenticationFilter extends GenericFilterBean {

    private String defaultClient;

    private WxRedirectStrategy wxRedirectStrategy;

    private AuthenticationManager authenticationManager;


    public WxAuthenticationFilter(final String defaultClient, final WxRedirectStrategy wxRedirectStrategy, final AuthenticationManager authenticationManager) {
        this.defaultClient = defaultClient;
        this.wxRedirectStrategy = wxRedirectStrategy;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String code = request.getParameter("code");

        String client = request.getParameter("client");
        if (StringUtils.isBlank(client)) {
            client = defaultClient;
        }

        if (code == null) {
            wxRedirectStrategy.redirect(client, (HttpServletRequest) request, (HttpServletResponse) response);
        } else {
            Authentication authenticate = authenticationManager.authenticate(new WxAuthenticationToken(client, code));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            chain.doFilter(request, response);
        }

    }
}
