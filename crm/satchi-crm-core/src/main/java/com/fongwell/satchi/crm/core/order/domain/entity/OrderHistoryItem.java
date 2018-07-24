package com.fongwell.satchi.crm.core.order.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by docker on 5/18/18.
 */
@Entity
@Table(name = "crm_order_history_item", indexes = @Index(name = "crm_order_history_item_idx", columnList = "history_id"))
public class OrderHistoryItem implements Serializable {

    @Id
    @GenericGenerator(name = "snowflake", strategy = "com.fongwell.satchi.crm.core.support.jpa.hibernate.SnowflakeIdGenerator")
    @GeneratedValue(generator = "snowflake")
    private Long id;

    @Column(name = "history_id")
    private Long historyId;

    @Column(name = "item_id")
    private Long itemId;

    private String name;

    private BigDecimal price;

    private int quantity;

    private String image;


    public OrderHistoryItem(final Long historyId, final Long itemId, String name, final BigDecimal price, final int quantity, final String image) {
        this.historyId = historyId;
        this.itemId = itemId;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.name = name;
    }

    OrderHistoryItem() {
    }
}
