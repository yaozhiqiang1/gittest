package com.fongwell.satchi.crm.core.customer.domain.query;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by docker on 5/18/18.
 */
public class UserTagsDto implements Serializable {
    private long id;
    private Collection<String> tags;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void setTags(final Collection<String> tags) {
        this.tags = tags;
    }
}
