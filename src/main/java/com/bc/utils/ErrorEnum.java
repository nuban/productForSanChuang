package com.bc.utils;

/**
 * 错误信息提示，枚举类型
 */
public enum ErrorEnum {
    USER_INFO_NULL(301,"信息内容不能为空"),
    PASSWORD_ERROR(302,"密码等级较低"),
    USER_EXIST(303,"用户名已存在"),
    USER_LOGIN(304,"添加用户失败"),
    USER_NOEXIST(305,"用户名已存在"),
    PASSWORD_LOGIN_ERROR(306,"登录密码错误"),
    SEARCH_NULL(307,"关键字为空"),
    NO_LOGGING(308,"无用户登录"),
    ADD_SHOPPING_FAIL(309,"添加购物车失败");

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ErrorEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;
}
