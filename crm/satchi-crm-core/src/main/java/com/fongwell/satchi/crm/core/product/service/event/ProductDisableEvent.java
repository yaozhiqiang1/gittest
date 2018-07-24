package com.fongwell.satchi.crm.core.product.service.event;

import com.fongwell.infrastructure.event.DomainEvent;
import com.fongwell.satchi.crm.core.product.dto.ProductData;
import com.fongwell.satchi.crm.core.support.event.CrmEvent;

import java.util.Collection;

/**
 * Created by roman on 18-4-3.
 */
public class ProductDisableEvent extends CrmEvent {

    private Collection<ProductData> target;

    public ProductDisableEvent(Collection<ProductData> target) {
        this.target = target;
    }

    public Collection<ProductData> getTarget() {
        return target;
    }

    @Override
    public String getAggregateType() {
        return "ProductDisableEvent";
    }
}
