package com.fongwell.satchi.crm.core.pricing;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by docker on 4/23/18.
 */
public class PricingContext implements Serializable {

    private long customerId;

    private Map<Long, Integer> skus;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final long customerId) {
        this.customerId = customerId;
    }

    public Map<Long, Integer> getSkus() {
        return skus;
    }

    public void setSkus(final Map<Long, Integer> skus) {
        this.skus = skus;
    }
}
