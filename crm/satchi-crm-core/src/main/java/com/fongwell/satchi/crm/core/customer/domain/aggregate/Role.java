package com.fongwell.satchi.crm.core.customer.domain.aggregate;

import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by docker on 2/25/18.
 */
@Entity
@Table(name = "fw_customer_role")
public class Role extends AbstractAggregateRoot {


    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @CollectionTable(
            name = "fw_customer_role_permissions",
            indexes = {@Index(name = "fw_customer_role_permissions_idx", columnList = "role_id")},
            joinColumns = @JoinColumn(name = "role_id")
    )
    @Column(name = "permission")
    private Collection<String> permissions;


    public Collection<String> getPermissions() {
        return permissions;
    }
}
