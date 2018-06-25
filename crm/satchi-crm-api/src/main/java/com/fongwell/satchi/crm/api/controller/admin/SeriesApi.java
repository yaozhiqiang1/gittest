package com.fongwell.satchi.crm.api.controller.admin;

import com.fongwell.base.rest.Payload;
import com.fongwell.base.validate.Validate;
import com.fongwell.satchi.crm.core.series.dto.SeriesData;
import com.fongwell.satchi.crm.core.series.query.AdminSeriesQueryMapper;
import com.fongwell.satchi.crm.core.series.service.SeriesService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by docker on 3/28/18.
 */
@RestController
@RequestMapping("/api/admin/series")
public class SeriesApi {

    @Resource(name = "seriesService")
    private SeriesService seriesService;

    @Resource(name = "adminSeriesQueryMapper")
    private AdminSeriesQueryMapper adminSeriesQueryMapper;

    @PutMapping(value = "")
    @Validate
    public Payload create(@Valid @RequestBody SeriesData data, BindingResult result) {

        return new Payload(seriesService.create(data).getId());
    }


    @PostMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Validate
    public void update(@PathVariable Long id, @Valid @RequestBody SeriesData data, BindingResult result) {

        seriesService.update(id, data);
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {

        seriesService.delete(id);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam Collection<Long> ids) {

        seriesService.delete(ids);
    }

    @GetMapping("")
    public Payload list(
            @RequestParam(required = false, defaultValue = "0") int from,
            @RequestParam(required = false, defaultValue = "20") int size
    ) {
        return new Payload(adminSeriesQueryMapper.listSeries(from, size));
    }

    @GetMapping("/count")
    public Payload count() {
        return new Payload(adminSeriesQueryMapper.countSeries());
    }

    @GetMapping("/{id}")
    public Payload get(@PathVariable long id) {
        return new Payload(adminSeriesQueryMapper.getSeries(id));
    }

    @PostMapping("/enable")
    @ResponseStatus(HttpStatus.OK)
    public void enable(@RequestParam Collection<Long> ids) {
        seriesService.enable(ids);
    }

    @PostMapping("/disable")
    @ResponseStatus(HttpStatus.OK)
    public void disable(@RequestParam Collection<Long> ids) {
        seriesService.disable(ids);
    }
}
