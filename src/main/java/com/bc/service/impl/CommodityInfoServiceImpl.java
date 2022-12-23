package com.bc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bc.entity.CommodityInfo;
import com.bc.mapper.CommodityInfoMapper;
import com.bc.service.CommodityInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ws
 * @since 2022-02-23
 */
@Service
public class
CommodityInfoServiceImpl extends ServiceImpl<CommodityInfoMapper, CommodityInfo> implements CommodityInfoService {

    /**
     * 查询产品数据库，并返回所有数据
     * @return
     */

    @Autowired
    private CommodityInfoMapper commodityInfoMapper;

    @Override
//    public List<CommodityInfo> queryData() {
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.select("id","commodity_name","commodity_price","by_address","information","images","create_time","category");
//        List<CommodityInfo> commodityInfos = this.commodityInfoMapper.selectList(queryWrapper);
//        return commodityInfos;
//    }
    public List<CommodityInfo> queryData() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id","commodity_name","commodity_price","by_address","by_name","information","images","category","create_time");
        List<CommodityInfo> commodityInfos = this.commodityInfoMapper.selectList(queryWrapper);
        return commodityInfos;
    }
}
