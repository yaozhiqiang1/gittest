package com.fongwell.satchi.crm.api.authentication.wx;

import com.fongwell.satchi.crm.api.security.filter.AntPathMatcherFilter;
import com.fongwell.satchi.crm.core.customer.service.CustomerAuthenticationService;
import com.fongwell.satchi.crm.wx.user.binding.WxUserBindingService;

import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.oauth2.provider.OAuth2Authentication;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;

/**
 * Created by docker on 3/19/18.
 */
public class WxCustomerAuthenticationFilter extends AntPathMatcherFilter {

    private static final GrantedAuthority WX_AUTHRORITY = new SimpleGrantedAuthority("ROLE_WX_USER");

    private UserDetailsChecker userDetailsChecker = new AccountStatusUserDetailsChecker();

    private CustomerAuthenticationService customerAuthenticationService;

    private WxUserBindingService wxUserBindingService;


    public WxCustomerAuthenticationFilter(final String pattern, final CustomerAuthenticationService customerAuthenticationService, final WxUserBindingService wxUserBindingService) {
        super(pattern);
        this.customerAuthenticationService = customerAuthenticationService;
        this.wxUserBindingService = wxUserBindingService;
    }

    public void setUserDetailsChecker(UserDetailsChecker userDetailsChecker) {
        this.userDetailsChecker = userDetailsChecker;
    }

    @Override
    protected void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication instanceof OAuth2Authentication) {
            String wxId = null;
            UserDetails userDetails = null;
            if (authentication.getAuthorities().contains(WX_AUTHRORITY)) {
                wxId = (String) authentication.getPrincipal();
                Long userId = wxUserBindingService.findBinding(wxId);

                if (userId != null) {
                    userDetails = customerAuthenticationService.authenticate(userId);
                } else {
                    SecurityContextHolder.getContext().setAuthentication(new WxCustomerAuthentication(wxId, authentication.getAuthorities()));

                    chain.doFilter(request, response);
                    return;
                }
            } else {

                String username = (String) ((OAuth2Authentication) authentication).getUserAuthentication().getPrincipal();
                userDetails = customerAuthenticationService.authenticate(username);
            }

            if (userDetails != null) {
                userDetailsChecker.check(userDetails);
            }
            SecurityContextHolder.getContext().setAuthentication(new WxCustomerAuthentication(wxId, userDetails));
        }

        chain.doFilter(request, response);


    }
}
