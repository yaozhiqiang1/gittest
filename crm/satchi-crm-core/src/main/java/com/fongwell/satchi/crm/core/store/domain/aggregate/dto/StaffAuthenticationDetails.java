package com.fongwell.satchi.crm.core.store.domain.aggregate.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by docker on 5/21/18.
 */
public class StaffAuthenticationDetails extends User {

    private long staffId;

    private Long storeId;

    public StaffAuthenticationDetails(long staffId, Long storeId, final String username, final String password, final Collection<? extends GrantedAuthority> authorities) {
        super(username, password == null ? "" : password, authorities);
        this.staffId = staffId;
        this.storeId = storeId;
    }

    public long getStaffId() {
        return staffId;
    }

    public Long getStoreId() {
        return storeId;
    }
}
