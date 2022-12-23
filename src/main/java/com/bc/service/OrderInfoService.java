package com.bc.service;

import com.bc.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bc.entity.User;
//import org.web3j.crypto.CipherException;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ws
 * @since 2022-02-27
 */
public interface OrderInfoService extends IService<OrderInfo> {
    public Boolean commitData(User user) throws Exception;
}
