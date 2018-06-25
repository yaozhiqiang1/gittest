package com.fongwell.satchi.crm.core.product.dto;

import com.fongwell.satchi.crm.core.product.value.ProductRestrictionType;
import com.fongwell.satchi.crm.core.product.value.ProductType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by roman on 18-4-8.
 */
public class ProductSettingsData {
    @NotNull(message = "ProductSettingsData.type require")
    private ProductType type = ProductType.COMMODITY;
    @NotNull(message = "ProductSettingsData.price require")
    @DecimalMin(value = "0",message = "ProductSettingsData.price smallest")
    private BigDecimal price;
    @DecimalMin(value = "0",message = "ProductSettingsData.originalPrice smallest")
    private BigDecimal originalPrice;
    @Min(value = 0,message = "ProductSettingsData.credit smallest")
    private Integer credit;
    @NotNull(message = "ProductSettingsData.restrictionType require")
    private ProductRestrictionType restrictionType = ProductRestrictionType.ORDER;

    private int restrictionAmount;

    private Date startDate;

    private Date endDate;

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public ProductRestrictionType getRestrictionType() {
        return restrictionType;
    }

    public void setRestrictionType(ProductRestrictionType restrictionType) {
        this.restrictionType = restrictionType;
    }

    public int getRestrictionAmount() {
        return restrictionAmount;
    }

    public void setRestrictionAmount(int restrictionAmount) {
        this.restrictionAmount = restrictionAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
