package com.fongwell.satchi.crm.core.series.service;

import com.fongwell.satchi.crm.core.common.AbstractWriteService;
import com.fongwell.satchi.crm.core.series.domain.aggregate.Series;
import com.fongwell.satchi.crm.core.series.dto.SeriesData;
import com.fongwell.satchi.crm.core.series.repository.SeriesRepository;
import com.fongwell.satchi.crm.core.series.value.SeriesState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * Created by docker on 3/28/18.
 */
@Service("seriesService")
public class SeriesServiceImpl extends AbstractWriteService<Series, Long, SeriesData> implements SeriesService {

    @Resource(name = "seriesRepository")
    private SeriesRepository seriesRepository;

    public SeriesServiceImpl() {
        super(Series.class);
    }

    @Override
    protected JpaRepository<Series, Long> getRepository() {
        return seriesRepository;
    }

    @Override
    public void enable(final Collection<Long> ids) {
        List<Series> targets = seriesRepository.findAll(ids);


        for (final Series target : targets) {
            if (target.toggleState(SeriesState.enabled)) {
                seriesRepository.save(targets);
            }
        }

    }

    @Override
    public void disable(final Collection<Long> ids) {
        List<Series> targets = seriesRepository.findAll(ids);


        for (final Series target : targets) {
            if (target.toggleState(SeriesState.disabled)) {
                seriesRepository.save(targets);
            }
        }
    }

    @Override
    public void delete(final Collection<Long> ids) {
        List<Series> targets = seriesRepository.findAll(ids);
        if (!targets.isEmpty()) {
            for (final Series target : targets) {
                seriesRepository.delete(target);
            }
        }

    }
}
