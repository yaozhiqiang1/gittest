package com.fongwell.satchi.crm.wx.checkout.payment;

/**
 * Created by docker on 4/26/18.
 */
public class WxPaymentGatewayException extends RuntimeException {
    public WxPaymentGatewayException(String message) {
        super(message);
    }

    public WxPaymentGatewayException(Throwable cause) {
        super(cause);
    }

}
