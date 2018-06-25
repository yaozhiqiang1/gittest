package com.fongwell.satchi.crm.core.series.service;

import com.fongwell.satchi.crm.core.common.WriteService;
import com.fongwell.satchi.crm.core.series.domain.aggregate.Series;
import com.fongwell.satchi.crm.core.series.dto.SeriesData;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by docker on 3/28/18.
 */
@Service("seriesService")
public interface SeriesService extends WriteService<Series, Long, SeriesData> {

    void enable(Collection<Long> ids);

    void disable(Collection<Long> ids);

    void delete(Collection<Long> ids);

}
