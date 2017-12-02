package com.commerce.platform.config;

import com.commerce.platform.util.HttpClientUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.params.CoreConnectionPNames;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


/**
 * http设置
 * Created by java_ztx on 2017/11/29.
 */
@SuppressWarnings("deprecation")
@Configuration
public class HttpClientConfig {
    @Value("${http.socketTimeout}")
    private int socketTimeout;
    @Value("${http.connectTimeout}")
    private int connectTimeout;
    @Value("${http.connectionRequestTimeout}")
    private int connectionRequestTimeout;

    @Bean(name = "httpClientObj", destroyMethod = "close")
    @Scope("prototype")
    public HttpClient getHttpClient() throws KeyManagementException, NoSuchAlgorithmException {
        HttpClient client = HttpClientUtil.getInstance();
        client.getParams().setParameter(ConnManagerParams.MAX_CONNECTIONS_PER_ROUTE,100); // 设置每个路由最大连接数
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,15000);//连接时间
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,90000);//数据传输时间
        return client;
    }


    @Bean(name = "httpClientRequestConfig")
    public RequestConfig getConfig() {
        return RequestConfig.custom().setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout).build();// 设置请求和传输超
    }
}
