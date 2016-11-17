package com.yuhi.webservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuhi.entity.UserInfo;
import com.yuhi.service.FooBarService;
import com.yuhi.webservice.util.BaseWsRespnose;

@Component
public class FooBarWS extends BaseWsRespnose {
	@Autowired
	private FooBarService fooBarService;
 
	@GET
	@Path("/{param}")
	public Response getMessage(@PathParam("param") String msg) {
		String respnosemes=fooBarService.getMessage(msg);
		return respnoseStringMes(respnosemes);
	}

	
	@GET
	@Path("/mes/{id}")
	public Response getbooks(@PathParam("id") String id) {
		UserInfo b=new UserInfo();
		b.setAccount("authon"+id);
		b.setId(id);
		b.setName("id:"+5.0);
		return respnoseJsonMes(b);
	}
	
	
	public FooBarService getFooBarService() {
		return fooBarService;
	}

	public void setFooBarService(FooBarService fooBarService) {
		this.fooBarService = fooBarService;
	}
	
}
