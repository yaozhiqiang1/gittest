package com.fongwell.satchi.crm.core.customer.query.mapper;

import com.fongwell.satchi.crm.core.customer.domain.value.AddressValue;
import com.fongwell.satchi.crm.core.customer.dto.AddressDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * Created by docker on 4/23/18.
 */
@Mapper
public interface CustomerAddressQueryMapper {

    Collection<AddressDto> queryAddreses(@Param("customerId") long customerId);

    AddressValue queryAddress(@Param("id") long id);
}
