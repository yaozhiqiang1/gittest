package com.fongwell.satchi.crm.core.series.dto;

import com.fongwell.satchi.crm.core.common.resource.SourceType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by docker on 3/28/18.
 */
public class SeriesData implements Serializable {


    @NotEmpty(message = "title.required")
    @Length(max = 20)
    private String title;
    private String introduction;

    @Length(max = 20)
    private String buttonText;

    private String imageUrl;

    private String videoUrl;

    private Integer opacity = 100;

    private SourceType source;


    private List<Long> productIds;

    public SourceType getSource() {
        return source;
    }

    public void setSource(SourceType source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(final String introduction) {
        this.introduction = introduction;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(final String buttonText) {
        this.buttonText = buttonText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(final String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getOpacity() {
        return opacity;
    }

    public void setOpacity(final Integer opacity) {
        this.opacity = opacity;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(final List<Long> productIds) {
        this.productIds = productIds;
    }
}
