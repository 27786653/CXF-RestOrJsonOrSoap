package com.sn.webservice.util;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.apache.cxf.common.i18n.Exception;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;

public class BaseWsRespnose {
	/**
	 * 返回json格式化数据
	 * @param array
	 * @return
	 */
	public Response respnoseJsonMes(Object array){
		String jsonstr = JSONArray.toJSONString(array);
		return Response.status(200).entity(jsonstr).build();
	}
	/**
	 * 返回文本
	 * @param str
	 * @return
	 */
	public Response respnoseStringMes(String str){
		return Response.status(200).entity(str).build();
	}
	
	/**
	 * 成功并写入cookie
	 * @param cok
	 * @return
	 * @throws java.lang.Exception 
	 */
	public Response respnoseByCokkie(Cookie cok) throws java.lang.Exception{
		return ByCokkie(new NewCookie(cok));
	}
	/**
	 * 成功并写入cookie(键值对)
	 * @param cok
	 * @return
	 * @throws java.lang.Exception 
	 */
	public Response respnoseByCokkie(String name,String value) throws java.lang.Exception{
		return ByCokkie(new NewCookie(name,value));
	}
	
	/**
	 * 基于cookie构建Msg
	 * @param cok
	 * @return
	 * @throws java.lang.Exception 
	 */
	private  Response ByCokkie(NewCookie cok) throws java.lang.Exception{
		if(cok==null)throw new java.lang.Exception("Cookie not found");
		return Response.status(200).cookie(cok).build();
	}
	
}
