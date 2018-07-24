package com.fongwell.satchi.crm.core.resource.qiniu;

import com.fongwell.satchi.crm.core.resource.domain.aggregate.Resource;
import com.fongwell.satchi.crm.core.resource.domain.value.ResourceType;
import com.fongwell.satchi.crm.core.resource.repository.ResourceRepository;
import org.springframework.stereotype.Service;

/**
 * Created by docker on 4/24/18.
 */
@Service("qiniuUploadResourceService")
public class QiniuUploadResourceServiceImpl implements QiniuUploadResourceService {

    @javax.annotation.Resource(name = "resourceRepository")
    private ResourceRepository resourceRepository;


    @Override
    public Resource upload(final Long userId, final String key, final String url, final ResourceType type) {

        Resource resource = new Resource(userId, key, url, type);
        resourceRepository.save(resource);

        return resource;
    }

}
