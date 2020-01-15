package org.zxb.ouath2;

import org.springframework.http.*;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO
 * @author: zjx
 * @time: 2020/1/12 17:40
 */
public class Test {

    public static void main(String[] args) {
        get();
    }

    public static void post() {
        RemoteTokenServices rts = new RemoteTokenServices();
        rts.setCheckTokenEndpointUrl("http://localhost:8080/login");
        rts.setClientId("clientId");
        rts.setClientSecret("secret");
        rts.setRestTemplate(new RestTemplate());
        OAuth2Authentication oa = rts.loadAuthentication("cdb5fb86-13f2-4cad-854c-eab03851869a");
    }

    public static void get(){
        RestTemplate r = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "bearer cdb5fb86-13f2-4cad-854c-eab03851869a");
        HttpEntity<String> entity = new HttpEntity<>("{\"username\":\"1233123\"}", headers);
        ResponseEntity<String> resp = r.exchange("http://localhost:8080/login", HttpMethod.POST, entity, String.class);
        System.out.println(resp.getBody());
        //Basic Y2xpZW50SWQ6c2VjcmV0
    }


}
