package com.fongwell.satchi.crm.wx.user.binding;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by docker on 4/27/18.
 */

@Transactional
public interface WxUserBindingService {

    @Transactional(readOnly = true)
    Long findBinding(String wxId);

    /**
     * 微信号和会员绑定
     * @param wxId
     * @param targetId
     */
    void bind(String wxId, Long targetId);

    /**
     * 接解除微信和会员绑定
     * @param wxId
     */
    void unbind(String wxId);

}
