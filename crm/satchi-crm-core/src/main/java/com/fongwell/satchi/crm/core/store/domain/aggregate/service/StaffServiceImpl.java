package com.fongwell.satchi.crm.core.store.domain.aggregate.service;

import com.fongwell.satchi.crm.core.common.error.DuplicateParameterException;
import com.fongwell.satchi.crm.core.store.domain.aggregate.Staff;
import com.fongwell.satchi.crm.core.store.domain.aggregate.dto.StaffData;
import com.fongwell.satchi.crm.core.store.domain.aggregate.repository.StaffRepository;
import com.fongwell.support.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by docker on 5/21/18.
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;


    @Override
    public Staff createStaff(final long storeId, final StaffData data) {


        Staff staff = staffRepository.findOneByMobile(data.getMobile());

        if (staff != null) {
            throw new DuplicateParameterException("Duplicate staff mobile");

        }
        staff = new Staff(storeId, data);

        staffRepository.save(staff);
        return staff;
    }

    @Override
    public void updateStaff(final long id, final StaffData data) {

        Staff staff = staffRepository.findOneByMobile(data.getMobile());

        if (staff != null && staff.getId() != id) {
            throw new DuplicateParameterException("Duplicate staff mobile");

        }
        staff = staffRepository.findOne(id);
        Assert.notNull(staff, "staff");

        staff.update(data);
        staffRepository.save(staff);
    }

    @Override
    public void deleteStaff(final long id) {
        staffRepository.delete(id);
    }
}
