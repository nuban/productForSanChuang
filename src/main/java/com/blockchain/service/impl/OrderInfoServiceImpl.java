package com.blockchain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blockchain.mapper.OrderInfoMapper;
import com.blockchain.pojo.OrderInfo;
import com.blockchain.pojo.ResBean;
import com.blockchain.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xbh
 * @since 2022-03-08
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public ResBean addOrder(OrderInfo orderInfo, HttpServletRequest request) {
        if( 0== orderInfoMapper.insert(orderInfo)){
            return ResBean.error("添加失败");
        }else{
            return ResBean.success("添加成功");
        }
    }
}
