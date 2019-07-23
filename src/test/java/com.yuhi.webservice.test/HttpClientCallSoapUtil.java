package com.yuhi.webservice.test;

import java.nio.charset.Charset;
import org.apache.http.HttpEntity;  
import org.apache.http.client.config.RequestConfig;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.entity.StringEntity;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClientBuilder;  
import org.apache.http.util.EntityUtils;  

public class HttpClientCallSoapUtil {  
    static int socketTimeout = 30000;// 请求超时时间  
    static int connectTimeout = 30000;// 传输超时时间  
//    static Logger logger = Logger.getLogger(HttpClientCallSoapUtil.class);
  
    /** 
     * 使用SOAP1.1发送消息 
     *  
     * @param postUrl 
     * @param soapXml 
     * @param soapAction 
     * @return 
     */  
    public static String doPostSoap1_1(String postUrl, String soapXml,  
            String soapAction) {  
        String retStr = "";  
        // 创建HttpClientBuilder  
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        // HttpClient  
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
        HttpPost httpPost = new HttpPost(postUrl);  
                //  设置请求和传输超时时间  
        RequestConfig requestConfig = RequestConfig.custom()  
                .setSocketTimeout(socketTimeout)  
                .setConnectTimeout(connectTimeout).build();  
        httpPost.setConfig(requestConfig);  
        try {  
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");  
            httpPost.setHeader("SOAPAction", soapAction);  
            StringEntity data = new StringEntity(soapXml,  
                    Charset.forName("UTF-8"));  
            httpPost.setEntity(data);  
            CloseableHttpResponse response = closeableHttpClient  
                    .execute(httpPost);  
            HttpEntity httpEntity = response.getEntity();  
            if (httpEntity != null) {  
                // 打印响应内容  
                retStr = EntityUtils.toString(httpEntity, "UTF-8");  
//                logger.info("response:" + retStr);
            }  
            // 释放资源  
            closeableHttpClient.close();  
        } catch (Exception e) {
            e.printStackTrace();
//            logger.error("exception in doPostSoap1_1", e);
        }  
        return retStr;  
    }  
  
    /** 
     * 使用SOAP1.2发送消息 
     *  
     * @param postUrl 
     * @param soapXml 
     * @param soapAction 
     * @return 
     */  
    public static String doPostSoap1_2(String postUrl, String soapXml,  
            String soapAction) {  
        String retStr = "";  
        // 创建HttpClientBuilder  
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();  
        // HttpClient  
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();  
        HttpPost httpPost = new HttpPost(postUrl);  
                // 设置请求和传输超时时间  
        RequestConfig requestConfig = RequestConfig.custom()  
                .setSocketTimeout(socketTimeout)  
                .setConnectTimeout(connectTimeout).build();  
        httpPost.setConfig(requestConfig);  
        try {  
            httpPost.setHeader("Content-Type",  
                    "application/soap+xml;charset=UTF-8");  
            httpPost.setHeader("SOAPAction", soapAction);  
            StringEntity data = new StringEntity(soapXml,  
                    Charset.forName("UTF-8"));  
            httpPost.setEntity(data);  
            CloseableHttpResponse response = closeableHttpClient  
                    .execute(httpPost);  
            HttpEntity httpEntity = response.getEntity();  
            if (httpEntity != null) {  
                // 打印响应内容  
                retStr = EntityUtils.toString(httpEntity, "UTF-8");  
//                logger.info("response:" + retStr);
            }  
            // 释放资源  
            closeableHttpClient.close();  
        } catch (Exception e) {
            e.printStackTrace();
//            logger.error("exception in doPostSoap1_2", e);
        }  
        return retStr;  
    }  
  
    public static void main(String[] args) {  


        String querySoapXml ="<?xml version=\"1.0\" encoding=\"utf-16\"?>" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "<soap:Body>" +
                "<AisQuery xmlns=\"http://www.kingdee.com/Public\"/>" +
                "</soap:Body>" +
                "</soap:Envelope>";

        doPostSoap1_1("http://mt.yuhi.com.cn:30744/KDWEBSERVICE/Public.asmx", querySoapXml, "http://www.kingdee.com/Public/AisQuery");
  
        //采用SOAP1.2调用服务端，这种方式只能调用服务端为soap1.2的服务  
        //doPostSoap1_2(postUrl, orderSoapXml, "order");  
        //doPostSoap1_2(postUrl, querySoapXml, "query");  
    }  
}  