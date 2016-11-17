package com.yuhi.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlRootElement(name="book")  
@XmlAccessorType(XmlAccessType.PROPERTY)  
@XmlType 
public class book {

	private String authon;
	private Date createTime;
	private Double price;
	@XmlElement(name="authon",required=false) 
	public String getAuthon() {
		return authon;
	}
	public void setAuthon(String authon) {
		this.authon = authon;
	}
	@XmlElement(name="createTime",required=false) 
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@XmlElement(name="price",required=false) 
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
}
