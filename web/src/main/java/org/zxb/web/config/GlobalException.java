package org.zxb.web.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zxb.common.dto.Result;
import org.zxb.common.exception.ZxbException;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * @author zjx
 * @description 错误拦截切面
 * @date 2020/1/2 0002
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {

    @Resource(name = "messageSource")
    private MessageSource messageSource;

    /**
     * 参数效验异常处理器
     *
     * @param e 数验证异常
     * @return org.zxb.common.dto.Result
     * @author zjx
     * @date 2020/7/7 23:29
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result parameterExceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return new Result(10000, fieldError.getDefaultMessage());
            }
        }
        return new Result(10000, null);
    }

    /**
     * 参数效验异常处理器
     *
     * @param e 数验证异常
     * @return org.zxb.common.dto.Result
     * @author zjx
     * @date 2020/7/7 23:29
     */
    @ExceptionHandler(BindException.class)
    public Result parameterExceptionHandler(BindException e) {
        log.error(e.getMessage(), e);
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return new Result(10000, fieldError.getDefaultMessage());
            }
        }
        return new Result(10000, null);
    }

    /**
     * 参数效验异常处理器
     *
     * @param e 数验证异常
     * @return org.zxb.common.dto.Result
     * @author zjx
     * @date 2020/7/7 23:29
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result parameterExceptionHandler(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        // 获取异常信息
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            String message = constraintViolation.getMessage();
            return new Result(10000, message);
        }
        return new Result(10000, null);
    }

    /**
     * 自定义参数错误异常处理器
     *
     * @param e 自定义异常
     * @return org.zxb.common.dto.Result
     * @author zjx
     * @date 2020/7/7 23:29
     */
    @ExceptionHandler({ZxbException.class})
    public Result paramExceptionHandler(ZxbException e) {
        log.error(e.getMessage(), e);
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        Locale locale = LocaleContextHolder.getLocale();
        String code = ((ZxbException) e).getCode();
        try {
            String message = messageSource.getMessage(code, null, locale);
            return new Result(Integer.valueOf(code), message);
        } catch (NoSuchMessageException ex) {
            log.error(ex.getMessage(), ex);
            return new Result(99999, ex.getMessage());
        }
    }

    /**
     * 其他异常
     *
     * @param e 自定义异常
     * @return org.zxb.common.dto.Result
     * @author zjx
     * @date 2020/7/7 23:29
     */
    @ExceptionHandler({Exception.class})
    public Result otherExceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return new Result(99999, e.getMessage());
    }


}
