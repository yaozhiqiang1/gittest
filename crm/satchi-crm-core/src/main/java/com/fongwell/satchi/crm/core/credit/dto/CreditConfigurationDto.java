package com.fongwell.satchi.crm.core.credit.dto;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created by docker on 5/7/18.
 */
public class CreditConfigurationDto implements Serializable {

    private Boolean enabled; //是否激活启用

    private Date expiration; //到期时间

    @Valid
    private Collection<CreditSourceConfigurationDto> sources; //积分来源配置

    @Valid
    private CreditConsumeConfigurationDto consumeConfiguration;//积分消费配置


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
