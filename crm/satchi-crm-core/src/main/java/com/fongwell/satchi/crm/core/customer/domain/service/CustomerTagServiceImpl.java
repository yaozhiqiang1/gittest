package com.fongwell.satchi.crm.core.customer.domain.service;

import com.fongwell.satchi.crm.core.customer.domain.aggregate.CustomerTags;
import com.fongwell.satchi.crm.core.customer.domain.repository.CustomerTagsRepository;
import com.fongwell.satchi.crm.core.tag.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by docker on 5/17/18.
 */
@Service("customerTagService")
public class CustomerTagServiceImpl implements CustomerTagService {

    @Resource(name = "customerTagsRepository")
    private CustomerTagsRepository customerTagsRepository;

    @Resource(name = "tagService")
    private TagService tagService;

    @Override
    public void saveTags(final long customerId, final Set<String> tags) {

        CustomerTags tag = customerTagsRepository.findOne(customerId);

        Collection<String> newTags;
        Collection<String> oldTags;
        if (tag == null) {
            tag = new CustomerTags(customerId, tags);
            newTags = tags;
            oldTags = Collections.emptyList();
        } else {
            Set<String> currentTags = tag.getTags();
            newTags = tag.updateTags(tags);

            oldTags = new HashSet<>();
            for (final String currentTag : currentTags) {
                if (!tags.contains(currentTag)) {
                    oldTags.add(currentTag);
                }
            }

        }
        customerTagsRepository.save(tag);

        for (final String newTag : newTags) {
            tagService.referenceTag(newTag);
        }

        for (final String oldTag : oldTags) {
            tagService.dereferenceTag(oldTag);
        }

    }
}
