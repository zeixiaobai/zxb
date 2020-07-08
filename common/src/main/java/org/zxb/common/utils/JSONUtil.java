package org.zxb.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * jackjson util
 *
 * @author zjx
 * @date 2020/7/8 0:50
 */
@Slf4j
public class JSONUtil {

    private static ObjectMapper om;

    /**
     * 初始化 ObjectMapper
     */
    static {
        om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    private JSONUtil() {
    }

    /**
     * 对象转字符串
     *
     * @param obj
     * @return
     * @author zjx
     * @date 2020/7/8 0:56
     */
    public static String toJSONString(Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LoggerUtil.error(log, e);
            return null;
        }
    }

    /**
     * 对象转字符串
     *
     * @param str json字符串
     * @param clz 对象类型
     * @return
     * @author zjx
     * @date 2020/7/8 0:56
     */
    public static <T> T parseObject(String str, Class<T> clz) {
        try {
            return om.readValue(str, clz);
        } catch (Exception e) {
            LoggerUtil.error(log, e);
            return null;
        }
    }

    /**
     * 对象转字符串
     *
     * @param str          json字符串
     * @param valueTypeRef 对象类型
     * @return
     * @author zjx
     * @date 2020/7/8 0:56
     */
    public static <T> T parseObject(String str, TypeReference<T> valueTypeRef) {
        try {
            return om.readValue(str, valueTypeRef);
        } catch (Exception e) {
            LoggerUtil.error(log, e);
            return null;
        }
    }

}
