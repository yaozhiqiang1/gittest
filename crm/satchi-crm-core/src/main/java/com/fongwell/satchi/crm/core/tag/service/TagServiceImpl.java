package com.fongwell.satchi.crm.core.tag.service;

import com.fongwell.satchi.crm.core.common.error.DuplicateParameterException;
import com.fongwell.satchi.crm.core.tag.domain.Tag;
import com.fongwell.satchi.crm.core.tag.repository.TagRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by docker on 5/17/18.
 */
@Service("tagService")
public class TagServiceImpl implements TagService {

    @Resource(name = "tagRepository")
    private TagRepository tagRepository;


    @Override
    public void referenceTag(final String tag) {

        Tag entity = tagRepository.findOne(tag);
        if (entity != null) {
            entity.incrementReferences();
            tagRepository.save(entity);

        }

    }

    @Override
    public void dereferenceTag(final String tag) {
        Tag entity = tagRepository.findOne(tag);
        if (entity != null) {
            entity.decrementReferences();
            tagRepository.save(entity);

        }

    }

    @Override
    public void deleteTag(final String tag) {
        tagRepository.delete(tag);
    }

    @Override
    public void createTag(final String tag) {
        Tag duplicate = tagRepository.findOne(tag);
        if (duplicate != null) {
            throw new DuplicateParameterException("duplcate tag:" + tag);
        }

        tagRepository.save(new Tag(tag));
    }
}
