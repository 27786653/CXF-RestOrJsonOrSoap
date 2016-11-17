package com.sn.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="UserInfo")  
@XmlAccessorType(XmlAccessType.PROPERTY)  
@XmlType 
public class UserInfo implements Serializable {  
      
    private String id;  
    private String name;// 用户姓名  
    private String sex;// 性别  
    private String idNo;//身份证号码  
    private String account;// 账号  
    private String pwd;// 密码  
    @XmlElement(name="id",required=false) 
    public String getId() {  
        return id;  
    }  
  
    public void setId(String id) {  
        this.id = id;  
    }  
    @XmlElement(name="name",required=false)  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
    @XmlElement(name="account",required=false)  
    public String getAccount() {  
        return account;  
    }  
  
    public void setAccount(String account) {  
        this.account = account;  
    }  
  
    public String getPwd() {  
        return pwd;  
    }  
  
    public void setPwd(String pwd) {  
        this.pwd = pwd;  
    }  
    @XmlElement(name="sex",required=false)  
    public String getSex() {  
        return sex;  
    }  
  
    public void setSex(String sex) {  
        this.sex = sex;  
    }  
    @XmlElement(name="no",required=false)  
    public String getIdNo() {  
        return idNo;  
    }  
  
    public void setIdNo(String idNo) {  
        this.idNo = idNo;  
    }  
}  