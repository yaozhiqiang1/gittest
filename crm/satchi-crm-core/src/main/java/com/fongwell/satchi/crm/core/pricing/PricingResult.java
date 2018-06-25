package com.fongwell.satchi.crm.core.pricing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by docker on 4/23/18.
 */
public class PricingResult implements Serializable {

    public static PricingResult ZERO = new PricingResult(BigDecimal.ZERO, BigDecimal.ZERO, Collections.<ItemPrice>emptyList());

    private BigDecimal price;
    private BigDecimal salePrice;
    private Collection<ItemPrice> itemPrices;

    public PricingResult(final BigDecimal price, final BigDecimal salePrice, final Collection<ItemPrice> itemPrices) {
        this.price = price;
        this.salePrice = salePrice;
        this.itemPrices = itemPrices;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public Collection<ItemPrice> getItemPrices() {
        return itemPrices;
    }
}
