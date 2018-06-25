package com.fongwell.satchi.crm.core.resource.qiniu;

import com.fongwell.satchi.crm.core.resource.domain.aggregate.Resource;
import com.fongwell.satchi.crm.core.resource.domain.value.ResourceType;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by docker on 4/24/18.
 */
@Transactional
public interface QiniuUploadResourceService {

    Resource upload(Long userId, String key, String url, ResourceType type);

}
