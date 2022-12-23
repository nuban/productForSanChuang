package com.bc.service;

import com.bc.entity.CommodityInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ws
 * @since 2022-02-23
 */
public interface CommodityInfoService extends IService<CommodityInfo> {
    public List<CommodityInfo> queryData();
}
