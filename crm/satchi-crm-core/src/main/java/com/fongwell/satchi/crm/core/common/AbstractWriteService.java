package com.fongwell.satchi.crm.core.common;

import com.fongwell.satchi.crm.core.support.ddd.AggregateFactory;
import com.fongwell.satchi.crm.core.support.ddd.AggregateRoot;
import com.fongwell.support.utils.Assert;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by docker on 11/26/17.
 */
public abstract class AbstractWriteService<T extends AggregateRoot, ID extends Serializable, DTO extends Serializable>
        implements WriteService<T, ID, DTO> {

    private final Class<T> type;
    @Resource(name = "aggregateFactory")
    protected AggregateFactory aggregateFactory;

    protected AbstractWriteService(Class<T> type) {
        this.type = type;
    }

    protected abstract JpaRepository<T, ID> getRepository();

    protected AggregateFactory getAggregateFactory() {
        return aggregateFactory;
    }

    @Override
    public T create(DTO data) {
        T aggregate = (T) getAggregateFactory().createAggregate(data, type);
        getRepository().save(aggregate);
        return aggregate;
    }

    @Override
    public T update(ID id, DTO data) {

        T aggregate = getRepository().findOne(id);
        Assert.notNull(aggregate, "aggregate:" + id);
        getAggregateFactory().mergeAggregate(data, aggregate);
        getRepository().save(aggregate);
        return aggregate;
    }

    @Override
    public void delete(ID id) {
        getRepository().delete(id);
    }
}
