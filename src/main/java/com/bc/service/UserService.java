package com.bc.service;

import com.bc.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bc.utils.UserDataValidation;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ws
 * @since 2022-02-21
 */
public interface UserService extends IService<User> {

    public User register(UserDataValidation userDataValidation) throws IOException;
    public User login(UserDataValidation userDataValidation);
}
