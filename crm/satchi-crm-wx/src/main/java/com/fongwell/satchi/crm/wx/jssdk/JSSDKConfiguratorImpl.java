package com.fongwell.satchi.crm.wx.jssdk;

import com.fongwell.satchi.crm.wx.account.WeixinAccountRepository;
import com.fongwell.support.utils.Assert;
import com.foxinmy.weixin4j.cache.CacheStorager;
import com.foxinmy.weixin4j.jssdk.JSSDKConfigurator;
import com.foxinmy.weixin4j.model.Token;
import com.foxinmy.weixin4j.model.WeixinAccount;
import com.foxinmy.weixin4j.mp.token.WeixinTicketCreator;
import com.foxinmy.weixin4j.mp.token.WeixinTokenCreator;
import com.foxinmy.weixin4j.token.TokenManager;
import com.foxinmy.weixin4j.type.TicketType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by docker on 4/28/18.
 */
@Component("jssdkConfiguratorFactory")
public class JSSDKConfiguratorImpl implements JSSDKConfiguratorFactory {

    @Resource(name = "weixinAccountRepository")
    private WeixinAccountRepository weixinAccountRepository;

    @Resource(name = "wxTokenStore")
    private CacheStorager<Token> wxTokenStore;

    @Override
    public JSSDKConfigurator create(final String client) {

        WeixinAccount account = weixinAccountRepository.findAccount(client);
        Assert.notNull(account, "WeixinAccount:" + client);

        WeixinTicketCreator tickerCreator = new WeixinTicketCreator(TicketType.jsapi, new TokenManager(new WeixinTokenCreator(account.getId(), account.getSecret()), wxTokenStore));
        TokenManager weixinTokenManager = new TokenManager(tickerCreator, wxTokenStore);

        return new JSSDKConfigurator(weixinTokenManager);


    }
}
