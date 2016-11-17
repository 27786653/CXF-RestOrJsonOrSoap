package com.sn.webservice.rest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.sn.entity.book;
import com.sn.service.FooBarService;
import com.sn.webservice.util.BaseWsRespnose;

@Component("bookServiceWS")
public class BookServiceWS extends BaseWsRespnose {
	
 
	
	/**
	 * 服务列表：http://localhost:8080/Spring-CXF/ws/ 
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	public Response getbooks(@PathParam("id") String id) {
		book b=new book();
		b.setAuthon("authon"+id);
		b.setCreateTime(new Date());
		b.setPrice(5.0);
		return respnoseJsonMes(b);
	}
	
	
}
