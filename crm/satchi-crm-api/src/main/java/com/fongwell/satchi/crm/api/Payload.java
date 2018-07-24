package com.fongwell.satchi.crm.api;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by docker on 3/19/18.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payload {


    public static final Payload empty = new Payload();

    protected int code;

    protected String msg;

    protected Object data;

    public Payload(Object data) {
        this.data = data;
    }

    public Payload() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
