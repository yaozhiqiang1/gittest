package com.fongwell.satchi.crm.core.tag.repository;

import com.fongwell.satchi.crm.core.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 5/17/18.
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
}
