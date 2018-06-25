package com.fongwell.satchi.crm.core.product.domain.aggregate.entity;

import com.fongwell.satchi.crm.core.support.id.Snowflake;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by docker on 4/20/18.
 */
@Entity
@Table(name = "crm_sku_inventory_audit", indexes = @Index(name = "crm_sku_inventory_audit_sku_id_idx", columnList = "sku_id"))
public class InventoryAudit implements Serializable {

    @Id
    private long id;

    @Column(name = "sku_id")
    private long skuId;

    private Integer change;

    private Integer inventoryBefore;

    private Integer inventoryAfter;

    private Long customerId;

    @Column(name = "order_id")
    private Long orderId;

    private String source;

    private Date createdDate;

    public InventoryAudit(final long skuId, final Integer change, final Integer inventoryBefore, final Integer inventoryAfter, final Long customerId, final Long orderId, final String source) {
        this.skuId = skuId;
        this.change = change;
        this.inventoryBefore = inventoryBefore;
        this.inventoryAfter = inventoryAfter;
        this.customerId = customerId;
        this.orderId = orderId;
        this.source = source;
        this.createdDate = new Date();
        this.id = Snowflake.id();
    }

    InventoryAudit() {
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getSkuId() {
        return skuId;
    }

    public void setSkuId(final long skuId) {
        this.skuId = skuId;
    }

    public Integer getChange() {
        return change;
    }

    public void setChange(final Integer change) {
        this.change = change;
    }

    public Integer getInventoryBefore() {
        return inventoryBefore;
    }

    public void setInventoryBefore(final Integer inventoryBefore) {
        this.inventoryBefore = inventoryBefore;
    }

    public Integer getInventoryAfter() {
        return inventoryAfter;
    }

    public void setInventoryAfter(final Integer inventoryAfter) {
        this.inventoryAfter = inventoryAfter;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(final String source) {
        this.source = source;
    }
}
