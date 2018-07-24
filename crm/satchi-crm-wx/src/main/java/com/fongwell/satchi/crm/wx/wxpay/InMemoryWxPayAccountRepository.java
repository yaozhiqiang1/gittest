package com.fongwell.satchi.crm.wx.wxpay;

import com.fongwell.support.utils.Assert;
import com.foxinmy.weixin4j.model.WeixinPayAccount;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by docker on 4/26/18.
 */

public class InMemoryWxPayAccountRepository implements WxPayAccountRepository {

    private final Map<String, WeixinPayAccount> accounts;


    public InMemoryWxPayAccountRepository(final Map<String, WeixinPayAccount> accounts) {
        this.accounts = new ConcurrentHashMap<>(accounts);
    }

    public Map<String, WeixinPayAccount> getAccounts() {
        return accounts;
    }

    @Override
    public WeixinPayAccount findAccount(final String identifier) {
        WeixinPayAccount account = accounts.get(identifier);
        Assert.notNull(account, "WeixinPayAccount for: " + identifier);
        return account;
}
}
