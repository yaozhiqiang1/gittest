package com.fongwell.satchi.crm.core.order.repository;

import com.fongwell.satchi.crm.core.order.domain.aggregate.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 4/20/18.
 */
@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {

    Shipping findByOrderId(long orderId);
}
