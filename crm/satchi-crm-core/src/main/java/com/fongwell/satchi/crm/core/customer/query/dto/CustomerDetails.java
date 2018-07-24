package com.fongwell.satchi.crm.core.customer.query.dto;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by docker on 2/25/18.
 */
public class CustomerDetails implements Serializable {

    private long id;

    private String name;

    private String mobile;

    private String password;

    private Collection<String> permissions;

    private boolean deleted;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }

    public Collection<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(final Collection<String> permissions) {
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
