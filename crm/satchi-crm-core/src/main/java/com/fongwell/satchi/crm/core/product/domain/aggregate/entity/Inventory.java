package com.fongwell.satchi.crm.core.product.domain.aggregate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by roman on 18-4-3.
 */
@Entity
@Table(name = "crm_sku_inventory")
public class Inventory {

    private Integer inventory;

    private Long version;

    private Date createdDate;

    private Date lastmodifiedDate;

    @Id
    @Column(name = "sku_id")
    private Long sku;

    public Inventory() {
        createdDate = new Date();
    }

    public Inventory(Long skuId, Integer in) {
        createdDate = new Date();
        sku = skuId;
        inventory = in;
    }

    public void decrease(Integer amount) {
        inventory -= amount;

    }

    public void increase(Integer amount) {
        inventory += amount;

    }

    public void setInventory(final Integer inventory) {
        this.inventory = inventory;
    }

    public void modified() {
        lastmodifiedDate = new Date();
    }

    public Integer getInventory() {
        return inventory;
    }

    public Long getSku() {
        return sku;
    }
}
