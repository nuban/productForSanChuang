package com.blockchain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blockchain.pojo.ResBean;
import com.blockchain.pojo.Tempname;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xbh
 * @since 2022-03-08
 */
public interface ITempnameService extends IService<Tempname> {

    int deleteByid(Integer id) ;

    ResBean findall();

    ResBean add(Tempname tempname);
}
