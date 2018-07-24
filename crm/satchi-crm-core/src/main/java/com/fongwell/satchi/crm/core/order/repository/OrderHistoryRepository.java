package com.fongwell.satchi.crm.core.order.repository;

import com.fongwell.satchi.crm.core.order.domain.aggregate.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 5/18/18.
 */
@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
}
