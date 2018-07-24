package com.fongwell.satchi.crm.core.support.ddd;


import com.fongwell.satchi.crm.core.support.id.Snowflake;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by docker on 11/28/17.
 */
@MappedSuperclass
public abstract class AbstractAggregateRoot extends Auditable implements AggregateRoot {

    @Id
    protected Long id;

    @Version
    protected Integer version;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    protected Boolean deleted = false;


    public AbstractAggregateRoot() {
        this.id = Snowflake.id();
    }

    public void preUpdate() {
        if (version == null) {
            version = 1;
        } else {
            version++;
        }
    }

    @Override
    public Long getId() {
        return id;
    }
}
