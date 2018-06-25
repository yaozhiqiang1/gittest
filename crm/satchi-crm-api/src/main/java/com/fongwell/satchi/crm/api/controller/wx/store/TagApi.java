package com.fongwell.satchi.crm.api.controller.wx.store;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.core.tag.query.TagSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by docker on 5/18/18.
 */
@RestController
@RequestMapping("/api/wx/store/tags")
public class TagApi {

    @Autowired
    private TagSearchService tagSearchService;

    @GetMapping("")
    public Payload tags(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "from", required = false, defaultValue = "0") int from,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        return new Payload(tagSearchService.searchTags(query, from, size));
    }

}
