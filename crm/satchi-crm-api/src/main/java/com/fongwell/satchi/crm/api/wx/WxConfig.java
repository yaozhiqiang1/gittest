package com.fongwell.satchi.crm.api.wx;

import com.fongwell.satchi.crm.wx.account.WxConfiguration;
import com.fongwell.satchi.crm.wx.oauth.ClientIdExtractor;
import com.fongwell.satchi.crm.wx.oauth.DefaultClientIdExtractor;
import com.fongwell.satchi.crm.wx.oauth.client.WxClientDetailsService;
import com.fongwell.satchi.crm.wx.oauth.token.WxUserTokenController;
import com.fongwell.satchi.crm.wx.sdk.token.JpaTokenStore;
import com.fongwell.satchi.crm.wx.sdk.token.TokenEntityRepository;
import com.fongwell.satchi.crm.wx.sdk.token.TokenStore;
import com.fongwell.satchi.crm.wx.user.binding.store.JpaWxUserBindingRepsitory;
import com.fongwell.satchi.crm.wx.user.binding.store.JpaWxUserBindingStore;
import com.fongwell.satchi.crm.wx.user.binding.store.WxUserBindingStore;
import com.fongwell.satchi.crm.wx.user.store.JpaWxUserStore;
import com.fongwell.satchi.crm.wx.user.store.WxUserEntityRepository;
import com.fongwell.satchi.crm.wx.user.store.WxUserStore;
import com.fongwell.satchi.crm.wx.wxpay.WxPayConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by docker on 4/26/18.
 */
@Configuration
@AutoConfigureAfter({WxPayConfiguration.class, WxConfiguration.class})
public class WxConfig {


    @Value("${wx.appId}")
    private String appId;

    @Resource(name = "wxUserEntityRepository")
    private WxUserEntityRepository wxUserEntityRepository;

    @Resource(name = "tokenEntityRepository")
    private TokenEntityRepository tokenEntityRepository;

    public static void main(String[] args){
        System.out.println(new BCryptPasswordEncoder().encode("1"));
    }


    @Bean
    public WxUserBindingStore wxUserBindingStore(@Autowired JpaWxUserBindingRepsitory jpaWxUserBindingRepsitory) {
        return new JpaWxUserBindingStore(jpaWxUserBindingRepsitory);
    }

    @Bean
    public DefaultClientIdExtractor clientIdExtractor() {
        return new DefaultClientIdExtractor(appId);
    }

    @Bean
    public WxUserStore wxUserStore() {
        return new JpaWxUserStore(wxUserEntityRepository);
    }


    @Bean
    public WxClientDetailsService wxClientDetailsService() {
        return new WxClientDetailsService("satchi-crm-wx");
    }

    @Bean
    public TokenStore wxTokenStore() {
        return new JpaTokenStore(tokenEntityRepository);
    }


    @RestController
    @RequestMapping("/oauth/wx")
    public static class XinfuWxUserTokenController extends WxUserTokenController {

        @Resource(name = "tokenStore")
        private org.springframework.security.oauth2.provider.token.TokenStore tokenStore;

        @Resource(name = "jwtAccessTokenConverter")
        private JwtAccessTokenConverter jwtAccessTokenConverter;

        public XinfuWxUserTokenController(
                @Autowired WxClientDetailsService wxClientDetailsService,
                @Autowired ClientIdExtractor clientIdExtractor) {
            super(clientIdExtractor, wxClientDetailsService);


        }

        @Override
        public void afterPropertiesSet() throws Exception {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setClientDetailsService(this.wxClientDetailsService);
            tokenServices.setTokenEnhancer(jwtAccessTokenConverter);
            tokenServices.setTokenStore(tokenStore);
            setTokenServices(tokenServices);
            super.afterPropertiesSet();
        }
    }


}
