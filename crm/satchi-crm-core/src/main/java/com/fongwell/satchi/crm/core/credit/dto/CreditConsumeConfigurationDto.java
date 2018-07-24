package com.fongwell.satchi.crm.core.credit.dto;

import com.fongwell.satchi.crm.core.credit.domain.value.ConsumeOption;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by docker on 5/7/18.
 */
public class CreditConsumeConfigurationDto implements Serializable {

    @NotNull(message = "consume.credit.required")
    private Integer credit;

    @NotNull(message = "consume.cash.required")
    private BigDecimal cash;

    private Boolean enabled;

    private Integer maxCreditPerOrder;

    private Integer maxDiscountPercentage;

    @NotNull(message = "consume.consumeOption.required")
    private ConsumeOption option;


    public ConsumeOption getOption() {
        return option;
    }

    public void setOption(final ConsumeOption option) {
        this.option = option;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(final Integer credit) {
        this.credit = credit;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(final BigDecimal cash) {
        this.cash = cash;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getMaxCreditPerOrder() {
        return maxCreditPerOrder;
    }

    public void setMaxCreditPerOrder(final Integer maxCreditPerOrder) {
        this.maxCreditPerOrder = maxCreditPerOrder;
    }

    public Integer getMaxDiscountPercentage() {
        return maxDiscountPercentage;
    }

    public void setMaxDiscountPercentage(final Integer maxDiscountPercentage) {
        this.maxDiscountPercentage = maxDiscountPercentage;
    }
}
