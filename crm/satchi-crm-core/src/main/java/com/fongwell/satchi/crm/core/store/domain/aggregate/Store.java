package com.fongwell.satchi.crm.core.store.domain.aggregate;

import com.fongwell.satchi.crm.core.store.value.StoreState;
import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

/**
 * Created by docker on 3/28/18.
 */
@Entity
@Table(name = "crm_store", indexes = {@Index(name = "crm_store_name_idx", columnList = "name")})
@SQLDelete(sql = "update crm_store set deleted = true, version = version + 1 where id = ? and version = ?")
public class Store extends AbstractAggregateRoot {

    private String name;

    @Column(name = "province", length = 50)
    private String province;
    @Column(name = "city", length = 50)
    private String city;
    @Column(name = "address")
    private String address;

    private String contact;

    @Column(name = "open", length = 10)
    private String open;
    @Column(name = "close", length = 10)
    private String close;

    private int orderNumber;

    @Enumerated(value = EnumType.STRING)
    private StoreState state = StoreState.enable;


    public boolean disble() {

        boolean changed = state == StoreState.enable;

        state = StoreState.disable;
        return changed;
    }

    public boolean enable() {
        boolean changed = state == StoreState.disable;

        state = StoreState.enable;
        return changed;
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }


    public int getOrderNumber() {
        return orderNumber;
    }

    public StoreState getState() {
        return state;
    }
}
