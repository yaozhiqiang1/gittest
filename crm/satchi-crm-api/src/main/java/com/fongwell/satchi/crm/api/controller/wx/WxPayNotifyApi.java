package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.satchi.crm.core.order.checkout.service.CheckoutService;
import com.fongwell.satchi.crm.core.payment.gateway.dto.PaymentGatewayResponse;
import com.fongwell.satchi.crm.wx.wxpay.WxPayAccountRepository;
import com.foxinmy.weixin4j.http.weixin.XmlResult;
import com.foxinmy.weixin4j.model.WeixinPayAccount;
import com.foxinmy.weixin4j.payment.WeixinPayProxy;
import com.foxinmy.weixin4j.payment.mch.Order;
import com.foxinmy.weixin4j.util.Consts;
import com.foxinmy.weixin4j.util.IOUtil;
import com.foxinmy.weixin4j.util.StringUtil;
import com.foxinmy.weixin4j.xml.ListsuffixResultDeserializer;
import com.foxinmy.weixin4j.xml.XmlStream;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by docker on 4/28/18.
 */
@RestController
@RequestMapping("/api/wxpay/notify")
public class WxPayNotifyApi {

    @Resource(name = "wxPayAccountRepository")
    private WxPayAccountRepository wxPayAccountRepository;

    @Resource(name = "checkoutService")
    private CheckoutService checkoutService;


    @PostMapping("/{client}")
    @ResponseBody
    public String notifyWxPay(@PathVariable String client, InputStream input) throws IOException {

        WeixinPayAccount account = wxPayAccountRepository.findAccount(client);
        String content = StringUtil.newStringUtf8(IOUtil.toByteArray(input));
        Order order = ListsuffixResultDeserializer.deserialize(content,
                Order.class);


        String sign = order.getSign();
        String valid_sign = new WeixinPayProxy(account).getWeixinSignature().sign(order);

        if (!sign.equals(valid_sign)) {
            return XmlStream.toXML(new XmlResult(Consts.FAIL, "签名错误"));
        }

        PaymentGatewayResponse payment = new PaymentGatewayResponse();
        payment.setAmount(new BigDecimal(order.getTotalFee()).divide(new BigDecimal(100)));
        payment.setGatewayType("wx");
        payment.setTransactionId(order.getTransactionId());
        payment.setPaymentDate(new Date());
        Long orderId = Long.valueOf(order.getOutTradeNo().split("_")[0]);
        payment.setOrderId(orderId);
        checkoutService.applyPayment(orderId, payment);


        // TODO 处理业务逻辑，如没有特殊要求可以考虑单独启一个线程去处理自己的业务，对于微信签名过后就可以返回success了。
        // 需要ajax的形式返回给微信，保证返回值能写到ResponseInputStream就行，Spring mvc使用 @ResponseBody注解，Servlet使用HttpServletResponse#getWrite#write
        return XmlStream.toXML(new XmlResult(Consts.SUCCESS, ""));

    }
}
