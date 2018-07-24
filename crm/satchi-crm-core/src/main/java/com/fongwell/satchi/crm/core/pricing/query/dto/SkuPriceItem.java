package com.fongwell.satchi.crm.core.pricing.query.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by docker on 4/23/18.
 */
public class SkuPriceItem implements Serializable {

    private long id;
    private BigDecimal price;
    private BigDecimal salePrice;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(final BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
}
