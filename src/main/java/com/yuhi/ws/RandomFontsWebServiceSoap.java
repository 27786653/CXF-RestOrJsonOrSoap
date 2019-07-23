package com.yuhi.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.14
 * 2017-11-26T10:53:28.618+08:00
 * Generated source version: 3.1.14
 * 
 */
@WebService(targetNamespace = "http://WebXml.com.cn/", name = "RandomFontsWebServiceSoap")
@XmlSeeAlso({ObjectFactory.class})
public interface RandomFontsWebServiceSoap {

    /**
     * <br /><h3>获得随机英文、数字Web Services</h3><p>输入参数：byFontsLength = 输出字的数量（Integer）；返回数据：一个一维字符串数组 String()，内容为随机英文、数字。为了避免混淆只输出，只随机产生 1,2,3,4,5,6,7,8,9,A,C,D,E,F,H,K,L,M,N,P,R,S,T,W,X,Y,Z，这里支持最多不超过10个输出，如需要更多输出请<a href="http://www.webxml.com.cn/zh_cn/contact_us.aspx" target="_blank">联系我们</a>。
     */
    @WebResult(name = "getCharFontsResult", targetNamespace = "http://WebXml.com.cn/")
    @RequestWrapper(localName = "getCharFonts", targetNamespace = "http://WebXml.com.cn/", className = "com.yuhi.ws.GetCharFonts")
    @WebMethod(action = "http://WebXml.com.cn/getCharFonts")
    @ResponseWrapper(localName = "getCharFontsResponse", targetNamespace = "http://WebXml.com.cn/", className = "com.yuhi.ws.GetCharFontsResponse")
    public ArrayOfString getCharFonts(
            @WebParam(name = "byFontsLength", targetNamespace = "http://WebXml.com.cn/")
                    int byFontsLength
    );

    /**
     * <br /><h3>获得随机中文简体字Web Services</h3><p>输入参数：byFontsLength = 输出中文字的数量（Integer）；返回数据：一个一维字符串数组 String()，内容为随机中文简体字。这里支持最多不超过8个中文简体字输出，如需要更多输出请<a href="http://www.webxml.com.cn/zh_cn/contact_us.aspx" target="_blank">联系我们</a>。
     */
    @WebResult(name = "getChineseFontsResult", targetNamespace = "http://WebXml.com.cn/")
    @RequestWrapper(localName = "getChineseFonts", targetNamespace = "http://WebXml.com.cn/", className = "com.yuhi.ws.GetChineseFonts")
    @WebMethod(action = "http://WebXml.com.cn/getChineseFonts")
    @ResponseWrapper(localName = "getChineseFontsResponse", targetNamespace = "http://WebXml.com.cn/", className = "com.yuhi.ws.GetChineseFontsResponse")
    public ArrayOfString getChineseFonts(
            @WebParam(name = "byFontsLength", targetNamespace = "http://WebXml.com.cn/")
                    int byFontsLength
    );
}
