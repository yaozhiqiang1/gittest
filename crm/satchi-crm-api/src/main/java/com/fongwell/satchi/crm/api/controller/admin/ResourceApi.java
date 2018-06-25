package com.fongwell.satchi.crm.api.controller.admin;

import com.fongwell.base.resource.qiniu.QiniuTemplate;
import com.fongwell.base.rest.Payload;
import com.fongwell.base.validate.Validate;
import com.fongwell.satchi.crm.core.resource.domain.value.ResourceType;
import com.fongwell.satchi.crm.core.resource.qiniu.QiniuUploadResourceService;
import com.fongwell.satchi.crm.core.resource.query.AdminResourceQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.Serializable;

/**
 * Created by docker on 4/24/18.
 */
@RestController
@RequestMapping("/api/admin/resources")
public class ResourceApi {

    @Resource(name = "qiniuTemplate")
    private QiniuTemplate qiniuTemplate;

    @Resource(name = "qiniuUploadResourceService")
    private QiniuUploadResourceService qiniuUploadResourceService;

    @Autowired
    private AdminResourceQueryMapper adminResourceQueryMapper;

    @GetMapping("")
    public Payload resources(@RequestParam ResourceType type,
                             @RequestParam(name = "from", defaultValue = "0", required = false) int from,
                             @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return new Payload(adminResourceQueryMapper.queryResources(type.name(), from, size));
    }

    @GetMapping("/count")
    public Payload countResoures(@RequestParam ResourceType type) {
        return new Payload(adminResourceQueryMapper.countResources(type.name()));
    }

    @RequestMapping(value = "/qiniu/uptoken", method = RequestMethod.POST)
    public Payload uptoken() {
        return new Payload(qiniuTemplate.generateUptoken());
    }


    @RequestMapping(value = "/qiniu/save", method = RequestMethod.POST)
    @Validate
    public Payload save(@Valid @RequestBody SaveQiniuResourceRequest request, BindingResult result) {
        String url = qiniuTemplate.getResourceUrl(request.getKey());
        qiniuUploadResourceService.upload(null, request.getKey(), url, request.getType());

        return new Payload(url);
    }

    public static final class SaveQiniuResourceRequest implements Serializable {
        private String key;
        private String url;
        private ResourceType type;

        public String getKey() {
            return key;
        }

        public void setKey(final String key) {
            this.key = key;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(final String url) {
            this.url = url;
        }

        public ResourceType getType() {
            return type;
        }

        public void setType(final ResourceType type) {
            this.type = type;
        }
    }
}
