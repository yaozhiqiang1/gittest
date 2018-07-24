package com.fongwell.satchi.crm.wx.account;

import com.foxinmy.weixin4j.model.WeixinAccount;

/**
 * Created by docker on 4/26/18.
 */
public interface WeixinAccountRepository {

    WeixinAccount findAccount(String identifier);
}
