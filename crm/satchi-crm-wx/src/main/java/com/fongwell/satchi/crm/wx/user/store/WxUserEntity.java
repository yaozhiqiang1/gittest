package com.fongwell.satchi.crm.wx.user.store;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.foxinmy.weixin4j.mp.model.User;

/**
 * Created by docker on 10/21/17.
 */
@Entity
@Table(name = "crm_wx_user")
public class WxUserEntity implements Serializable {

    @Id
    private String id;


    private String nickName;
    private int gender;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private boolean subscribe;
    private long subscribeTime;
    private String language;

    private String unionId;
    private String remark;

    private int groupId;

    @Version
    private Integer version;

    public WxUserEntity(User user) {
        this.id = user.getOpenId();
        this.nickName = user.getNickName();
        this.gender = user.getGender();
        this.province = user.getProvince();
        this.city = user.getCity();
        this.country = user.getCountry();
        this.headimgurl = user.getHeadimgurl();
        this.subscribe = user.isSubscribe();
        this.subscribeTime = user.getSubscribeTime();
        this.language = user.getLanguage();
        this.unionId = user.getUnionId();
        this.remark = user.getRemark();
        this.groupId = user.getGroupId();

    }

    WxUserEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }


    public boolean isSubscribe() {
        return subscribe;
    }

    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }

    public long getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
