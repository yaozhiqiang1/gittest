package com.fongwell.satchi.crm.wx.user.binding.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 4/27/18.
 */
@Repository
public interface JpaWxUserBindingRepsitory extends JpaRepository<JpaBinding, String> {

    JpaBinding findOneByTargetId(long targetId);

}
