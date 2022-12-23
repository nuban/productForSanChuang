package com.blockchain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blockchain.mapper.ShoppingCartMapper;
import com.blockchain.pojo.ShoppingCart;
import com.blockchain.service.IShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xbh
 * @since 2022-03-08
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements IShoppingCartService {

}
