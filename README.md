# 集成CXF发布WebServer服务(SOAP,RESTful,JSON)



## 利用cxf生成soap方式调用WebService代码
```
wsdl2java.bat  -p {包名} -encoding UTF-8 {远程地址}

例：wsdl2java.bat  -p com.yuhi.ws -encoding UTF-8 http://www.webxml.com.cn/WebServices/RandomFontsWebService.wsdl

```

## 直接调用即可
```java
    @Test
    public  void testws(){
        RandomFontsWebService service=new RandomFontsWebService();
//        service.getRandomFonts
        ArrayOfString charFonts = service.getRandomFontsWebServiceSoap().getCharFonts(10);
        ArrayOfString chineseFonts = service.getRandomFontsWebServiceSoap().getChineseFonts(10);
        System.out.println(charFonts);
    }

```

##问题. 
### 使用代码总是请求一个错误的地址
CXF生成代码，因为服务器内部是80端口外网是30744端口转发请求
![问题截图](picture/端口转发问题默认显示还是内部端口.png "login")
在生成的PublicService中发现了会请求这个协议文档，得到请求的地址，所以会出错

### 解决方案：
使用idea的Webservice生成插件生成，会吧这个请求协议文档转换为本地文件
![截图](picture/PublicService请求的webservice协议文件取决了请求的地址.png "login")
然后自己改下这个本地文件加上端口即可解决问题










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

```xml
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

```java
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

```java
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

```xml
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

```xml
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



### http调用方式
访问地址：http://localhost:9090/ws/HelloWorld  
访问参数：  
```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Body>
        <ns2:sayHi xmlns:ns2="http://romte.webservice.yuhi.com/">
            <arg0>kongxx</arg0>
        </ns2:sayHi>
    </soap:Body>
</soap:Envelope>
```
返回参数：  
```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Body>
        <ns2:sayHiResponse xmlns:ns2="http://romte.webservice.yuhi.com/">
            <return>Hello kongxx</return>
        </ns2:sayHiResponse>
    </soap:Body>
</soap:Envelope>
```

###CXF客户端调用方式：
```java
public static void main(String[] args) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(HelloWorld.class);
        factory.setAddress("http://localhost:9090/ws/HelloWorld");
        HelloWorld helloworld = (HelloWorld) factory.create();
        System.out.println(helloworld.sayHi("kongxx"));
        System.exit(0);
    }
```