package com.bc.mapper;

import com.bc.entity.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ws
 * @since 2022-02-24
 */
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

    // 自定义数据库操作
//    public int updataId(Integer id,String name);
    // 调用在serverimpl中
    // this.ShoppingCartMapper.updataId(id,name);
    List<ShoppingCart> selectListss();
}
