package com.fongwell.satchi.crm.api.controller.wx;

import com.fongwell.satchi.crm.api.Payload;
import com.fongwell.satchi.crm.wx.jssdk.JSSDKConfiguratorFactory;
import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.jssdk.JSSDKAPI;
import com.foxinmy.weixin4j.jssdk.JSSDKConfigurator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by docker on 4/28/18.
 */
@RestController
@RequestMapping("/api/wx")
public class WxConfigApi {

    @Value("${wx.jssdk.debug}")
    private Boolean jssdkDebug;

    @Resource(name = "jssdkConfiguratorFactory")
    private JSSDKConfiguratorFactory jssdkConfiguratorFactory;

    @Value("${wx.defaultClient}")
    private String defaultClient;

    @PostMapping("/jssdk")
    public Payload jssdk(@RequestBody JssdkRequest request) throws WeixinException {
        String client = request.getClient();
        if (StringUtils.isBlank(client)) {
            client = defaultClient;
        }
        JSSDKConfigurator jssdkConfigurator = jssdkConfiguratorFactory.create(client).apis(JSSDKAPI.values());
        if (jssdkDebug != null && jssdkDebug) {
            jssdkConfigurator.debugMode();
        }
        return new Payload(jssdkConfigurator.toJSONConfig(request.getUrl()));

    }

    public static class JssdkRequest implements Serializable {
        private String client;
        private String url;

        public String getClient() {
            return client;
        }

        public void setClient(final String client) {
            this.client = client;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(final String url) {
            this.url = url;
        }
    }
}
