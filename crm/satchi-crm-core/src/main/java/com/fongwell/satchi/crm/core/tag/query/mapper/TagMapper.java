package com.fongwell.satchi.crm.core.tag.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Map;

/**
 * Created by docker on 5/17/18.
 */
@Mapper
public interface TagMapper {

    Collection<String> searchTags(@Param("query") String query, @Param("from") int from, @Param("size") int size);

    Collection<Map> queryTags(@Param("query") String query, @Param("from") int from, @Param("size") int size);

    int countTags(@Param("query") String query);
}
