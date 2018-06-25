package com.fongwell.satchi.crm.core.series.domain.aggregate;

import com.fongwell.satchi.crm.core.common.resource.SourceType;
import com.fongwell.satchi.crm.core.series.value.SeriesState;
import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.List;

/**
 * Created by docker on 3/28/18.
 */
@Entity
@Table(name = "crm_series")
@SQLDelete(sql = "update crm_series set deleted = true, version = version + 1 where id = ? and version = ?")
public class Series extends AbstractAggregateRoot {


    @Column(name = "title", length = 100)
    private String title;
    @Column(name = "introduction", length = 800)
    private String introduction;

    @Column(name = "buttonText", length = 50)
    private String buttonText;

    @Column(name = "imageUrl", columnDefinition = "text")
    private String imageUrl;

    @Column(name = "videoUrl", columnDefinition = "text")
    private String videoUrl;

    @Enumerated(EnumType.STRING)
    private SourceType source;

    @Column(name = "opacity", columnDefinition = "smallint")
    private Integer opacity = 1;

    @Enumerated(EnumType.STRING)
    private SeriesState state = SeriesState.enabled;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @CollectionTable(
            name = "crm_series_product",
            indexes = {@Index(name = "crm_series_product_seriesid_idx", columnList = "series_id")},
            joinColumns = @JoinColumn(name = "series_id")
    )
    @OrderColumn(name = "displayOrder")
    @Column(name = "product_id")
    private List<Long> productIds;


    public boolean toggleState(SeriesState state) {

        boolean changed = this.state != state;
        this.state = state;
        return changed;
    }


}
