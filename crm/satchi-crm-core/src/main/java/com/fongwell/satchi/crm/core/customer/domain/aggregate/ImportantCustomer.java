package com.fongwell.satchi.crm.core.customer.domain.aggregate;

import com.fongwell.satchi.crm.core.customer.domain.entity.ImportantCustomerId;

import javax.persistence.*;

/**
 * Created by docker on 5/24/18.
 */
@Entity
@Table(name = "fw_important_customer")
@IdClass(ImportantCustomerId.class)
public class ImportantCustomer {

    @Column(name = "customer_id")
    @Id
    private long customerId;

    @Column(name = "store_id")
    @Id
    private long storeId;

    @Version
    private Integer version;

    public ImportantCustomer(final long customerId, final long storeId) {
        this.customerId = customerId;
        this.storeId = storeId;
    }

    ImportantCustomer() {
    }


}
