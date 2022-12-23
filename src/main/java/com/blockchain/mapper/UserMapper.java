package com.blockchain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blockchain.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xbh
 * @since 2022-03-08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
