package com.fongwell.satchi.crm.wx.oauth.filter;

import com.fongwell.satchi.crm.wx.oauth.WxAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by docker on 10/21/17.
 */
public class UrlParameterWxAuthenticationFilter extends GenericFilterBean {

    private UserDetailsService wxUserDetailsService;

    public UrlParameterWxAuthenticationFilter(UserDetailsService wxUserDetailsService) {
        this.wxUserDetailsService = wxUserDetailsService;
    }

    @Override

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String id = request.getParameter("id");
        if (id == null) {
            throw new BadCredentialsException("missing id parameter!");
        }

        UserDetails userDetails = wxUserDetailsService.loadUserByUsername(id);
        SecurityContextHolder.getContext().setAuthentication(new WxAuthenticationToken(userDetails));
        chain.doFilter(request, response);

    }
}
