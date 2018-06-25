package com.fongwell.satchi.crm.wx.user.store;

import com.foxinmy.weixin4j.mp.model.User;

/**
 * Created by docker on 10/21/17.
 */
public interface WxUserStore {

    User findUser(String openId);

    void createUser(User user);

    void saveUser(User user);
}
