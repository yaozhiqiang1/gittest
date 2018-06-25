package com.fongwell.satchi.crm.core.support.event;

import com.fongwell.infrastructure.event.DomainEvent;

import java.util.Date;

/**
 * Created by roman on 18-3-23.
 */
public abstract class CrmEvent implements DomainEvent {
    private Date date;

    public CrmEvent() {
        this.date = new Date();
    }

    @Override
    public String getId() {
        return "CrmEvent";
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public Long getVersion() {
        return date.getTime();
    }

    @Override
    public String getDest() {
        return null;
    }

    @Override
    public boolean isAudit() {
        return true;
    }
}
