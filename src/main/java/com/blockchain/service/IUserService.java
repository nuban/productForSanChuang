package com.blockchain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blockchain.pojo.ResBean;
import com.blockchain.pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xbh
 * @since 2022-03-08
 */
public interface IUserService extends IService<User> {


    ResBean login(String username, String password, String code, HttpServletRequest request);

    User getUserByUsername(String username);
    String getUsernameByid(Integer id);

    ResBean findall();

    ResBean deleteByid(Integer id);
}
