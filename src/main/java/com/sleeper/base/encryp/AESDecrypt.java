package com.sleeper.base.encryp;

import com.sleeper.base.annotate.SensitiveField;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author sleeper
 * @date 2021-11-17
 */
public class AESDecrypt {


    /**
     * 解密
     *
     * @param result resultType的实例
     * @return T
     * @throws IllegalAccessException 字段不可访问异常
     */
    public static  <T> T decrypt(T result) throws IllegalAccessException {
        //取出resultType的类
        Class<?> resultClass = result.getClass();
        Field[] declaredFields = resultClass.getDeclaredFields();
        for (Field field : declaredFields) {
            SensitiveField sensitiveField = field.getAnnotation(SensitiveField.class);
            if (!Objects.isNull(sensitiveField)) {
                field.setAccessible(true);
                Object object = field.get(result);
                if (object instanceof String) {
                    String value = (String) object;
                    field.set(result, AESUtil.decrypt(value));
                }
            }
        }
        return result;
    }

}

