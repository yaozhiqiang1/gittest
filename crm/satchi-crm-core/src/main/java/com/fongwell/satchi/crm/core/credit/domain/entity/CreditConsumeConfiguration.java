package com.fongwell.satchi.crm.core.credit.domain.entity;

import com.fongwell.satchi.crm.core.credit.domain.value.ConsumeOption;
import com.fongwell.satchi.crm.core.credit.dto.CreditConsumeConfigurationDto;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by docker on 5/7/18.
 */
@Entity
@Table(name = "crm_credit_consume_configuration")
public class CreditConsumeConfiguration {

    @Id
    private long id;

    private Integer credit;

    private BigDecimal cash;

    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    private ConsumeOption option;

    private Integer maxCreditPerOrder;

    private Integer maxDiscountPercentage;

    public CreditConsumeConfiguration(final long id, CreditConsumeConfigurationDto data) {
        this.id = id;
        updateProperties(data);
    }

    public void updateProperties(CreditConsumeConfigurationDto data) {
        this.credit = data.getCredit();
        this.cash = data.getCash();
        this.enabled = data.getEnabled();
        this.maxCreditPerOrder = data.getMaxCreditPerOrder();
        this.maxDiscountPercentage = data.getMaxDiscountPercentage();
        this.option = data.getOption();

    }

    CreditConsumeConfiguration() {
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

    public ConsumeOption getOption() {
        return option;
    }

    public void setOption(ConsumeOption option) {
        this.option = option;
    }
}
