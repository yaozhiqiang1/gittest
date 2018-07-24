package com.fongwell.satchi.crm.wx.account;

import com.fongwell.support.utils.Assert;
import com.foxinmy.weixin4j.model.WeixinAccount;

import java.util.Map;

/**
 * Created by docker on 4/26/18.
 */
public class InMemoryWeixinAccountRepository implements WeixinAccountRepository {

    private Map<String, WeixinAccount> accounts;

    public InMemoryWeixinAccountRepository(final Map<String, WeixinAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public WeixinAccount findAccount(final String identifier) {
        WeixinAccount account = accounts.get(identifier);
        Assert.notNull(account, "WeixinAccount: " + identifier);
        return account;
    }
}
