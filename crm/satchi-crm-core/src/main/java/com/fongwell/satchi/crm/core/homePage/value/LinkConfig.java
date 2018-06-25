package com.fongwell.satchi.crm.core.homePage.value;

import java.io.Serializable;

/**
 * Created by docker on 5/10/18.
 */
public class LinkConfig implements Serializable {

    private String type;

    private Long id;

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }
}
