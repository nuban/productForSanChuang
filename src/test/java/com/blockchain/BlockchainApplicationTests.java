package com.blockchain;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blockchain.config.addmark.AddMark;
import com.blockchain.mapper.UserMapper;
import com.blockchain.pojo.CommodityInfo;
import com.blockchain.pojo.User;
import com.blockchain.service.ICommodityInfoService;
import com.blockchain.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.concurrent.locks.LockSupport;

@SpringBootTest
class BlockchainApplicationTests {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ICommodityInfoService iCommodityInfoService;
    @Test
    void contextLoads() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
        System.out.println(userDetails.getUsername()+""+userDetails.getPassword());
    }

    @Test
    void addmark(){
        new AddMark().addWatermark("D:/PHPlearn/image/1.jfif","D:/PHPlearn/image/check/2.jpg","admin","jpg");
    }

    @Test
    void findadd(){
        System.out.println(iCommodityInfoService.findAll());
    }



    @Test
    void theadTest(){
        AddMark addMark = new AddMark();
        addMark.addWatermark("E:/wampserver/www/image/UploadImage/"+ "abc_1658478559659.jpg","E:/wampserver/www/image/checkImage/"+"a.jpg","admin","jpg");






    }

}
