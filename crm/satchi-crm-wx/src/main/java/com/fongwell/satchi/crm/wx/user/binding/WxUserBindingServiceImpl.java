package com.fongwell.satchi.crm.wx.user.binding;

import com.fongwell.satchi.crm.wx.user.binding.store.WxUserBindingStore;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by docker on 4/27/18.
 */
@Service("wxUserBindingService")
public class WxUserBindingServiceImpl implements WxUserBindingService {

    @Resource(name = "wxUserBindingStore")
    private WxUserBindingStore wxUserBindingStore;

    @Override
    public Long findBinding(final String wxId) {
        return wxUserBindingStore.findBinding(wxId);
    }

    @Override
    public void bind(final String wxId, final Long targetId) {

        Long boundTarget = wxUserBindingStore.findBinding(wxId);

        if (boundTarget == null) {

            String boundWx = wxUserBindingStore.findBindingByTargetId(targetId);

            if (boundWx == null) {

                wxUserBindingStore.createBinding(wxId, targetId);

            } else if (!boundWx.equals(wxId)) {
                throw new DuplicateBindTargetException("specified targetId already bound with another wxId!");
            }


        } else if (boundTarget.longValue() != targetId) {
            throw new WxUserBindingException("specified wxId already bound another target!");

        }


    }


    @Override
    public void unbind(final String wxId) {
        wxUserBindingStore.deleteBinding(wxId);
    }

	@Override
	public String getOpenId(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}
}
