package com.fongwell.satchi.crm.wx.user.binding;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by docker on 4/27/18.
 */

@Transactional
public interface WxUserBindingService {

    @Transactional(readOnly = true)
    Long findBinding(String wxId);


    void bind(String wxId, Long targetId);

    void unbind(String wxId);

}
