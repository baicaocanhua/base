package com.sleeper.base.encryp;

import com.sleeper.base.annotate.SensitiveField;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author sleeper
 * @date 2021-11-17
 */
public class AESEncrypt  {

    /**
     * 加密
     *
     * @param declaredFields paramsObject所声明的字段
     * @param paramsObject   mapper中paramsType的实例
     * @return T
     * @throws IllegalAccessException 字段不可访问异常
     */
    public static  <T> T encrypt(Field[] declaredFields, T paramsObject) throws IllegalAccessException {
        for (Field field : declaredFields) {
            SensitiveField sensitiveField = field.getAnnotation(SensitiveField.class);
            if (!Objects.isNull(sensitiveField)) {
                field.setAccessible(true);
                Object object = field.get(paramsObject);
                if (object instanceof String) {
                    String value = (String) object;
                    field.set(paramsObject, AESUtil.encrypt(value));
                }
            }
        }
        return paramsObject;
    }

}

