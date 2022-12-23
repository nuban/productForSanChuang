package com.bc.controller;


import com.bc.entity.User;
import com.bc.service.OrderInfoService;
import com.bc.service.ShoppingCartService;
import com.bc.utils.ErrorEnum;
import com.bc.utils.ExceptionDeal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ws
 * @since 2022-02-27
 */
@Controller
@RequestMapping("/order")
@Slf4j
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    public static int NUM = 0;
    /**
     * 提交订单处理
     * @return
     */
    @GetMapping("/commit")
    public ModelAndView commit(HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        if(user == null){
            // 未登录
            log.info("[购物车]未登录状态");
            throw new ExceptionDeal(ErrorEnum.NO_LOGGING);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement3");
        modelAndView.addObject("carList",this.shoppingCartService.findList(user.getId()));
        boolean flag = this.orderInfoService.commitData(user);
        if (!flag){
            log.info("[提交订单]提交订单失败");
        }


        return modelAndView;
    }

}

