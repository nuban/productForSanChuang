package com.bc.mapper;

import com.bc.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ws
 * @since 2022-02-21
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
    public int updateBalance(Integer id, BigInteger price);
}
