package com.fongwell.satchi.crm.core.product.domain.aggregate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by roman on 18-4-3.
 */
@Entity
@Table(name = "crm_sku", indexes = {@Index(name = "crm_sku_number_idx", columnList = "number")})
public class Sku {

    @Id
    @GenericGenerator(name = "snowflake", strategy = "com.fongwell.satchi.crm.core.support.jpa.hibernate.SnowflakeIdGenerator")
    @GeneratedValue(generator = "snowflake")
    private Long id;

    private String number;

    @Column(name = "product_id", updatable = false)
    private Long product;
    @Column(name = "price", scale = 2)
    private BigDecimal price;
    @Column(name = "originalPrice", scale = 2)
    private BigDecimal originalPrice;

    private Integer credit;

    @OneToOne(targetEntity = Inventory.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType
            .LAZY, orphanRemoval = true)
    private Inventory inventory;

    public Sku() {
    }

    public Sku(String number, BigDecimal price, BigDecimal originalPrice, Integer credit, Long product, Integer inventory) {
        super();
        this.number = number;
        this.product = product;
        this.price = price;
        this.originalPrice = originalPrice;
        this.inventory = new Inventory(id, inventory);
    }

    public void update(String number, BigDecimal price, BigDecimal originalPrice, Integer credit, Integer inventory) {
        this.number = number;
        this.price = price;
        this.originalPrice = originalPrice;
        this.inventory.setInventory(inventory);
    }

    public String getNumber() {
        return number;
    }

    public Long getProduct() {
        return product;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public Integer getCredit() {
        return credit;
    }
}
