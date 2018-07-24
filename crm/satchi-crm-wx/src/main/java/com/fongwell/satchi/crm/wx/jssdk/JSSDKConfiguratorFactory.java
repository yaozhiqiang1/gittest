package com.fongwell.satchi.crm.wx.jssdk;

import com.foxinmy.weixin4j.jssdk.JSSDKConfigurator;
import com.foxinmy.weixin4j.mp.token.WeixinTicketCreator;
import com.foxinmy.weixin4j.type.TicketType;

/**
 * Created by docker on 4/28/18.
 */
public interface JSSDKConfiguratorFactory {

    JSSDKConfigurator create(String client);
}
