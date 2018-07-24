package com.fongwell.satchi.crm.wx.sdk.token;

import com.fongwell.support.lang.json.Json;
import com.foxinmy.weixin4j.model.Token;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by docker on 10/26/17.
 */
@Entity
@Table(name = "wx_access_token")
public class TokenEntity {

    @Id
    private String id;
    private long expires;
    private long createTime;
    private String extraJson;
    private String accessToken;

    @Version
    private Integer version;

    TokenEntity(String id, Token token) {
        this.id = id;
        merge(token);

    }

    TokenEntity() {
    }

    void merge(Token token) {
        this.expires = token.getExpires();
        this.createTime = token.getCreateTime();
        this.accessToken = token.getAccessToken();
        Map<String, String> extra = token.getExtra();
        if (extra != null) {
            extraJson = Json.toJson(extra);
        }

    }

    Token toToken() {
        Token token = new Token(accessToken, expires, createTime);

        if (extraJson != null) {
            HashMap<String, String> map = Json.fromJson(extraJson, HashMap.class);
            token.getExtra().putAll(map);
        }
        return token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getExtraJson() {
        return extraJson;
    }

    public void setExtraJson(String extraJson) {
        this.extraJson = extraJson;
    }
}
