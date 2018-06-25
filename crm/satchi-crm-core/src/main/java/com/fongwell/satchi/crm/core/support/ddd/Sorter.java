package com.fongwell.satchi.crm.core.support.ddd;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by roman on 18-3-24.
 */
@MappedSuperclass
public abstract class Sorter extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected boolean active;

    @Version
    protected Integer version;

    protected Boolean deleted;

    public Sorter() {
        this.createdDate = new Date();
    }

    public void preUpdate() {
        if (version == null) {
            version = 1;
        } else {
            version++;
        }
    }


    @Column(name = "orderNumber",length = 3)
    protected int orderNumber;

    public boolean isActive() {
        return active;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getId() {
        return id;
    }

    public Date getCreatedDate(){
        return createdDate;
    }


}
