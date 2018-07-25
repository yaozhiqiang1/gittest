package com.fongwell.satchi.crm.wx.checkout.payment;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fongwell.satchi.crm.core.customer.query.mapper.WxCustomerQueryMapper;
import com.fongwell.satchi.crm.core.order.checkout.service.CheckoutService;
import com.fongwell.satchi.crm.core.payment.gateway.PaymentGatewayCheckoutRequest;
import com.fongwell.satchi.crm.core.payment.gateway.dto.PaymentGatewayResponse;
import com.fongwell.satchi.crm.core.payment.gateway.provider.PaymentGatewayProvider;
import com.fongwell.satchi.crm.wx.user.binding.WxUserBindingService;
import com.fongwell.satchi.crm.wx.wxpay.WxPayAccountRepository;
import com.fongwell.support.utils.Assert;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.payment.WeixinPayProxy;
import com.foxinmy.weixin4j.payment.mch.MchPayRequest;

/**
 * Created by docker on 4/23/18.
 */
@Component
public class WxPaymentGatewayProvider implements PaymentGatewayProvider {

	@Autowired
	private WxCustomerQueryMapper wxCustomerQueryMapper;

	
	
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
    	System.out.println("-------------------WxPaymentGatewayProvider.initCheckout:"+request.getCustomerId());
        if (enabled) {
            Map data = request.getData();
            Assert.notNull(data, "data");
            WxPaymentGatewayRequest wxPayRequest = (WxPaymentGatewayRequest) request.getData().get("wxpay");

            Assert.notNull(wxPayRequest, "wxpay data");
            //Assert.notNull(wxPayRequest.getOpenId(), "openId");
            //wxUserBindingService
            
            //wxPayRequest.setOpenId("ooBmhjmvx-24uvwWSskddE_PdTuY");
            if(null==wxPayRequest.getOpenId()||wxPayRequest.getOpenId().length()<1) {
            	System.out.println("openId = null");
            	String opendId=wxCustomerQueryMapper.getOpenId(request.getCustomerId());
            	System.out.println("opendId:"+opendId);
            	System.out.println("set===================");
            	wxPayRequest.setOpenId(opendId);;
            }
            
            
            if (wxPayRequest.getCreateIp() == null) {
                wxPayRequest.setCreateIp(IP);
            }
            request.setAmount(new BigDecimal("0.01"));
            
            try {
                MchPayRequest jsPayRequest = new WeixinPayProxy(wxPayAccountRepository.findAccount(wxPayRequest.getClient())).createJSPayRequest(wxPayRequest.getOpenId(), wxPayRequest.getBody(), String.valueOf(request.getOrderId()) + "_" + System.currentTimeMillis(),
                        request.getAmount().doubleValue(), wxPayRequest.getNotifyUrl(), wxPayRequest.getCreateIp(), wxPayRequest.getAttach());
                System.out.println("-----------------------------request.getOrderId():"+request.getOrderId());
                wxCustomerQueryMapper.updateType(request.getOrderId());
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
