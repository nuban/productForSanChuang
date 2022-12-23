package com.bc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bc.entity.User;
import com.bc.mapper.UserMapper;
import com.bc.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bc.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.web3j.protocol.admin.Admin;
//import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ws
 * @since 2022-02-21
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User register(UserDataValidation userDataValidation) throws IOException {
        // 用户名是否可以，是否冲突
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userDataValidation.getUserName());
        User userEx = this.userMapper.selectOne(queryWrapper);
        if (userEx != null){
            log.info("此用户名已存在");
            throw new ExceptionDeal(ErrorEnum.USER_EXIST);
        }

        // 格式校验,验证密码是否为全数字
        if(!VerificationData.checkQQ(userDataValidation.getPassword())){
            log.info("用户注册中用户的密码等级较低");
            throw new ExceptionDeal(ErrorEnum.PASSWORD_ERROR);
        }

        // 存储数据
        User user = new User();
        BeanUtils.copyProperties(userDataValidation,user); // 将前一个对象的数据赋值给后一个对象，前提是对象内数据属性要一致

        // 添加用户初始账户余额
        BigInteger price = new BigInteger("0");
        user.setBalancePrice(price);

        /**
         * 创建用户的钱包地址
         */
        // 添加钱包地址
//        user.setMoneryAdd("chengdu");
//        Admin web3j = Admin.build(new HttpService("http://192.168.1.102:8545"));
//        String name = web3j.personalNewAccount(user.getPassword()).send().getAccountId();
        user.setMoneryAdd("MoneyAddress");

        // 加密用户密码
        // user.setPassword((MD5.getSaltMD5(user.getPassword())));

        // 添加时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String t = df.format(new Date());
        user.setCreateTime(t);

        int insertData = this.userMapper.insert(user);
        if (insertData != 1){
            log.info("用户数据插入失败");
            throw new ExceptionDeal(ErrorEnum.USER_LOGIN);
        }
        return user;
    }

    /**
     * 用户登录
     * @param userDataValidation
     * @return
     */
    @Override
    public User login(UserDataValidation userDataValidation) {
        // 判断用户名是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userDataValidation.getUserName());
        User user = this.userMapper.selectOne(queryWrapper);
        if (user == null){
            log.info("[用户登录]--用户名不存在");
            throw new ExceptionDeal(ErrorEnum.USER_NOEXIST);
        }

        // 判读密码是否正确
        // boolean flag = MD5.getSaltverifyMD5(userDataValidation.getPassword(),user.getPassword();
        boolean flag = false;
        if (userDataValidation.getPassword().equals(user.getPassword()) ){
             flag = true;
        }
        if(!flag){
            log.info("[用户登录]--登录密码错误");
            throw new ExceptionDeal(ErrorEnum.PASSWORD_LOGIN_ERROR);
        }

        return user;
    }
}
