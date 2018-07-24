package com.fongwell.satchi.crm.api.controller.admin;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.core.tag.query.mapper.TagMapper;
import com.fongwell.satchi.crm.core.tag.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by docker on 5/22/18.
 */
@RestController("adminTagApi")
@RequestMapping("/api/admin/tags")
public class TagApi {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagMapper tagMapper;


    @GetMapping("")
    public Payload list(@RequestParam(required = false, value = "query") String query,
                        @RequestParam(required = false, defaultValue = "0") int from, @RequestParam(required = false, defaultValue = "20") int size) {
        return new Payload(tagMapper.queryTags(query, from, size));
    }

    @GetMapping("/count")
    public Payload count(@RequestParam(required = false, value = "query") String query
    ) {
        return new Payload(tagMapper.countTags(query));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestParam String tag) {
        tagService.createTag(tag);
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam String tag) {
        tagService.deleteTag(tag);
    }
}
