package org.zxb.common.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @description: TODO
 * @author: zjx
 * @time: 2020/1/8 21:42
 */
public class ResttemplateUtil {

    private static RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void post() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, "");
        HttpEntity httpEntity = new HttpEntity("", httpHeaders);
        ResponseEntity<String> resp = restTemplate.exchange("", HttpMethod.POST, null, String.class);
    }

}
