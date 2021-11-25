package com.sleeper.base.enums;

import lombok.Getter;

/**
 * @author sleeper
 * @description 响应码枚举
 */
@Getter
public enum ResultCode {

    SUCCESS(0, "操作成功"),

    UNAUTHORIZED(401, "没有登录"),

    FORBIDDEN(402, "没有相关权限"),

    VALIDATE_FAILED(1000, "参数校验失败"),

    FAILED(1001, "接口异常"),

    ERROR(9999, "未知错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
