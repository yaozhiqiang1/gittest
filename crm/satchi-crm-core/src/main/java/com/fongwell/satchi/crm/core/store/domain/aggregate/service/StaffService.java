package com.fongwell.satchi.crm.core.store.domain.aggregate.service;

import com.fongwell.satchi.crm.core.store.domain.aggregate.Staff;
import com.fongwell.satchi.crm.core.store.domain.aggregate.dto.StaffData;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by docker on 5/21/18.
 */
@Transactional
public interface StaffService {

    Staff createStaff(long storeId, StaffData data);

    void updateStaff(long id, StaffData data);

    void deleteStaff(long id);

}
