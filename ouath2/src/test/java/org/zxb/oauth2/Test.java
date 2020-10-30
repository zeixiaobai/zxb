package org.zxb.oauth2;

import org.springframework.http.*;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

/**
 * @description: TODO
 * @author: zjx
 * @time: 2020/1/12 17:40
 */
public class Test {

    public static void main(String[] args) {
        checkToken(getToken());
    }

    public static void post() {
        RemoteTokenServices rts = new RemoteTokenServices();
        rts.setCheckTokenEndpointUrl("http://localhost:8080/login");
        rts.setClientId("clientId");
        rts.setClientSecret("secret");
        rts.setRestTemplate(new RestTemplate());
        OAuth2Authentication oa = rts.loadAuthentication("cdb5fb86-13f2-4cad-854c-eab03851869a");
    }

    public static void get() {
        RestTemplate r = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.AUTHORIZATION, "bearer e714220f-a5f9-40a5-bef4-de2aeb91168b");
        HttpEntity<String> entity = new HttpEntity<>("{\"username\":\"1233123\"}", headers);
        ResponseEntity<String> resp = r.exchange(
                "http://localhost:8080/hello?access_token=2a2f616a-b0a5-4675-833d-34a3598ed7a0",
                HttpMethod.GET, entity, String.class);
        System.out.println(resp.getBody());
        //Basic Y2xpZW50SWQ6c2VjcmV0
    }

    public static void checkToken(String token) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
        formData.add("token", token+"1");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", getAuthorizationHeader("clientId", "secret"));
        Map<String, Object> map = postForMap("http://localhost:8080/oauth/check_token", formData, headers);
        System.out.println(map);
    }

    private static String getAuthorizationHeader(String clientId, String clientSecret) {
        String creds = String.format("%s:%s", clientId, clientSecret);
        try {
            return "Basic " + new String(Base64.getEncoder().encode(creds.getBytes("UTF-8")));
        }
        catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Could not convert String");
        }
    }

    private static Map<String, Object> postForMap(String path, MultiValueMap<String, String> formData, HttpHeaders headers) {
        if (headers.getContentType() == null) {
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }
        Map map = new RestTemplate().exchange(path, HttpMethod.POST,
                new HttpEntity<MultiValueMap<String, String>>(formData, headers), Map.class).getBody();
        return map;
    }

    public static String getToken() {
        RestTemplate r = new RestTemplate();
        ResponseEntity<DefaultOAuth2AccessToken> resp = r.exchange(
                "http://localhost:8080/oauth/token?client_id=clientId&client_secret=secret&grant_type=client_credentials&username=zhangsan&password=123456",
                HttpMethod.GET, null, DefaultOAuth2AccessToken.class);
        System.out.println(resp.getBody().getValue());
        return resp.getBody().getValue();
    }

}
