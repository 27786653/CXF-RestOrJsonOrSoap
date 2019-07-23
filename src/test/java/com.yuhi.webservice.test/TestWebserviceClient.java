package com.yuhi.webservice.test;


/**
 * 测试远程调用Webservice接口
 *  K3端口30744
 * @author 李森林
 * @date 2017-11-25 23:37
 */
public class TestWebserviceClient {
    /**
     *
     *
     <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
             <soap:Body>
             <Query xmlns="http://www.kingdee.com/Material">
                 <iAisID>1</iAisID>
                 <strUser>Administrator</strUser>
                 <strPassword></strPassword>
                 <iPerCount>1</iPerCount>
                 <strFilter></strFilter>
                 <strTimeStamp></strTimeStamp>
             </Query>
             </soap:Body>
     </soap:Envelope>
     * @throws Exception
     */
//    @Test
//    public void testRemoteApi2() throws Exception {
//        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
//        Client client = clientFactory.createClient("http://mt.yuhi.com.cn:30744" +
//                "/KDWEBSERVICE/Public.asmx?wsdl");
//        Object[] result = client.invoke("AisQuery");
//
//        System.out.println(result[0]);
//    }
//
//
//
//    @Test
//    public void testRemoteApi() throws Exception {
//        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
//        Client client = clientFactory.createClient("http://www.webxml.com.cn/WebServices/RandomFontsWebService.wsdl");
//        Object[] result = client.invoke("getChineseFonts", new Object[]{10});
//
//        System.out.println(result[0]);
//    }
//
//    //传文件,将文件读取为二进制流进行传递,“请求内容”则为二进制流
//    private byte[] getContent(String filePath) throws IOException {
//
//        FileInputStream inputStream = new FileInputStream(filePath);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
//        System.out.println("bytes available: " + inputStream.available());
//
//        byte[] b = new byte[1024];
//        int size = 0;
//
//        while((size = inputStream.read(b)) != -1)
//            outputStream.write(b, 0, size);
//
//        inputStream.close();
//        byte[] bytes = outputStream.toByteArray();
//        outputStream.close();
//
//        return bytes;
//    }
//
//
//    @Test
//    public  void testws(){
//        RandomFontsWebService service=new RandomFontsWebService();
////        service.getRandomFonts
//        ArrayOfString charFonts = service.getRandomFontsWebServiceSoap().getCharFonts(10);
//        ArrayOfString chineseFonts = service.getRandomFontsWebServiceSoap().getChineseFonts(10);
//        System.out.println(charFonts);
//    }
//
//
//    @Test
//    public  void testws2(){
//        PublicService service=new PublicService();
//        Holder<String> strerror = new Holder<String>();
//        Holder<String> queryresult = new Holder<String>();
//        PublicServiceSoap publicServiceSoap = service.getPublicServiceSoap();
//
//        Holder<ArrayOfAisInfo> queryresult2=new Holder<>();
//        publicServiceSoap.aisQuery(queryresult2,strerror);
////        publicServiceSoap.getAisType(1,"Administrator",
////                "",queryresult,strerror);
//        System.out.println("");
//
//
////        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//////        factory.setServiceClass(IEvalService.class);//注册WebService接口
//////        factory.setAddress(CoreConst.PATENT_URL);//设置WebService地址
//////        factory.getOutInterceptors().add(new          ClientLoginInterceptor(CoreConst.PARENT_USERNAME,CoreConst.PARENT_PASSWORD));
//////        IEvalService client = (IEvalService)factory.create();//客户端对象
////        //设置客户端的配置信息，超时等.
////        Client proxy = ClientProxy.getClient(service);
////        HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
////        HTTPClientPolicy policy = new HTTPClientPolicy();
////        policy.setConnectionTimeout(30000); //连接超时时间
////        policy.setReceiveTimeout(180000);//请求超时时间.
////        conduit.setClient(policy);
//
////        ResultData info = service.loadDataFromSearch(pa,ipc, api_key, sign);//调用服务
//    }
//
//
//    @Test
//    public  void testws3(){
//        DemoService service=new DemoService();
//        DemoServiceSoap demoServiceSoap = service.getDemoServiceSoap();
//        ArrayOfUsers userArys=new ArrayOfUsers();
//        Users u=new Users();
//        u.setUserID("12321321");
//        userArys.getUsers().add(u);
//        demoServiceSoap.testBuinese(userArys);
//        System.out.println("");
//    }


}
