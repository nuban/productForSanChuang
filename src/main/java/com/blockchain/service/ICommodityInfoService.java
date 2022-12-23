package com.blockchain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blockchain.pojo.CommodityInfo;
import com.blockchain.pojo.ResBean;
import com.blockchain.pojo.Tempname;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xbh
 * @since 2022-03-08
 */
public interface ICommodityInfoService extends IService<CommodityInfo> {
    ResBean save(Tempname tempname, HttpServletRequest request);

    ResBean add(CommodityInfo commodityInfo, HttpServletRequest request);

    ResBean deleteByid(Integer id, HttpServletRequest request);

    ResBean findAll();

    ResBean saveForm(MultipartFile multipartFile, String commodityName, String commodityInformation, String commodityPrice);
}
