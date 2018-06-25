package com.fongwell.satchi.crm.core.product.domain.aggregate.entity;

import javax.persistence.*;

/**
 * Created by roman on 18-4-8.
 */
@Entity
@Table(name = "crm_product_attach")
public class Attach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String key;

    @Column(name = "value", length = 1000)
    private String value;

    @Column(name = "product_id")
    private Long product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Long getProduct() {
        return product;
    }
}
