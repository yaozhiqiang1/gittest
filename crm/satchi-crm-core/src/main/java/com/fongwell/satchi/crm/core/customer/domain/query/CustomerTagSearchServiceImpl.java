package com.fongwell.satchi.crm.core.customer.domain.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by docker on 5/18/18.
 */
@Service("customerTagSearchService")
public class CustomerTagSearchServiceImpl implements CustomerTagSearchService {

    @Autowired
    private CustomerTagQueryMapper customerTagQueryMapper;

    @Override
    public Map<Long, Collection<String>> searchTags(final Collection<Long> customerIds) {

        Collection<UserTagsDto> data = customerTagQueryMapper.queryTags(customerIds);
        if (data.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<Long, Collection<String>> tags = new HashMap<>(((int) (data.size() / 0.75f)) + 1);
        for (final UserTagsDto item : data) {
            tags.put(item.getId(), item.getTags());
        }
        return tags;
    }

    @Override
    public Collection<Long> searchCustomersWithTags(final Collection<String> tags) {
        return customerTagQueryMapper.searchCustomersWithTags(tags);
    }
}
