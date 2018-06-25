package com.fongwell.satchi.crm.core.order.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by docker on 4/19/18.
 */
@Entity
@Table(name = "crm_order_item", indexes = {@Index(name = "crm_order_item_idx", columnList = "order_id")})
public class OrderItem implements Serializable {

    @Id
    @GenericGenerator(name = "snowflake", strategy = "com.fongwell.satchi.crm.core.support.jpa.hibernate.SnowflakeIdGenerator")
    @GeneratedValue(generator = "snowflake")
    private Long id;

    @Column(name = "order_id")
    private long orderId;

    private String name;

    @Column(name = "sku_id")
    private long skuId;

    private int quantity;

    private BigDecimal price;

    private BigDecimal salePrice;

    private BigDecimal totalSalePrice;

    private BigDecimal totalPrice;

    @Column(name = "product_id")
    private long productId;


    private String mobileImage;
    private String pcImage;

    private String skuNumber;

    public OrderItem(final long orderId, final long productId, final long skuId, final int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.skuId = skuId;
        this.quantity = quantity;
    }

    OrderItem() {
    }

    public String getMobileImage() {
        return mobileImage;
    }

    public void setMobileImage(final String mobileImage) {
        this.mobileImage = mobileImage;
    }

    public String getPcImage() {
        return pcImage;
    }

    public void setPcImage(final String pcImage) {
        this.pcImage = pcImage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(final BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(final long orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(final long skuId) {
        this.skuId = skuId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(final BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getTotalSalePrice() {
        return totalSalePrice;
    }

    public void setTotalSalePrice(final BigDecimal totalSalePrice) {
        this.totalSalePrice = totalSalePrice;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(final long productId) {
        this.productId = productId;
    }


    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(final String skuNumber) {
        this.skuNumber = skuNumber;
    }
}
