package com.fongwell.satchi.crm.core.order.checkout;

import com.fongwell.satchi.crm.core.customer.domain.value.AddressValue;
import com.fongwell.satchi.crm.core.order.dto.CheckoutRequest;
import com.fongwell.satchi.crm.core.product.dto.SkuInfo;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by docker on 4/23/18.
 */
public class CheckoutContext implements Serializable {

    private long customerId;
    private CheckoutRequest request;
    private Map<SkuInfo, Integer> skus;
    private AddressValue address;

    public CheckoutContext(final long customerId, final CheckoutRequest request, final Map<SkuInfo, Integer> skus, final AddressValue address) {
        this.customerId = customerId;
        this.request = request;
        this.skus = skus;
        this.address = address;
    }

    public long getCustomerId() {
        return customerId;
    }

    public Map<SkuInfo, Integer> getSkus() {
        return skus;
    }

    public CheckoutRequest getRequest() {
        return request;
    }

    public AddressValue getAddress() {
        return address;
    }
}
