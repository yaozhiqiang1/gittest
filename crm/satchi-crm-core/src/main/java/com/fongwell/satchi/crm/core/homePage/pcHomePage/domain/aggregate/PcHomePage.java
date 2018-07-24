package com.fongwell.satchi.crm.core.homePage.pcHomePage.domain.aggregate;

import com.fongwell.satchi.crm.core.common.State;
import com.fongwell.satchi.crm.core.homePage.value.LinkConfig;
import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by roman on 18-3-24.
 */
@Entity
@Table(name = "crm_pc_home_page")
public class PcHomePage extends AbstractAggregateRoot{

    @Column(name = "title",length = 30)
    private String title;
    @Column(name = "url",columnDefinition = "text")
    private String url;
    @Column(name = "introduction",columnDefinition = "text")
    private String introduction;
    @Column(name = "button",length = 12)
    private String button;
    @Column(name = "imageUrl",columnDefinition = "text")
    private String imageUrl;
    @Column(name = "videoUrl",columnDefinition = "text")
    private String videoUrl;
    @Column(name = "source",length = 20)
    private String source;
    @Column(name = "opacity",columnDefinition = "smallint")
    private Integer opacity;
    @Column(name = "orderNumber",length = 3)
    private int orderNumber;
    @Enumerated(EnumType.STRING)
    private State state = State.disable;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "type", column = @Column(name = "link_type", length = 10)),
            @AttributeOverride(name = "id", column = @Column(name = "link_id"))})
    private LinkConfig linkConfig;


    public PcHomePage() {
        super();
        createdDate = new Date();
        lastModifiedDate = new Date();
    }

    public void onEnable(){
        this.state = State.enable;
        modifiedDate();
    }

    public void onSort(int orderNumber){
        this.orderNumber = orderNumber;
        modifiedDate();
    }

    public void onDisable(){
        this.state = State.disable;
        modifiedDate();
    }

    public void onDelete(){
        this.deleted = true;
        modifiedDate();
    }

    public void modifiedDate(){
        this.lastModifiedDate = new Date();
    }


    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getButton() {
        return button;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSource() {
        return source;
    }

    public Integer getOpacity() {
        return opacity;
    }

    public String getIntroduction() {
        return introduction;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public State getState() {
        return state;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj == null && obj.getClass() != this.getClass()){
            return false;
        }
        PcHomePage that = (PcHomePage) obj;
        if(this.hashCode() == that.hashCode()){
            return true;
        }
        return false;
    }
}
