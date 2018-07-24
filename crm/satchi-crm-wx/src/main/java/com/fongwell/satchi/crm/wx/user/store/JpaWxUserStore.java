package com.fongwell.satchi.crm.wx.user.store;

import com.foxinmy.weixin4j.mp.model.User;

/**
 * Created by docker on 10/21/17.
 */

public class JpaWxUserStore implements WxUserStore {

    private WxUserEntityRepository wxUserEntityRepository;

    public JpaWxUserStore(WxUserEntityRepository wxUserEntityRepository) {
        this.wxUserEntityRepository = wxUserEntityRepository;
    }


    @Override
    public User findUser(String openId) {

        WxUserEntity wxUser = wxUserEntityRepository.findOne(openId);

        if (wxUser == null) {
            return null;
        }

        User user = new User();
        user.setOpenId(wxUser.getId());
        user.setNickName(wxUser.getNickName());
        user.setProvince(wxUser.getProvince());
        user.setCity(wxUser.getCity());
        user.setCountry(wxUser.getCountry());
        user.setHeadimgurl(wxUser.getHeadimgurl());
        user.setSubscribe(wxUser.isSubscribe());
        user.setSubscribeTime(wxUser.getSubscribeTime());
        user.setLanguage(wxUser.getLanguage());
        user.setUnionId(wxUser.getUnionId());
        user.setRemark(wxUser.getRemark());
        user.setGroupId(wxUser.getGroupId());


        return user;
    }

    @Override
    public void createUser(User user) {

        wxUserEntityRepository.save(new WxUserEntity(user));

    }

    @Override
    public void saveUser(User user) {
        wxUserEntityRepository.save(new WxUserEntity(user));

    }
}


