package com.bc.service;

import com.bc.entity.ShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bc.vo.cartVO;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ws
 * @since 2022-02-24
 */
public interface ShoppingCartService extends IService<ShoppingCart> {
    public boolean add(ShoppingCart shoppingCart);
//    public List<cartVO> findVOList(Integer userAddress);
    public List<ShoppingCart> findList(Integer userAddress);
}
