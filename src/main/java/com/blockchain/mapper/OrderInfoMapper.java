package com.blockchain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blockchain.pojo.OrderInfo;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xbh
 * @since 2022-03-08
 */
@MapperScan
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

}
