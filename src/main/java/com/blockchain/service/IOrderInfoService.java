package com.blockchain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blockchain.pojo.OrderInfo;
import com.blockchain.pojo.ResBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xbh
 * @since 2022-03-08
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    ResBean addOrder(OrderInfo orderInfo, HttpServletRequest request);
}
