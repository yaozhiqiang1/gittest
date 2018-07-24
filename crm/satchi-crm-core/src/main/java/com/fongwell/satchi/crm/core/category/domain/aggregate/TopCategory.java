package com.fongwell.satchi.crm.core.category.domain.aggregate;

import com.fongwell.satchi.crm.core.common.State;
import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by roman on 18-4-16.
 */
@Entity
@Table(name = "crm_top_category")
public class TopCategory extends AbstractAggregateRoot {

    public String name;

    private String imageUrl;

    private Long categoryId;

    @Enumerated(EnumType.STRING)
    private State state = State.disable;

    private int orderNumber;

    public TopCategory() {
        super();
        createdDate = new Date();
    }

    public void onDelete() {
        deleted = true;
    }

    public void onEnable() {
        state = State.enable;
    }

    public void onDisable() {
        state = State.disable;
    }


    public void onSort(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public State getState() {
        return state;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

	@Override
	public String toString() {
		return "TopCategory [name=" + name + ", imageUrl=" + imageUrl + ", categoryId=" + categoryId + ", state="
				+ state + ", orderNumber=" + orderNumber + "]";
	}
    
}
