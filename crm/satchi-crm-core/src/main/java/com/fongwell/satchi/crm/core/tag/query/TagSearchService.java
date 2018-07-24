package com.fongwell.satchi.crm.core.tag.query;

import java.util.Collection;

/**
 * Created by docker on 5/17/18.
 */
public interface TagSearchService {

    Collection<String> searchTags(String query, int from, int size);

}
