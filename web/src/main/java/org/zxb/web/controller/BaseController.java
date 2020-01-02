package org.zxb.web.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.zxb.web.dto.Result;
import org.zxb.web.exception.ValidateException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @description web抽象基础类
 * @author zjx
 * @date 2020/1/2
 */
public abstract class BaseController {

    Logger log = LoggerFactory.getLogger(this.getClass());;

    @Autowired
    private Validator validator;

    private Set<ConstraintViolation<Object>> constraintViolationSet;

    /**
     *  构建返回实体字符串
     * @return
     */
    protected String build(Object obj){
        Result result = new Result(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase());
        result.setData(obj);
        return JSON.toJSONString(result);
    }

    /**
     * @description 校验注解添加在实体类里面
     * @author zjx
     */
    protected void validate(Object obj, Class<?>... groups) throws ValidateException {
       Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(obj,groups);
       validateConverResult(constraintViolationSet);
    }

    /**
     * @description 校验注解添加参数上面
     * @author zjx
     */
    protected void validate(Object obj, Object[] args, Class<?>... groups) throws ValidateException {
        try {
            // 获取调用方法名字
            StackTraceElement[] ses = Thread.currentThread().getStackTrace();
            String methodName = ses[2].getMethodName();

            Class [] paramTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                paramTypes[i] = arg.getClass();
            }
            Method method = this.getClass().getMethod(methodName,paramTypes);

            // 对方法参数的校验
            ExecutableValidator evalidator = validator.forExecutables();
            Set<ConstraintViolation<Object>> constraintViolationSet = evalidator.validateParameters(obj, method, args, groups);
            validateConverResult(constraintViolationSet);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 错误信息封装成result DTO，没有则返回空
     * @author zjx
     */
    private void validateConverResult(Set<ConstraintViolation<Object>> constraintViolationSet) throws ValidateException {
        if (constraintViolationSet != null && !constraintViolationSet.isEmpty()) {
            // 总是快速失败
            for (ConstraintViolation cv : constraintViolationSet) {
                throw new ValidateException(cv.getMessage());
            }
        }
    }
}
