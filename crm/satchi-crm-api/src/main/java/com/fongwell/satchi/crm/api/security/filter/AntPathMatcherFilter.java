package com.fongwell.satchi.crm.api.security.filter;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by docker on 10/29/17.
 */
public abstract class AntPathMatcherFilter extends OncePerRequestFilter {

    private AntPathMatcher matcher;
    private Collection<String> patterns;

    public AntPathMatcherFilter(String pattern) {
        this.matcher = new AntPathMatcher();
        String[] split = pattern.split(",");
        patterns = new HashSet<>();
        for (String path : split) {
            patterns.add(path.trim());

        }

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String url = getRequestPath(request);

        boolean matched = false;

        for (String pattern : patterns) {
            if (matcher.matchStart(pattern, url)) {
                matched = true;
                break;
            }
        }

        if (matched) {
            execute(request, response, filterChain);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private String getRequestPath(HttpServletRequest request) {
        String url = request.getServletPath();

        if (request.getPathInfo() != null) {
            url += request.getPathInfo();
        }

        url = url.toLowerCase();

        return url;
    }

    protected abstract void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException;
}
