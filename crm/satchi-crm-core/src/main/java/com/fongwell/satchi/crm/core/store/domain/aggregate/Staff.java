package com.fongwell.satchi.crm.core.store.domain.aggregate;

import com.fongwell.satchi.crm.core.store.domain.aggregate.dto.StaffData;
import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Created by docker on 5/21/18.
 */
@Entity
@Table(name = "crm_staff", indexes = @Index(name = "crm_staff_idx", columnList = "store_id"))
public class Staff extends AbstractAggregateRoot {

    @Column(name = "store_id", updatable = false)
    private Long storeId;

    private String name;

    private String number;

    private String mobile;

    private String position;

    @Column(name = "note", columnDefinition = "text")
    private String note;

    public Staff(Long storeId, StaffData data) {

        this.storeId = storeId;
        update(data);
    }

    Staff() {
    }

    public void update(StaffData data) {
        this.name = data.getName();
        this.number = data.getNumber();
        this.mobile = data.getMobile();
        this.note = data.getNote();
        this.position = data.getPosition();
    }

    public Long getStoreId() {
        return storeId;
    }
}
