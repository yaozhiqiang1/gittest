package com.fongwell.satchi.crm.wx.oauth;

import com.fongwell.satchi.crm.wx.user.store.WxUserStore;
import com.foxinmy.weixin4j.mp.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by docker on 10/21/17.
 */
public class WxUserDetailsService implements UserDetailsService {

    private WxUserStore wxUserStore;

    public WxUserDetailsService(WxUserStore wxUserStore) {
        this.wxUserStore = wxUserStore;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = wxUserStore.findUser(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new WxUserDetails(user);
    }
}
