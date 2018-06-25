package com.fongwell.satchi.crm.core.store.domain.aggregate.repository;

import com.fongwell.satchi.crm.core.store.domain.aggregate.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 5/21/18.
 */
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    Staff findOneByMobile(String mobile);

}
