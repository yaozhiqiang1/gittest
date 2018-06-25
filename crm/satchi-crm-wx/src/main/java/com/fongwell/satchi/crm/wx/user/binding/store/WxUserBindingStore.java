package com.fongwell.satchi.crm.wx.user.binding.store;

/**
 * Created by docker on 4/27/18.
 */

public interface WxUserBindingStore {


    Long findBinding(String wxId);

    String findBindingByTargetId(final long targetId);


    void createBinding(String wxId, Long targetId);

    void deleteBinding(String wxId);

}
