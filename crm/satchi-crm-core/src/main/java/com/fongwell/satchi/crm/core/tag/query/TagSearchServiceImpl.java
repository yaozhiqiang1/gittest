package com.fongwell.satchi.crm.core.tag.query;

import com.fongwell.satchi.crm.core.tag.query.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by docker on 5/17/18.
 */
@Service("tagSearchService")
public class TagSearchServiceImpl implements TagSearchService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Collection<String> searchTags(final String query, final int from, final int size) {
        return tagMapper.searchTags(query, from, size);
    }
}
