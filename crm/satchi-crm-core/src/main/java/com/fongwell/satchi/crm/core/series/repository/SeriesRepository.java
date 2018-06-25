package com.fongwell.satchi.crm.core.series.repository;

import com.fongwell.satchi.crm.core.series.domain.aggregate.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by docker on 3/28/18.
 */
@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
}
