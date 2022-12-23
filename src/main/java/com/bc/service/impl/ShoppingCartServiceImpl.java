package com.bc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bc.entity.CommodityInfo;
import com.bc.entity.ShoppingCart;
import com.bc.mapper.CommodityInfoMapper;
import com.bc.mapper.ShoppingCartMapper;
import com.bc.service.ShoppingCartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bc.utils.ErrorEnum;
import com.bc.utils.ExceptionDeal;
import com.bc.vo.cartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ws
 * @since 2022-02-24
 */
@Service
@Slf4j
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private CommodityInfoMapper commodityInfoMapper;
//    @Transactional   // 用于给数据库添加事务，springboot添加事务加这个就ok了

    @Override
    public boolean add(ShoppingCart shoppingCart) {
        int insert = this.shoppingCartMapper.insert(shoppingCart);
        if (insert != 1){
            log.info("[添加购物车]插入购物车数据失败");
            throw new ExceptionDeal(ErrorEnum.ADD_SHOPPING_FAIL);
        }
        return true;
    }

    @Override
    public List<ShoppingCart> findList(Integer userId) {
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<ShoppingCart> shoppingCartList = this.shoppingCartMapper.selectList(queryWrapper);
        return shoppingCartList;
    }
}
