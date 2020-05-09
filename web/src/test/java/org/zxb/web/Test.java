package org.zxb.web;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;
import org.springframework.web.client.RestTemplate;
import org.zxb.web.bean.User;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;

public class Test {

    public static void main(String[] args) throws BindException {
//        User user = new User();
//        DataBinder binder = new DataBinder(user, "user");
//        MutablePropertyValues pvs = new MutablePropertyValues();
//        pvs.add("name", "fsx");
//        pvs.add("age", 18);
//        pvs.add("address","xxxxx");
//
//        binder.bind(pvs);
//
//        binder.validate();
//
//        Map<?, ?> close = binder.close();
//
//        System.out.println(user);
//        System.out.println(close);
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 9999)));
        RestTemplate restTemplate = new RestTemplate(factory);

        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8081//proxy/test?id=123", String.class);
        System.out.println(forEntity.getBody());
    }
}
