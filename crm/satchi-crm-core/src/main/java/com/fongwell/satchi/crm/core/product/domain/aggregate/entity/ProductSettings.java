package com.fongwell.satchi.crm.core.product.domain.aggregate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fongwell.satchi.crm.core.product.domain.aggregate.Product;
import com.fongwell.satchi.crm.core.product.value.ProductRestrictionType;
import com.fongwell.satchi.crm.core.product.value.ProductType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by roman on 18-4-8.
 */
@Entity
@Table(name = "crm_product_settings")
public class ProductSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Product.class)
    @JsonBackReference
    private Product product;

    @Enumerated(EnumType.STRING)
    private ProductType type;
    @Column(name = "price",scale = 2)
    private BigDecimal price;
    @Column(name = "originalPrice",scale = 2)
    private BigDecimal originalPrice;

    private Date startDate;

    private Date endDate;

    @Enumerated(EnumType.STRING)
    private ProductRestrictionType restrictionType;

    private int restrictionAmount;

    private Integer credit;

    public ProductSettings() {
    }

    public void onProduct(Product product){
        this.product = product;
    }

    public ProductType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Integer getCredit() {
        return credit;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public ProductRestrictionType getRestrictionType() {
        return restrictionType;
    }

    public int getRestrictionAmount() {
        return restrictionAmount;
    }
}
