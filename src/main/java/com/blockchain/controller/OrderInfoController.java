package com.blockchain.controller;


import com.blockchain.pojo.OrderInfo;
import com.blockchain.pojo.ResBean;
import com.blockchain.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xbh
 * @since 2022-03-08
 */
@RestController
@RequestMapping("/order-info")
public class OrderInfoController {

    @Autowired
    private IOrderInfoService iOrderInfoService;

    @PostMapping("/add")
    public ResBean addOrder(@RequestBody OrderInfo orderInfo, HttpServletRequest request){
        return iOrderInfoService.addOrder(orderInfo,request);
    }



}
