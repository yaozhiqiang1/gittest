package com.fongwell.satchi.crm.core.order.repository;

import com.fongwell.satchi.crm.core.order.domain.aggregate.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 4/20/18.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
