package org.zxb.common.utils;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * @description: rest 封装工具类
 * @author: zjx
 * @time: 2020/1/8 21:42
 */
public class ResttemplateUtil {

    /**
     * post 请求
     *
     * @param url
     * @param body
     * @return {@link String}
     * @author zjx
     * @date 2020/10/16 17:28
     */
    public static String post(String url, Object body) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity httpEntity = new HttpEntity(body, httpHeaders);
        ResponseEntity<String> re = SpringContextHolder.getBean(RestTemplate.class).exchange(url, HttpMethod.POST, httpEntity, String.class);
        return re.getBody();
    }

}
