package com.bc.utils;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用于对传入的前端数据进行校验判断
 */

@Data
public class UserDataValidation {
    @NotEmpty(message = "登录名不能为空")
    private String userName;
    @NotEmpty(message = "密码不能为空")
    private String password;
//    @NotEmpty(message = "钱包地址不能为空")
//    private String moneryAdd;

}
