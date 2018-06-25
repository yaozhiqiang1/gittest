package com.fongwell.satchi.crm.wx.user.binding.store;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by docker on 4/27/18.
 */
@Entity
@Table(name="wx_binding")
public class JpaBinding implements Serializable {

    @Id
    private String wxId;

    private Long targetId;

    public JpaBinding(final String wxId, final Long targetId) {
        this.wxId = wxId;
        this.targetId = targetId;
    }

    JpaBinding(){}

    public Long getTargetId() {
        return targetId;
    }

    public String getWxId() {
        return wxId;
    }
}
