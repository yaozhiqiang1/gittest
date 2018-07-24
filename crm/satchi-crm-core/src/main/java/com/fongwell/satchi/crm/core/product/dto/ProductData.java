package com.fongwell.satchi.crm.core.product.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fongwell.satchi.crm.core.product.domain.aggregate.entity.ProductSourceUrl;
import com.fongwell.satchi.crm.core.product.value.ProductType;

/**
 * Created by roman on 18-4-3.
 */
public class ProductData {
    @NotBlank(message = "ProductData.name require")
    private String name;
    @NotBlank(message = "ProductData.number require")
    @Length(max = 100,message = "ProductData.number long length")
    private String number;
    @Length(max = 500,message = "ProductData.introduction long length")
    private String introduction;
    @Length(max = 500,message = "ProductData.composition long length")
    private String composition;
    @Length(max = 500,message = "ProductData.delivery long length")
    private String delivery;
    
   // @NotNull(message = "ProductData.inventory require")
    @Min(value = 0,message = "ProductData.inventory smallest")
    private Integer inventory;
    
    @DecimalMin(value = "0",message = "ProductData.postage smallest")
    private BigDecimal postage = BigDecimal.ZERO;

    private Long categoryId;

    @Size(min = 1,message = "ProductData.pcImages require")
    private Collection<ProductSourceUrl> pcImages;
    @Size(min = 1,message = "ProductData.mobileImages require")
    private Collection<ProductSourceUrl> mobileImages;

    private Collection<ProductSourceUrl> pcVideos = new ArrayList<>();
    private Collection<ProductSourceUrl> mobileVideos = new ArrayList<>();

    private int orderNumber;
    @NotNull(message = "ProductData.settings require")
    @Valid
    private ProductSettingsData settings;

    private ProductType type = ProductType.COMMODITY;

    @Valid
    private List<AttachData> attaches = new ArrayList<>();

    public ProductData() {
    }

    public ProductData(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public Collection<ProductSourceUrl> getPcImages() {
        return pcImages;
    }

    public void setPcImages(Collection<ProductSourceUrl> pcImages) {
        this.pcImages = pcImages;
    }

    public Collection<ProductSourceUrl> getMobileImages() {
        return mobileImages;
    }

    public void setMobileImages(Collection<ProductSourceUrl> mobileImages) {
        this.mobileImages = mobileImages;
    }

    public Collection<ProductSourceUrl> getPcVideos() {
        return pcVideos;
    }

    public void setPcVideos(Collection<ProductSourceUrl> pcVideos) {
        this.pcVideos = pcVideos;
    }

    public Collection<ProductSourceUrl> getMobileVideos() {
        return mobileVideos;
    }

    public void setMobileVideos(Collection<ProductSourceUrl> mobileVideos) {
        this.mobileVideos = mobileVideos;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<AttachData> getAttaches() {
        return attaches;
    }

    public void setAttaches(List<AttachData> attaches) {
        this.attaches = attaches;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public ProductSettingsData getSettings() {
        return settings;
    }

    public void setSettings(ProductSettingsData settings) {
        this.settings = settings;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
