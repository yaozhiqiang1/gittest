package com.fongwell.satchi.crm.core.customer.domain.query;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * Created by docker on 5/17/18.
 */
@Mapper
public interface CustomerTagQueryMapper {

    Collection<UserTagsDto> queryTags(@Param("customerIds") Collection<Long> customerIds);

    Collection<Long> searchCustomersWithTags(@Param("tags") final Collection<String> tags);

    Collection<String> queryTagsForCustomer(@Param("customerId") long customerId);

}
