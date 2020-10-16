package org.zxb.oauth2.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;
import org.zxb.common.utils.LoggerUtil;
import org.zxb.web.constant.ErrorConstant;
import org.zxb.web.vo.Result;

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
                .body(Result.buildFail(ErrorConstant.AUTH_ERROR, e.getMessage()));
    }

}
