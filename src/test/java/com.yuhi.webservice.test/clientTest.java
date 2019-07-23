package com.yuhi.webservice.test;

import com.yuhi.webservice.romte.HelloWorld;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author lsl
 * @version V1.0
 * @date 2019/7/23 15:54
 */
public class clientTest {

    public static void main(String[] args) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(HelloWorld.class);
        factory.setAddress("http://localhost:9090/ws/HelloWorld");
        HelloWorld helloworld = (HelloWorld) factory.create();
        System.out.println(helloworld.sayHi("kongxx"));
        System.exit(0);
    }

}
