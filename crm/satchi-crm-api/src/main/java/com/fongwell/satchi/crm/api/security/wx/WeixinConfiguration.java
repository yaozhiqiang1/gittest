//package com.fongwell.satchi.crm.api.security.wx;
//
//import com.fongwell.crm.wx.oauth.client.service.WxClientDetailsService;
//import com.fongwell.crm.wx.oauth.token.WxAuthenticationToken;
//import com.fongwell.crm.wx.oauth.token.WxTokenStoreImpl;
//import com.fongwell.crm.wx.oauth.token.extractor.ClientIdExtractor;
//import com.fongwell.crm.wx.oauth.token.extractor.DefaultClientIdExtractor;
//import com.foxinmy.weixin4j.cache.CacheStorager;
//import com.foxinmy.weixin4j.model.Token;
//import com.foxinmy.weixin4j.model.WeixinAccount;
//import com.foxinmy.weixin4j.model.WeixinPayAccount;
//import com.foxinmy.weixin4j.mp.WeixinProxy;
//import com.foxinmy.weixin4j.payment.WeixinPayProxy;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.ClientDetails;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.OAuth2Request;
//import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Collections;
//
///**
// * Created by roman on 18-4-19.
// */
//@Configuration
//public class WeixinConfiguration {
//
//    @Value("${wx.appId}")
//    private String appId;
//
//    @Value("${wx.appSecret}")
//    private String appSecret;
//
//    @Value("${wx.mchId}")
//    private String mchId;
//
//    @Value("${wx.secret}")
//    private String secret;
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @Bean
//    public CacheStorager<Token> wxStorager(){
//        return new WxTokenStoreImpl(sessionFactory);
//    }
//
//    @Bean
//    public WeixinProxy wxProxy(){
//        return new WeixinProxy(new WeixinAccount(appId,appSecret),wxStorager());
//    }
//
//    @Bean
//    public WeixinPayProxy wxPayProxy(){
//        return new WeixinPayProxy(new WeixinPayAccount(appId,secret,mchId));
//    }
//
//    @Bean
//    public ClientIdExtractor clientIdExtractor(){
//        return new DefaultClientIdExtractor(appId);
//    }
//
//    @Bean
//    public WxClientDetailsService wxClientDetailsService(){
//        return new WxClientDetailsService("satchi-crm-wx");
//    }
//
//    @Configuration
//    @RestController("/api/wx")
//    public static class WxController implements InitializingBean {
//
//        @Autowired
//        private WxClientDetailsService wxClientDetailsService;
//
//        @Autowired
//        private ClientIdExtractor clientIdExtractor;
//
//        @Autowired
//        private TokenStore tokenStore;
//
//        @Autowired
//        private TokenEnhancer tokenEnhancer;
//
//        private AuthorizationServerTokenServices tokenServices;
//
//        @RequestMapping(value = "", method = {RequestMethod.POST,RequestMethod.GET})
//        public OAuth2AccessToken getAccessToken(ServletRequest request) throws IOException, ServletException {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (authentication instanceof WxAuthenticationToken) {
//
//                WxAuthenticationToken wxAuthenticationToken = (WxAuthenticationToken) authentication;
//                if (wxAuthenticationToken.isAuthenticated()) {
//                    String clientId = clientIdExtractor.extract((HttpServletRequest) request);
//                    if (clientId == null) {
//                        throw new BadCredentialsException("Unable to extract clientId from request!");
//                    }
//
//                    ClientDetails clientDetails = wxClientDetailsService.loadClientByClientId(clientId);
//
//                    OAuth2Request oAuth2Request = new OAuth2Request(Collections.<String, String>emptyMap(), clientDetails.getClientId(), clientDetails.getAuthorities(), true, clientDetails.getScope(),
//                            clientDetails.getResourceIds(), null, null, null);
//
//                    OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, wxAuthenticationToken);
//
//                    return tokenServices.createAccessToken(oAuth2Authentication);
//                }
//            }
//            return null;
//        }
//
//        public void afterPropertiesSet() throws Exception {
//            DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//            defaultTokenServices.setTokenStore(tokenStore);
//            defaultTokenServices.setClientDetailsService(wxClientDetailsService);
//            defaultTokenServices.setTokenEnhancer(tokenEnhancer);
//            tokenServices = defaultTokenServices;
//        }
//    }
//}
