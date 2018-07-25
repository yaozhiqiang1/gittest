package com.fongwell.satchi.crm.wx.checkout.payment;

import java.io.Serializable;

/**
 * Created by docker on 4/26/18.
 */
public class WxPaymentGatewayRequest implements Serializable {
    private String client;


    private String openId;

    private String body;

    private String createIp;

    private String attach;

    private String notifyUrl;

    public String getClient() {
        return client;
    }

    public void setClient(final String client) {
        this.client = client;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(final String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(final String createIp) {
        this.createIp = createIp;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(final String attach) {
        this.attach = attach;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(final String openId) {
        this.openId = openId;
    }

	@Override
	public String toString() {
		return "WxPaymentGatewayRequest [client=" + client + ", openId=" + openId + ", body=" + body + ", createIp="
				+ createIp + ", attach=" + attach + ", notifyUrl=" + notifyUrl + "]";
	}
    
}
