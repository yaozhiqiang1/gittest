package com.fongwell.satchi.crm.core.customer.query.repository;


import com.fongwell.satchi.crm.core.customer.domain.value.AddressValue;
import com.fongwell.satchi.crm.core.customer.dto.AddressDto;

import java.util.Collection;

/**
 * Created by docker on 4/19/18.
 */
public interface CustomerAddressQueryRepository {

    AddressValue queryAddress(long id);

    Collection<AddressDto> queryAddreses(long customerId);

}
