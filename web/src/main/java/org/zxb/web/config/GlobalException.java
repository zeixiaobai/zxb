package org.zxb.web.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zxb.common.utils.LoggerUtil;
import org.zxb.web.constant.ErrorConstant;
import org.zxb.web.exception.CommonException;
import org.zxb.web.vo.Result;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @author zjx
 * @description web异常全局处理器
 * @date 2020/1/2
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {

    @Resource(name = "messageSource")
    private MessageSource messageSource;

    @Value("${zxb.web.global.param.error:}")
    public String paramError;

    @Value("${zxb.web.global.sys.error:}")
    public String sysError;

    /**
     * 参数校验异常
     *
     * @return {@link Result}
     * @Param e 参数验证异常
     * @author zjx
     * @date 2020/07/08 14:12
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result parameterExceptionHandler(MethodArgumentNotValidException e) {
        LoggerUtil.error(log, e);
        // 获取异常信息
        return getResultByValidParam(e.getBindingResult());
    }

    /**
     * 参数校验异常
     *
     * @param e 数验证异常
     * @return @link Result}
     * @author zjx
     * @date 2020/7/7 23:29
     */
    @ExceptionHandler(BindException.class)
    public Result parameterExceptionHandler(BindException e) {
        LoggerUtil.error(log, e);
        return getResultByValidParam(e.getBindingResult());
    }

    /**
     * 参数校验异常
     *
     * @param e 参数验证异常
     * @return org.zxb.common.dto.Result
     * @author zjx
     * @date 2020/7/7 23:29
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result parameterExceptionHandler(ConstraintViolationException e) {
        LoggerUtil.error(log, e);
        // 获取异常信息
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            Object[] args = {constraintViolation.getMessage()};
            String message = messageSource.getMessage(ErrorConstant.PARAM_ERROR, args, null);
            return buildParam(message);
        }
        return buildParam(null);
    }

    /**
     * 自定义参数错误异常处理器
     *
     * @param e 自定义异常
     * @return org.zxb.common.dto.Result
     * @author zjx
     * @date 2020/7/7 23:29
     */
    @ExceptionHandler({CommonException.class})
    public Result paramExceptionHandler(CommonException e) {
        LoggerUtil.error(log, e);
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        String code = e.getCode();
        try {
            Object[] args = {e.getMessage()};
            String message = messageSource.getMessage(e.getCode(), args, null);
            return Result.buildFail(e.getCode(), message);
        } catch (NoSuchMessageException ex) {
            log.error(ex.getMessage(), ex);
            return Result.buildFail(e.getCode(), ex.getMessage());
        }
    }

    /**
     * 系统异常
     *
     * @param e 自定义异常
     * @return org.zxb.common.dto.Result
     * @author zjx
     * @date 2020/7/7 23:29
     */
    @ExceptionHandler({Exception.class})
    public Result otherExceptionHandler(Exception e) {
        LoggerUtil.error(log, e);
        Object[] args = {e.getMessage()};
        String message = messageSource.getMessage(ErrorConstant.SYS_ERROR, args, null);
        return buildSys(message);
    }


    private Result getResultByValidParam(BindingResult exceptions) {
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                Object[] args = {fieldError.getDefaultMessage()};
                String message = messageSource.getMessage(ErrorConstant.PARAM_ERROR, args, null);
                return buildParam(message);
            }
        }
        return buildParam(null);
    }

    private Result buildParam(String message){
        return Result.buildFail( StringUtils.isEmpty(paramError) ? ErrorConstant.PARAM_ERROR : paramError, message);
    }

    private Result buildSys(String message){
        return Result.buildFail( StringUtils.isEmpty(sysError) ? ErrorConstant.SYS_ERROR : sysError, message);
    }

}
