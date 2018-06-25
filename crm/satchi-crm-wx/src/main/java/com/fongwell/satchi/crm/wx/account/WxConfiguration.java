package com.fongwell.satchi.crm.wx.account;

import com.foxinmy.weixin4j.model.WeixinAccount;
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
@ConditionalOnProperty(name = "wx.enabled", havingValue = "true")
public class WxConfiguration {


    @Configuration
    public static class InMemoryWxAccountRepositoryConfig {


        @Autowired
        private InMemoryAccounts accounts;

        @Bean
        @ConditionalOnProperty(name = "wx.account.repository.inmemory.enabled", havingValue = "true")
        public WeixinAccountRepository weixinAccountRepository() {
            return new InMemoryWeixinAccountRepository(accounts.getAccountsMap());
        }

        @Component
        @ConfigurationProperties(prefix = "wx.account.repository.inmemory")
        public static class InMemoryAccounts {

            private Map<String, Map<String, String>> accounts;

            public Map<String, WeixinAccount> getAccountsMap() {
                if (accounts == null || accounts.isEmpty()) {
                    return Collections.emptyMap();
                }

                Map<String, WeixinAccount> result = new HashMap<>();
                for (final Map.Entry<String, Map<String, String>> entry : accounts.entrySet()) {
                    Map<String, String> value = entry.getValue();

                    result.put(entry.getKey(), new WeixinAccount(value.get("appId"), value.get("appSecret")));
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
