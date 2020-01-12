package org.zxb.ouath2;

import org.springframework.http.*;
import org.springframework.security.crypto.codec.Base64;
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

        post();
    }

    public static void post() {
        RemoteTokenServices rts = new RemoteTokenServices();
        rts.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
        rts.setClientId("clientId");
        rts.setClientSecret("secret");
        rts.setRestTemplate(new RestTemplate());
        rts.loadAuthentication("8add7806-4c6f-426a-bda9-07478d03729f");
    }

    public static void get(){
        RestTemplate r = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.AUTHORIZATION, "bearer 71e53286-47b3-4738-b8fa-362df6394a5b");
        headers.add(HttpHeaders.AUTHORIZATION, "Basic Y2xpZW50SWQ6c2VjcmV0");
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> resp = r.exchange("http://localhost:8080/hello?token=8add7806-4c6f-426a-bda9-07478d03729f", HttpMethod.GET, entity, String.class);
        System.out.println(resp.getBody());
        //Basic Y2xpZW50SWQ6c2VjcmV0
    }


}
