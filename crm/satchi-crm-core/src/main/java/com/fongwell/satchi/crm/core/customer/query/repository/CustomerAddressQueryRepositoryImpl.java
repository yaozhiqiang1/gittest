package com.fongwell.satchi.crm.core.customer.query.repository;

import com.fongwell.satchi.crm.core.customer.domain.value.AddressValue;
import com.fongwell.satchi.crm.core.customer.dto.AddressDto;
import com.fongwell.satchi.crm.core.customer.query.mapper.CustomerAddressQueryMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by docker on 4/19/18.
 */
@Repository("customerAddressQueryRepository")
public class CustomerAddressQueryRepositoryImpl implements CustomerAddressQueryRepository {

    @Resource(name = "customerAddressQueryMapper")
    private CustomerAddressQueryMapper customerAddressQueryMapper;

    @Override
    public AddressValue queryAddress(final long id) {
        return customerAddressQueryMapper.queryAddress(id);
    }

    @Override
    public Collection<AddressDto> queryAddreses(final long customerId) {
        return customerAddressQueryMapper.queryAddreses(customerId);
    }
}
