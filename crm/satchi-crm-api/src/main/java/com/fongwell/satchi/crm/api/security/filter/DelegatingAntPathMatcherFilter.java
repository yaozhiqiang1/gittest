package com.fongwell.satchi.crm.api.security.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by docker on 12/4/17.
 */
public class DelegatingAntPathMatcherFilter extends AntPathMatcherFilter {


    private Filter delegate;

    public DelegatingAntPathMatcherFilter(String pattern, Filter delegate) {
        super(pattern);
        this.delegate = delegate;
    }

    @Override
    protected void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        delegate.doFilter(request, response, chain);

    }
}
