package com.fongwell.satchi.crm.core.credit.domain.aggregate;

import com.fongwell.satchi.crm.core.credit.domain.entity.CreditConsumeConfiguration;
import com.fongwell.satchi.crm.core.credit.domain.entity.CreditSourceConfiguration;
import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;
import com.fongwell.support.utils.Assert;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.*;

/**
 * Created by docker on 5/7/18.
 */
@Entity
@Table(name = "crm_credit_configuration")
public class CreditConfiguration extends AbstractAggregateRoot {

    public static final long DEFAULT_ID = -1;

    private Boolean enabled;

    private Date expiration;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parentId", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @Fetch(FetchMode.SUBSELECT)
    private Collection<CreditSourceConfiguration> sources;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private CreditConsumeConfiguration consumeConfiguration;

    @Transient
    private Map<String, CreditSourceConfiguration> sourceMap;


    public CreditConfiguration(long id) {
        this.id = id;
        this.sources = new LinkedList<>();
    }

    public void addSource(CreditSourceConfiguration source) {

        if (getSourceMap().containsKey(source.getType())) {
            throw new IllegalArgumentException("source " + source.getType() + " already exists!");
        }

        sources.add(source);
        getSourceMap().put(source.getType(), source);

    }

    public void setSources(final Collection<CreditSourceConfiguration> sources) {

        Assert.notNull(sources, "sources");

        this.sources.clear();
        this.sources.addAll(sources);
        if (sourceMap != null) {
            this.sourceMap.clear();
        }


    }

    private Map<String, CreditSourceConfiguration> getSourceMap() {
        if (sourceMap == null) {
            sourceMap = new HashMap<>((int) (sources.size() / 0.75f) + 1);
            for (final CreditSourceConfiguration source : sources) {
                sourceMap.put(source.getType(), source);
            }
        }
        return sourceMap;

    }

    public CreditSourceConfiguration getSource(String type) {


        return getSourceMap().get(type);

    }


    CreditConfiguration() {
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public boolean isApplicable(Date now) {

        if (enabled == null || !enabled) {
            return false;
        }

        return expiration == null || expiration.after(DateUtils.truncate(now, Calendar.DATE));
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(final Date expiration) {
        this.expiration = expiration;
    }

    public Collection<CreditSourceConfiguration> getSources() {
        return sources;
    }


    public CreditConsumeConfiguration getConsumeConfiguration() {
        return consumeConfiguration;
    }

    public void setConsumeConfiguration(final CreditConsumeConfiguration consumeConfiguration) {
        this.consumeConfiguration = consumeConfiguration;
    }
}
