package com.fongwell.satchi.crm.wx.checkout.payment;

import com.fongwell.satchi.crm.core.order.checkout.service.CheckoutService;
import com.fongwell.satchi.crm.core.payment.gateway.PaymentGatewayCheckoutRequest;
import com.fongwell.satchi.crm.core.payment.gateway.dto.PaymentGatewayResponse;
import com.fongwell.satchi.crm.core.payment.gateway.provider.PaymentGatewayProvider;
import com.fongwell.satchi.crm.wx.wxpay.WxPayAccountRepository;
import com.fongwell.support.utils.Assert;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.payment.WeixinPayProxy;
import com.foxinmy.weixin4j.payment.mch.MchPayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by docker on 4/23/18.
 */
@Component
public class WxPaymentGatewayProvider implements PaymentGatewayProvider {

    private static final String IP;

    static {
        try {
            IP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private static final BigDecimal HUNDRED = new BigDecimal("100");


    @Resource(name = "checkoutService")
    private CheckoutService checkoutService;

    @Value("${wx.pay.enabled:false}")
    private boolean enabled;

    @Autowired(required = false)
    private WxPayAccountRepository wxPayAccountRepository;


    @Override
    public Object initCheckout(final PaymentGatewayCheckoutRequest request) {

        if (enabled) {

            Map data = request.getData();
            Assert.notNull(data, "data");
            WxPaymentGatewayRequest wxPayRequest = (WxPaymentGatewayRequest) request.getData().get("wxpay");

            Assert.notNull(wxPayRequest, "wxpay data");
            Assert.notNull(wxPayRequest.getOpenId(), "openId");


            if (wxPayRequest.getCreateIp() == null) {
                wxPayRequest.setCreateIp(IP);
            }
            request.setAmount(new BigDecimal("0.01"));
            try {
                MchPayRequest jsPayRequest = new WeixinPayProxy(wxPayAccountRepository.findAccount(wxPayRequest.getClient())).createJSPayRequest(wxPayRequest.getOpenId(), wxPayRequest.getBody(), String.valueOf(request.getOrderId()) + "_" + System.currentTimeMillis(),
                        request.getAmount().doubleValue(), wxPayRequest.getNotifyUrl(), wxPayRequest.getCreateIp(), wxPayRequest.getAttach());


                return jsPayRequest.toRequestObject();
            } catch (WeixinException e) {
                throw new WxPaymentGatewayException(e);
            }


        } else {
            PaymentGatewayResponse payment = new PaymentGatewayResponse();
            payment.setAmount(request.getAmount());
            payment.setGatewayType("wx");
            payment.setTransactionId(UUID.randomUUID().toString());
            payment.setPaymentDate(new Date());
            payment.setOrderId(request.getOrderId());
            checkoutService.applyPayment(request.getOrderId(), payment);
        }


        return null;
    }

    @Override
    public String getSupportedType() {
        return "wx";
    }
}