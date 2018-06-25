package com.fongwell.satchi.crm.wx.user.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 4/26/18.
 */
@Repository
public interface WxUserEntityRepository extends JpaRepository<WxUserEntity, String> {
}
