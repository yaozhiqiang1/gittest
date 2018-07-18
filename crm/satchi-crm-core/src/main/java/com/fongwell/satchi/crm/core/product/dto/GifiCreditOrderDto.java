package com.fongwell.satchi.crm.core.product.dto;

import java.math.BigDecimal;

/**
 * Date:2018/7/17
 * Author: yaozhiqiang
 * Desc:
 */
public class GifiCreditOrderDto{

    private Long productId;

    private Integer credit;

    private Long orderid;

    private BigDecimal price;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
