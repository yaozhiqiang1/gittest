package com.fongwell.satchi.crm.wx.wxpay;

import com.foxinmy.weixin4j.model.WeixinPayAccount;

/**
 * Created by docker on 4/26/18.
 */
public interface WxPayAccountRepository {

    WeixinPayAccount findAccount(String identifier);
}
