package com.fongwell.satchi.crm.core.tag.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by docker on 5/17/18.
 */
@Transactional
public interface TagService {

    void referenceTag(String tag);

    void dereferenceTag(String tag);

    void deleteTag(String tag);

    void createTag(String tag);
}
