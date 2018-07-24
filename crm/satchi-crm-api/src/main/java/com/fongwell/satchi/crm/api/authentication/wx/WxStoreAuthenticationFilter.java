package com.fongwell.satchi.crm.api.authentication.wx;

import com.fongwell.satchi.crm.api.security.filter.AntPathMatcherFilter;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.Authentication;
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
 * Created by docker on 5/28/18.
 */
public class WxStoreAuthenticationFilter extends AntPathMatcherFilter {

    private UserDetailsChecker userDetailsChecker = new AccountStatusUserDetailsChecker();

    private WxStoreUserDetailsService wxStoreUserDetailsService;

    public WxStoreAuthenticationFilter(final String pattern, final WxStoreUserDetailsService wxStoreUserDetailsService) {
        super(pattern);
        this.wxStoreUserDetailsService = wxStoreUserDetailsService;
    }

    public void setUserDetailsChecker(final UserDetailsChecker userDetailsChecker) {
        this.userDetailsChecker = userDetailsChecker;
    }

    @Override
    protected void execute(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication instanceof OAuth2Authentication) {

            String username = (String) ((OAuth2Authentication) authentication).getUserAuthentication().getPrincipal();
            UserDetails userDetails = wxStoreUserDetailsService.loadUserByUsername(username);
            if (userDetails != null) {
                userDetailsChecker.check(userDetails);
            }
            SecurityContextHolder.getContext().setAuthentication(new WxStoreAuthentication(null, userDetails));
        }


        chain.doFilter(request, response);
    }
}
