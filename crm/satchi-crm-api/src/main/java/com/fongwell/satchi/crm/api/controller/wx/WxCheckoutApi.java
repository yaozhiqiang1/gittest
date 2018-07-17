package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.base.validate.Validate;
import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.api.authentication.wx.WxCustomerContext;
import com.fongwell.satchi.crm.core.order.checkout.service.CheckoutService;
import com.fongwell.satchi.crm.core.order.dto.CartRequest;
import com.fongwell.satchi.crm.core.order.dto.CheckoutRequest;
import com.fongwell.satchi.crm.core.payment.gateway.PaymentGatewayCheckoutRequest;
import com.fongwell.satchi.crm.core.payment.gateway.PaymentGatewayCheckoutService;
import com.fongwell.satchi.crm.wx.checkout.payment.WxPaymentGatewayRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by docker on 4/23/18.
 */
@RequestMapping("/api/wx")
@RestController
public class WxCheckoutApi {

    @Resource(name = "checkoutService")
    private CheckoutService checkoutService;

    @Resource(name = "paymentGatewayCheckoutService")
    private PaymentGatewayCheckoutService paymentGatewayCheckoutService;
    @Value("${wx.defaultClient}")
    private String wxDefaultClient;
    @Value("${wx.pay.notifyUrl}")
    private String wxPayNotifyUrl;

    @Validate
    @PostMapping("/checkout")
    public Payload checkout(@Valid @RequestBody CheckoutRequest request, BindingResult result) {
        return new Payload(checkoutService.checkout(WxCustomerContext.getUser().getCustomerId(), request).getId());
    }


    @PostMapping("/checkout/updateCart")
    @Validate
    public Payload updateCart(@Valid @RequestBody CartRequest request, BindingResult result) {
        return new Payload(checkoutService.updateCart(WxCustomerContext.getUser().getCustomerId(), request.getItems()));
    }

    @PostMapping("/checkout/initCart")
    @Validate
    public Payload initCart(@Valid @RequestBody CartRequest request, BindingResult result) {

        return new Payload(checkoutService.initCart(WxCustomerContext.getUser().getCustomerId(), request.getItems()));

    }

    /**
     * 启动支付
     * @param request
     * @param result
     * @return
     */
    @PostMapping("/checkout/initPayment")
    @Validate
    public Payload initPayment(@Valid
                               @RequestBody PaymentGatewayCheckoutRequest request, BindingResult result) {

        Map data = new HashMap();
        //微信支付请求的实体类
        WxPaymentGatewayRequest wxPaymentGatewayRequest = new WxPaymentGatewayRequest();
        wxPaymentGatewayRequest.setOpenId(WxCustomerContext.getWxId());
        // wxPaymentGatewayRequest.setOpenId("opreGjr2hzDBBpkgYIzpjfYy5iTU");  wxDefaultClient === test
        wxPaymentGatewayRequest.setClient(wxDefaultClient);
        //http://local.ngrok.fongwell.com/api/wxpay/notify/test
        wxPaymentGatewayRequest.setNotifyUrl(wxPayNotifyUrl + "/" + wxDefaultClient);
        wxPaymentGatewayRequest.setBody("test");
        data.put("wxpay", wxPaymentGatewayRequest);

        request.setData(data);
        request.setCustomerId(WxCustomerContext.getUser().getCustomerId());

        return new Payload(paymentGatewayCheckoutService.initCheckout(request));
    }
}
