package com.fongwell.satchi.crm.core.customer.domain.entity;

import java.io.Serializable;

/**
 * Created by docker on 5/24/18.
 */

public class ImportantCustomerId implements Serializable {

    private long customerId;
    private long storeId;

    public ImportantCustomerId(final long customerId, final long storeId) {
        this.customerId = customerId;
        this.storeId = storeId;
    }
    ImportantCustomerId(){}

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ImportantCustomerId that = (ImportantCustomerId) o;

        if (customerId != that.customerId) return false;
        return storeId == that.storeId;

    }

    @Override
    public int hashCode() {
        int result = (int) (customerId ^ (customerId >>> 32));
        result = 31 * result + (int) (storeId ^ (storeId >>> 32));
        return result;
    }
}
