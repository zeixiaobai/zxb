package org.zxb.web.exception;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zxb.web.dto.Result;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * @author zjx
 * @description 错误拦截切面
 * @date 2020/1/2 0002
 */
@Slf4j
@RestControllerAdvice
public class GlobalExeception {

    @Resource(name = "messageSource")
    private MessageSource messageSource;

    @ExceptionHandler(value = Exception.class)
    public String errorHandler(Exception e) {
        // 对于校验错误 不打堆栈信息
        if (e instanceof ValidateException) {
            Locale locale = LocaleContextHolder.getLocale();
            String validateMsg = e.getMessage();
            if (StringUtils.isEmpty(validateMsg)) {
                validateMsg = "";
            }
            String message = validateMsg.replace("{", "").replace("}", "").trim();
            /* 没有配置 打印错误日志，不抛出错误 */
            if (!validateMsg.trim().equals(message)) {
                try {
                    message = messageSource.getMessage(message, null, locale);
                } catch (NoSuchMessageException ex) {
                    log.error(ex.getMessage(), e);
                    message = e.getMessage();
                }
            }
            if (!StringUtils.isEmpty(((ValidateException) e).getFieldName())) {
                message = ((ValidateException) e).getFieldName() + message;
            }
            log.info(message);
            return JSON.toJSONString(new Result(HttpStatus.OK.value(), message));
        } else {
            // 全局错误处理
            log.error(e.getMessage(), e);
            return JSON.toJSONString(new Result(0, "未知错误:" + e.getMessage()));
        }
    }

}
