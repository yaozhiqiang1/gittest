package com.fongwell.satchi.crm.core.credit.dto;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created by docker on 5/7/18.
 */
public class CreditConfigurationDto implements Serializable {

    private Boolean enabled;

    private Date expiration;

    @Valid
    private Collection<CreditSourceConfigurationDto> sources;

    @Valid
    private CreditConsumeConfigurationDto consumeConfiguration;


    public Boolean getEnabled() {
        return enabled;
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

    public Collection<CreditSourceConfigurationDto> getSources() {
        return sources;
    }

    public void setSources(final Collection<CreditSourceConfigurationDto> sources) {
        this.sources = sources;
    }

    public CreditConsumeConfigurationDto getConsumeConfiguration() {
        return consumeConfiguration;
    }

    public void setConsumeConfiguration(final CreditConsumeConfigurationDto consumeConfiguration) {
        this.consumeConfiguration = consumeConfiguration;
    }
}
