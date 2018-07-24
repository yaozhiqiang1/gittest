package com.fongwell.satchi.crm.core.tag.domain;

import com.fongwell.satchi.crm.core.support.ddd.Auditable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by docker on 5/17/18.
 */
@Entity
@Table(name = "crm_tag")
public class Tag extends Auditable {

    @Id
    private String id;


    private Integer referenceCount;

    @Column(name = "createdDate", updatable = false)
    private Date createdDate;
    @Version
    private Integer version;


    public Tag(final String id) {
        this.id = id;
        referenceCount = 0;
        this.createdDate = new Date();
    }

    Tag() {
    }

    public void incrementReferences() {
        if (referenceCount == null) {
            referenceCount = 1;
        } else {
            referenceCount++;
        }

    }

    public void decrementReferences() {
        if (referenceCount != null) {
            referenceCount--;
        }
    }
}
