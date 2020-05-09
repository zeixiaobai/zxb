package org.zxb;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) throws Exception {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> re = restTemplate.postForEntity("http://127.0.0.1:8080/soap/user?wsdl", null, String.class);
//        System.out.println(re);

        StringBuilder soapBuilder = new StringBuilder(64);
        soapBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        soapBuilder.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.soft.com/\">");
        soapBuilder.append("   <soapenv:Header/>");
        soapBuilder.append("       <soapenv:Body>");
        soapBuilder.append("             <web:getUser>");
        soapBuilder.append("                     <userId>").append("4f66a225c0a2408f9a5f91767d9d65cb").append("</userId>");
        soapBuilder.append("               </web:getUser>");
        soapBuilder.append("    </soapenv:Body>");
        soapBuilder.append("</soapenv:Envelope>");

        sendPost("http://127.0.0.1:8080/soap/user?wsdl", soapBuilder.toString());
    }

    // 发送post请求
    public static String sendPost(String url, String param) {
        String result = "";
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            URL resUrl = new URL(url);
            URLConnection urlConnec = resUrl.openConnection();
            urlConnec.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
            urlConnec.setRequestProperty("accept", "*/*");
            urlConnec.setRequestProperty("connection", "Keep-Alive");
            urlConnec.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            urlConnec.setDoInput(true);
            urlConnec.setDoOutput(true);

            out = new PrintWriter(urlConnec.getOutputStream());
            out.print(param);// 发送post参数
            out.flush();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(urlConnec.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("post请求发送失败" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    private void test12() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://127.0.0.1:8080/soap/user?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("getUser", "4f66a225c0a2408f9a5f91767d9d65cb");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
