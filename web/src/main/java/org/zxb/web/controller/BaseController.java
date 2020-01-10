package org.zxb.web.controller;

import com.alibaba.fastjson.JSON;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.zxb.web.dto.Result;
import org.zxb.web.exception.ValidateException;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author zjx
 * @description web抽象基础类
 * @date 2020/1/2
 */
public abstract class BaseController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Validator validator;

    private Set<ConstraintViolation<Object>> constraintViolationSet;

    /**
     * 构建返回实体字符串
     *
     * @return
     */
    protected String build(Object obj) {
        Result result = new Result(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
        result.setData(obj);
        return JSON.toJSONString(result);
    }

    /**
     * @description 校验注解添加在实体类里面
     * @author zjx
     */
    protected void validate(Object obj, Class<?>... groups) throws ValidateException {
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(obj, groups);
        validateConverResult(constraintViolationSet,false);
    }

    /**
     * @description 校验注解添加参数上面 不分组
     * @author zjx
     */
    protected void validated(Object obj, Object... args) throws ValidateException {
        // 获取调用方法名字
        StackTraceElement[] ses = Thread.currentThread().getStackTrace();
        String methodName = ses[2].getMethodName();
        Method method = null;
        // 通过方法名字找，不能有重复的方法名字
        Method[] methods = obj.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method_ = methods[i];
            if (methods[i].getName().equals(methodName)) {
                method = method_;
            }
        }
        // 对方法参数的校验
        ExecutableValidator evalidator = validator.forExecutables();
        Set<ConstraintViolation<Object>> constraintViolationSet = evalidator.validateParameters(obj, method, args);
        validateConverResult(constraintViolationSet,true);

    }

    /**
     * @description 错误信息封装成result DTO，没有则返回空
     * @author zjx
     */
    private void validateConverResult(Set<ConstraintViolation<Object>> constraintViolationSet,boolean flag) throws ValidateException {
        if (constraintViolationSet != null && !constraintViolationSet.isEmpty()) {
            String filedName = "";
            // 总是快速失败
            for (ConstraintViolation cv : constraintViolationSet) {
                if (cv instanceof ConstraintViolationImpl) {
                    ConstraintViolationImpl cvi = (ConstraintViolationImpl) cv;
                    Path propertyPath = cvi.getPropertyPath();
                    filedName = propertyPath.toString();
                    if(flag){
                        String [] paths = filedName.split("\\.");
                        filedName = paths[paths.length-1];
                    }
                }
                throw new ValidateException(filedName, cv.getMessage());
            }
        }
    }
}
