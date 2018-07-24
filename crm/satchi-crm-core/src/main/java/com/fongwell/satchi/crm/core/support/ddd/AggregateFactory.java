package com.fongwell.satchi.crm.core.support.ddd;

/**
 * Created by docker on 11/21/17.
 */
public interface AggregateFactory<E, DTO> {

    E createAggregate(DTO source, Class<E> clazz);

    void mergeAggregate(DTO source, E destination);
}
