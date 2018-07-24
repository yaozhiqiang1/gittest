package com.fongwell.satchi.crm.wx.user.binding.store;

/**
 * Created by docker on 4/27/18.
 */
public class JpaWxUserBindingStore implements WxUserBindingStore {

    private JpaWxUserBindingRepsitory jpaBindingRepsitory;

    public JpaWxUserBindingStore(
            final JpaWxUserBindingRepsitory jpaBindingRepsitory) {
        this.jpaBindingRepsitory = jpaBindingRepsitory;
    }

    @Override
    public Long findBinding(final String wxId) {
        JpaBinding binding = jpaBindingRepsitory.findOne(wxId);
        return binding == null ? null : binding.getTargetId();
    }

    @Override
    public String findBindingByTargetId(final long targetId) {
        JpaBinding binding = jpaBindingRepsitory.findOneByTargetId(targetId);
        return binding == null ? null : binding.getWxId();
    }

    @Override
    public void createBinding(final String wxId, final Long targetId) {

        jpaBindingRepsitory.save(new JpaBinding(wxId, targetId));

    }

    @Override
    public void deleteBinding(final String wxId) {
        jpaBindingRepsitory.delete(wxId);

    }
}
