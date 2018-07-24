package com.fongwell.satchi.crm.api;

import com.fongwell.satchi.crm.api.wx.WxConfig;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.Collections;

/**
 * Created by javcly on 12/13/16.
 */

@Configuration
@AutoConfigureAfter({WxConfig.class})
public class ApiSecurity {


    @Resource(name = "corsFilter")
    private Filter corsFilter;

    @Bean
    public FilterRegistrationBean corsRegisterFilter() {

        FilterRegistrationBean bean = new FilterRegistrationBean(corsFilter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        bean.setUrlPatterns(Collections.singletonList("/*"));
        return bean;

    }


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() throws IOException, InvalidKeyException {
        JwtAccessTokenConverter result = new JwtAccessTokenConverter();

        result.setKeyPair(new KeyStoreKeyFactory(new ClassPathResource("crm.jks"), "undead2009".toCharArray()).getKeyPair("xinfu"));
        return result;
    }


    @Bean
    public TokenStore tokenStore() throws IOException, InvalidKeyException {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }


}
