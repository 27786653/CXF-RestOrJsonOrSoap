# 集成CXF发布WebServer服务(SOAP,RESTful,JSON)

---

## 项目介绍
> CXF结合spring发布WS服务，含SOAP services、RESTful services  
不想听废话，直接上代码  
代码URL: [GITHUB走你{我是代码;}](https://github.com/27786653/CXF-RestOrJsonOrSoap)  
1. RESTful:在包com.yuhi.webservice.rest下  
2. SOAP:在包com.yuhi.webservice.romte下  
3. BaseWsRespnose序列化数据返回
4. 服务列表可访问：http://localhost:8080/Spring-CXF/ws/
### applicationContext.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:jaxrs="http://cxf.apache.org/jaxrs"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:jaxws="http://cxf.apache.org/jaxws" 
	xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
       http://cxf.apache.org/jaxrs http://cxf.apache.org/schemas/jaxrs.xsd
       http://www.springframework.org/schema/context 
       http://www.springframework.org/schema/context/spring-context.xsd
       http://cxf.apache.org/jaxws http://cxf.apache.org/schemas/jaxws.xsd
       ">
	<!-- 扫描注解 -->
	<context:component-scan base-package="com.yuhi"></context:component-scan>
	<!-- soap -->
	<jaxws:endpoint 
	  id="helloWorld" 
	  implementor="com.yuhi.webservice.romte.impl.HelloWorldImpl" 
	  address="/HelloWorld" />
	
	<!-- 发布RESTful服务 -->
	<jaxrs:server id="fooBarWs" address="/foobar">
		<jaxrs:serviceBeans>
			<ref bean="fooBarWS" />
		</jaxrs:serviceBeans>
	</jaxrs:server>
	
	<jaxrs:server id="bookServiceWs" address="/book">
		<jaxrs:serviceBeans>
			<ref bean="bookServiceWS" />
		</jaxrs:serviceBeans>
	</jaxrs:server>
</beans>
```
### soap实现

```
//接口
@WebService
public interface HelloWorld {
    String sayHi(String text);
}
//实现类
@WebService(endpointInterface = "com.yuhi.webservice.romte.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello " + text;
    }
}
```
### restful实现

```
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
```
### pom依赖

```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.yuhi</groupId>
	<artifactId>CXF-RestOrJsonOrSoap</artifactId>
	<packaging>war</packaging>
	<version>1.0-SNAPSHOT</version>
	<name>CXF Maven</name>
	<url>http://maven.apache.org</url>
	
	<properties>
		<spring.version>3.1.0.RELEASE</spring.version>
		<cxf.version>2.7.18</cxf.version>
	</properties>
	<dependencies>
		<!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.7</version>
</dependency>
		
		<dependency>
			<groupId>org.apache.cxf</groupId>
			<artifactId>cxf-rt-frontend-jaxws</artifactId>
			<version>${cxf.version}</version>
		</dependency>

		<dependency>
			<groupId>org.apache.cxf</groupId>
			<artifactId>cxf-rt-frontend-jaxrs</artifactId>
			<version>${cxf.version}</version>
		</dependency>

		<dependency>
			<groupId>org.apache.cxf</groupId>
			<artifactId>cxf-rt-transports-http</artifactId>
			<version>${cxf.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${spring.version}</version>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>${spring.version}</version>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-oxm</artifactId>
			<version>${spring.version}</version>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${spring.version}</version>
		</dependency>
	</dependencies>
	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<version>2.1.1</version>
			</plugin>
			<plugin>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<source>1.5</source>
					<target>1.5</target>
				</configuration>
			</plugin>
		</plugins>
		<finalName>Spring-CXF</finalName>
	</build>
</project>

```
### web.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/j2ee" xmlns:web="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd" id="WebApp_ID" version="2.4">
  <display-name>Spring CXF</display-name>
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
  </context-param>
  <listener>
    <listener-class>
                        org.springframework.web.context.ContextLoaderListener
        </listener-class>
  </listener>
  <servlet>
    <display-name>CXF Servlet</display-name>
    <servlet-name>CXFServlet</servlet-name>
    <servlet-class>
			org.apache.cxf.transport.servlet.CXFServlet
		</servlet-class>
    <load-on-startup>2</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>CXFServlet</servlet-name>
    <url-pattern>/ws/*</url-pattern>
  </servlet-mapping>
</web-app>
```

