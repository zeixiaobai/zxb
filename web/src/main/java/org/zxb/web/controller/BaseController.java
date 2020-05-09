package org.zxb.web.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zxb.common.dto.Result;

/**
 * @author zjx
 * @description web抽象基础类
 * @date 2020/1/2
 */
public abstract class BaseController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 构建返回实体字符串
     *
     * @return
     */
    protected String build(Object obj) {
        Result result = Result.buildSuccess("OK");
        result.setData(obj);
        return JSON.toJSONString(result);
    }

}
