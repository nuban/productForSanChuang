package com.bc.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

/**
 * 捕获程序中的异常并进行处理
 * 异常处理器
 */
@RestControllerAdvice  // 获取异常
public class HandlerException {
    @ExceptionHandler(value = ExceptionDeal.class)
    public ModelAndView excp(ExceptionDeal e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        ErrorEnum errorEnum = e.getErrorEnum();
        switch(errorEnum.getCode()){
            case 301:
                modelAndView.setViewName("register");
                modelAndView.addObject("userNameError",errorEnum.getMsg());
                break;
            case 302:
                modelAndView.setViewName("register");
                modelAndView.addObject("passwordError",errorEnum.getMsg());
                break;
            case 303:
                modelAndView.setViewName("register");
                modelAndView.addObject("userExistError",errorEnum.getMsg());
                break;
            case 304:
                modelAndView.setViewName("register");
                modelAndView.addObject("userAddError",errorEnum.getMsg());
                break;
            case 305:
                modelAndView.setViewName("login");
                modelAndView.addObject("userNameLoginError",errorEnum.getMsg());
                break;
            case 306:
                modelAndView.setViewName("login");
                modelAndView.addObject("userPasswordLoginError",errorEnum.getMsg());
                break;
        }
        return modelAndView;
    }
}
