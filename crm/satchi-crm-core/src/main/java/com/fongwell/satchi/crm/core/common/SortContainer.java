package com.fongwell.satchi.crm.core.common;

import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;
import com.fongwell.satchi.crm.core.support.ddd.Sorter;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by roman on 18-3-26.
 */
public abstract class SortContainer<D extends Sorter> extends AbstractAggregateRoot {

    public abstract List<D> getItems();

    public abstract List<D> sorted(List<Long> ids);

    public abstract void sort(List<Long> ids);

    public void active(D param) {
        Splitter splitter = Splitter.build(getItems());
        LinkedHashMap inactive = splitter.getInactive();
        inactive.remove(param.getId());
        LinkedHashMap active = splitter.getActive();
        active.put(param.getId(), param);

        getItems().clear();
        int orderNumber = 1;
        orderNumber = sortNumber(orderNumber,active,getItems());
        sortNumber(orderNumber,inactive,getItems());
    }

    public void inactive(Sorter param) {
        Splitter splitter = Splitter.build(getItems());
        LinkedHashMap active = splitter.getActive();
        active.remove(param.getId());
        LinkedHashMap inactive = splitter.getInactive();
        inactive.put(param.getId(), param);
        getItems().clear();

        int orderNumber = 1;
        orderNumber = sortNumber(orderNumber,active,getItems());
        sortNumber(orderNumber,inactive,getItems());
    }

    private int sortNumber(int orderNumber,LinkedHashMap source,List sorted){
        D current;
        for (Object next : source.values()) {
            current = ((D)next);
            current.setOrderNumber(orderNumber++);
            sorted.add(current);
        }
        return orderNumber;
    }
}
