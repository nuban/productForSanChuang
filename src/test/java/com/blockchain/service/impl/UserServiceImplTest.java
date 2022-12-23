package com.blockchain.service.impl;

import com.blockchain.mapper.OrderInfoMapper;
import com.blockchain.pojo.LoginUser;
import com.blockchain.pojo.OrderInfo;
import com.blockchain.service.IOrderInfoService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Autowired
    private IOrderInfoService iOrderInfoService;
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Test
    void teseAdd (){

        OrderInfo o = new OrderInfo();
        o.setBuyerAdd("xx");
        orderInfoMapper.insert(o);



    }


    @Test
    void testPass (){
        System.out.println(new BCryptPasswordEncoder().encode("123456"));

    }


}