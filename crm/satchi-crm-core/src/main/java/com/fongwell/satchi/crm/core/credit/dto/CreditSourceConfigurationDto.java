package com.fongwell.satchi.crm.core.credit.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Created by docker on 5/7/18.
 */
public class CreditSourceConfigurationDto implements Serializable {

    @Min(value = 0)
    private Integer credit;

    @NotEmpty(message = "type.required")
    private String type;
    private Boolean enabled;

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(final Integer credit) {
        this.credit = credit;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }
}
