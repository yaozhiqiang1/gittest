package com.fongwell.satchi.crm.core.resource.domain.aggregate;

import com.fongwell.satchi.crm.core.resource.domain.value.ResourceType;
import com.fongwell.satchi.crm.core.support.id.Snowflake;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by docker on 4/24/18.
 */
@Entity
@Table(name = "crm_resource", indexes = @Index(name = "crm_resource_user_id_idx", columnList = "user_id"))
public class Resource implements Serializable {

    @Id
    private long id;

    private String key;

    @Column(name = "url", columnDefinition = "text")
    private String url;

    private Date createdDate;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    private ResourceType type;

    public Resource(final Long userId, final String key, final String url, final ResourceType type) {
        this.userId = userId;
        this.key = key;
        this.url = url;
        this.type = type;
        this.id = Snowflake.id();
        this.createdDate = new Date();
    }

    Resource() {
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(final ResourceType type) {
        this.type = type;
    }
}
