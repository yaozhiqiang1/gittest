package com.fongwell.satchi.crm.api;

import com.fongwell.base.resource.qiniu.QiniuConfigurationImpl;
import com.fongwell.base.resource.qiniu.QiniuServiceImpl;
import com.fongwell.base.resource.qiniu.QiniuTemplate;
import com.fongwell.base.resource.qiniu.QiniuTemplateImpl;
import com.fongwell.concurrent.lock.JdkLockService;
import com.fongwell.support.http.DefaultHttpClientService;
import com.fongwell.support.http.HttpClientFactoryBean;
import com.fongwell.support.http.HttpClientService;
import com.fongwell.support.http.SocketConfigFactoryBean;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Created by docker on 9/8/17.
 */
@Configuration
public class QiniuConfig {

    @Value("${qiniu.http.maxTotal}")
    private int maxTotal;
    @Value("${qiniu.http.maxPerRoute}")
    private int maxPerRoute;
    @Value("${qiniu.domain}")
    private String qiniuDomain;
    @Value("${qiniu.bucket}")
    private String qiniuBucket;
    @Value("${qiniu.key}")
    private String qiniuKey;
    @Value("${qiniu.secret}")
    private String qiniuSecret;

    @Value("${qiniu.token.timeout}")
    private int timeout;

    @Resource(name = "cacheManager")
    private CacheManager cacheManager;

    @Bean
    public HttpClientFactoryBean qiniuHttpClient() throws Exception {

        HttpClientFactoryBean bean = new HttpClientFactoryBean();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(maxTotal);
        connectionManager.setDefaultMaxPerRoute(maxTotal);

        SocketConfigFactoryBean socketConfig = new SocketConfigFactoryBean();
        socketConfig.setSoKeepAlive(true);
        socketConfig.setTcpNoDelay(true);
        socketConfig.afterPropertiesSet();
        connectionManager.setDefaultSocketConfig(socketConfig.getObject());

        bean.setConnectionManager(connectionManager);
        return bean;

    }

    @Bean
    public QiniuTemplate qiniuTemplate(@Autowired @Qualifier("qiniuHttpClient") HttpClient httpClient) throws Exception {
        HttpClientService httpClientService = new DefaultHttpClientService((CloseableHttpClient) httpClient);

        QiniuConfigurationImpl config = new QiniuConfigurationImpl();
        config.setDomain(qiniuDomain);
        config.setBucket(qiniuBucket);
        config.setKey(qiniuKey);
        config.setSecret(qiniuSecret);
        config.setTimeout(timeout);

        QiniuServiceImpl qiniuService = new QiniuServiceImpl();
        qiniuService.setLockService(new JdkLockService());
        qiniuService.setCacheManager(cacheManager);
        qiniuService.afterPropertiesSet();

        return new QiniuTemplateImpl(config, qiniuService, httpClientService);
    }

}
