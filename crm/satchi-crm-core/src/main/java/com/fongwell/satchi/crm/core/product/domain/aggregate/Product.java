package com.fongwell.satchi.crm.core.product.domain.aggregate;

import com.fongwell.satchi.crm.core.common.State;
import com.fongwell.satchi.crm.core.product.domain.aggregate.entity.Attach;
import com.fongwell.satchi.crm.core.product.domain.aggregate.entity.ProductSettings;
import com.fongwell.satchi.crm.core.product.domain.aggregate.entity.ProductSourceUrl;
import com.fongwell.satchi.crm.core.product.domain.aggregate.entity.Sku;
import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by roman on 18-4-3.
 */
@Entity
@Table(name = "crm_product", indexes = {@Index(name = "crm_product_number", columnList = "number")})
public class Product extends AbstractAggregateRoot {

    private String name;

    private String number;

    @Enumerated(EnumType.STRING)
    private State state = State.enable;

    private Long categoryId;

    @OneToMany(targetEntity = ProductSourceUrl.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinTable(name = "crm_product_pcVideos", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "source_id"))
    @OrderColumn(name = "orderNumber")
    private List<ProductSourceUrl> pcVideos;

    @OneToMany(targetEntity = ProductSourceUrl.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinTable(name = "crm_product_pcImages", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "source_id"))
    @OrderColumn(name = "orderNumber")
    private List<ProductSourceUrl> pcImages;

    @OneToMany(targetEntity = ProductSourceUrl.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinTable(name = "crm_product_mobileImages", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "source_id"))
    @OrderColumn(name = "orderNumber")
    private List<ProductSourceUrl> mobileImages;

    private String mobileImage;

    private String pcImage;

    @OneToMany(targetEntity = ProductSourceUrl.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinTable(name = "crm_product_mobileVideos", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "source_id"))
    @OrderColumn(name = "orderNumber")
    private List<ProductSourceUrl> mobileVideos;

    @Column(name = "introduction", columnDefinition = "text")
    private String introduction;
    @Column(name = "delivery", columnDefinition = "text")
    private String delivery;
    @Column(name = "composition", columnDefinition = "text")
    private String composition;
    @Column(name = "postage", scale = 2)
    private BigDecimal postage;

    @OneToMany(targetEntity = Sku.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true, mappedBy = "product")
    @OrderColumn(name = "orderNumber")
    private List<Sku> items;

    @Column(name = "orderNumber", columnDefinition = "integer default 0")
    private int orderNumber;

    @OneToMany(targetEntity = Attach.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true, mappedBy = "product")
    @OrderColumn(name = "orderNumber")
    private List<Attach> attaches;

    @OneToOne(targetEntity = ProductSettings.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true, mappedBy = "product")
    private ProductSettings settings;

    private Integer sale;


    public Product() {
        super();
        this.createdDate = new Date();
        this.lastModifiedDate = new Date();
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(final Integer sale) {
        this.sale = sale;
    }

    public String getMobileImage() {
        return mobileImage;
    }

    public String getPcImage() {
        return pcImage;
    }

    private void modifiedDate() {
        lastModifiedDate = new Date();
    }

    public void setItems(List<Sku> newItems) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.clear();
        items.addAll(newItems);
    }


    public void onDisable() {
        state = State.disable;
        modifiedDate();
    }

    public void onEnable() {
        state = State.enable;
        modifiedDate();
    }

    public void onDelete() {
        deleted = true;
        modifiedDate();
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
        modifiedDate();
    }

    public ProductSettings onSettings() {
        if (settings != null) {
            settings.onProduct(this);
        }
        return settings;
    }

    public void fixImages() {
        if (mobileImages == null || mobileImages.isEmpty()) {
            mobileImage = null;
        } else {
            mobileImage = mobileImages.get(0).getSourceUrl();
        }
        if (pcImages == null || pcImages.isEmpty()) {
            pcImage = null;
        } else {
            pcImage = pcImages.get(0).getSourceUrl();
        }
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public State getState() {
        return state;
    }

    public List<ProductSourceUrl> getPcVideos() {
        return pcVideos;
    }

    public List<ProductSourceUrl> getPcImages() {
        return pcImages;
    }

    public List<ProductSourceUrl> getMobileImages() {
        return mobileImages;
    }

    public List<ProductSourceUrl> getMobileVideos() {
        return mobileVideos;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getComposition() {
        return composition;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public Collection<Sku> getItems() {
        return items;
    }

    public boolean isDelete() {
        return deleted;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public List<Attach> getAttaches() {
        return attaches;
    }

    public ProductSettings getSettings() {
        return settings;
    }

    public String getDelivery() {
        return delivery;
    }
}
