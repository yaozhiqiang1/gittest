package com.fongwell.satchi.crm.api.audit;

import com.fongwell.infrastructure.event.DomainEvent;
import com.fongwell.infrastructure.event.DomainEventEntityHandler;
import com.fongwell.infrastructure.event.store.EventStore;

/**
 * Created by docker on 3/28/18.
 */
public class NoopEventStore implements EventStore {
    @Override
    public long storeEvent(final DomainEvent event) {
        return 0;
    }

    @Override
    public void confirmEventPublished(final long id) {

    }

    @Override
    public void fetchUnpublishedEvents(final DomainEventEntityHandler handler, final int fetchSize) {

    }
}
