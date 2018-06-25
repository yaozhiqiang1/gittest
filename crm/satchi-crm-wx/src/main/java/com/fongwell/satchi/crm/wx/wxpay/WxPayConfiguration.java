package com.fongwell.satchi.crm.wx.wxpay;

import com.foxinmy.weixin4j.model.WeixinPayAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by docker on 4/26/18.
 */
@Configuration
public class WxPayConfiguration {


    @Configuration
    @ConditionalOnProperty(name = "wx.pay.account.repository.inmemory.enabled", havingValue = "true")
    public static class InMemoryWxPayAccountRepositoryConfig {


        @Autowired
        private InMemoryAccounts accounts;

        @Bean
        public WxPayAccountRepository wxPayAccountRepository() {
            return new InMemoryWxPayAccountRepository(accounts.getAccountsMap());
        }

        @Component
        @ConfigurationProperties(prefix = "wx.pay.account.repository.inmemory")
        public static class InMemoryAccounts {

            private Map<String, Map<String, String>> accounts;

            public Map<String, WeixinPayAccount> getAccountsMap() {
                if (accounts == null || accounts.isEmpty()) {
                    return Collections.emptyMap();
                }

                Map<String, WeixinPayAccount> result = new HashMap<>();
                for (final Map.Entry<String, Map<String, String>> entry : accounts.entrySet()) {
                    Map<String, String> value = entry.getValue();
                    WeixinPayAccount account = new WeixinPayAccount(value.get("appId"), value.get("appSecret"), value.get("paySignKey")
                            , value.get("mchId"), value.get("certificateKey"), value.get("certificateFile")
                            , value.get("deviceInfo"), value.get("partnerId"), value.get("subId")
                            , value.get("subMchId"));

                    result.put(entry.getKey(), account);
                }

                return result;
            }


            public Map<String, Map<String, String>> getAccounts() {
                return accounts;
            }

            public void setAccounts(final Map<String, Map<String, String>> accounts) {
                this.accounts = accounts;
            }
        }
    }
}
