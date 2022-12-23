package com.bc.utils;

/**
 * 程序错误信息异常处理
 */

public class ExceptionDeal extends RuntimeException {
    // 构造器
    private ErrorEnum errorEnum;

    public void setErrorEnum(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }

    public ExceptionDeal(ErrorEnum errorEnum) {
        super(errorEnum.getMsg());
        this.errorEnum = errorEnum;  // 记录错误信息
    }
}
