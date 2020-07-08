package org.zxb.oauth2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;
import org.zxb.common.dto.Result;
import org.zxb.common.utils.LoggerUtil;

/**
 * 自定义鉴权错误返回
 *
 * @author zjx
 * @date 17:32
 */
@Slf4j
@Component("myDefaultWebResponseExceptionTranslator")
public class MyDefaultWebResponseExceptionTranslator implements WebResponseExceptionTranslator<Result> {

    @Override
    public ResponseEntity<Result> translate(Exception e) throws Exception {
        LoggerUtil.error(log, e);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Result.buildFail("10000", e.getMessage()));
    }

}
