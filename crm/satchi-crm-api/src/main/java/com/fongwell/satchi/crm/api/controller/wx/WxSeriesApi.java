package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.base.rest.Payload;
import com.fongwell.satchi.crm.core.series.query.WxSeriesQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by roman on 18-4-23.
 */
@RestController
@RequestMapping("/api/wx/series")
public class WxSeriesApi {

    @Autowired
    private WxSeriesQueryMapper seriesQueryMapper;

    @GetMapping
    public Payload findAll(@RequestParam(required = false, defaultValue = "0") int from,
    		@RequestParam(required = false, defaultValue = "0") int size) {
        return new Payload(seriesQueryMapper.findAll(from, size));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable long id) {
        return new Payload(seriesQueryMapper.queryDetail(id));
    }

}
