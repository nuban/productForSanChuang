package com.blockchain.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blockchain.config.addmark.AddMark;
import com.blockchain.config.uploadfile.UploadTodist;
import com.blockchain.mapper.CommodityInfoMapper;
import com.blockchain.pojo.CommodityInfo;
import com.blockchain.pojo.ResBean;
import com.blockchain.pojo.Tempname;
import com.blockchain.service.ICommodityInfoService;
import com.blockchain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xbh
 * @since 2022-03-08
 */
@Service
public class CommodityInfoServiceImpl extends ServiceImpl<CommodityInfoMapper, CommodityInfo> implements ICommodityInfoService {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private UploadTodist uploadTodist;

    @Autowired
    private ICommodityInfoService iCommodityInfoService;
    @Autowired
    private CommodityInfoMapper commodityInfoMapper;

    @Override
    public ResBean save(Tempname tempname, HttpServletRequest request) {

        AddMark addMark = new AddMark();
        System.out.println(tempname);
        CommodityInfo commodityInfo = new CommodityInfo();
        commodityInfo.setByName(iUserService.getUsernameByid(tempname.getUserId()))
                .setCommodityName(tempname.getCommodityName())
                .setCommodityPrice(tempname.getCommodityPrice())
                .setImages(tempname.getCommodityImages())
                .setInformation(tempname.getCommodityInformation())
                .setCreateTime(new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss").format(new Date()));

        String path = commodityInfo.getImages();
        System.out.println(path);
        if(path.equals(null)) {
            return ResBean.success("图片为空");
        }else {
            addMark.addWatermark("D:\\上传图片\\"+path,"D:\\上传图片\\"+path,commodityInfo.getByName(),"jpg");
        }
        if(iCommodityInfoService.save(commodityInfo)){
            return ResBean.success("添加成功");
        }else {
            return ResBean.error("添加失败");
        }
    }

    @Override
    public ResBean add(CommodityInfo commodityInfo, HttpServletRequest request) {


        commodityInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss").format(new Date()));
        if(iCommodityInfoService.save(commodityInfo)){
            return ResBean.success("添加成功");
        }else{
            return ResBean.error("添加失败");
        }
    }

    @Override
    public ResBean deleteByid(Integer id, HttpServletRequest request) {
        if(iCommodityInfoService.removeById(id)){
            return ResBean.success("删除成功");
        }else {
            return ResBean.error("删除失败");
        }

    }

    @Override
    public ResBean findAll() {
        List<CommodityInfo> commodityInfos = commodityInfoMapper.selectList(new QueryWrapper<CommodityInfo>());
        return ResBean.success("查询成功",commodityInfos);
    }

    @Override
    public ResBean saveForm(MultipartFile multipartFile, String commodityName, String commodityInformation, String commodityPrice) {
        AddMark addMark = new AddMark();
        CommodityInfo commodityInfo = new CommodityInfo();
        String upload = uploadTodist.upload(multipartFile);
        if(upload.equals("上传失败") || upload.equals("图片为空")){
            return ResBean.error("上传失败");
        }
        addMark.addWatermark("D:\\上传图片\\"+upload,"D:\\上传图片\\"+upload,"admin","jpg");
        commodityInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss").format(new Date()))
                .setCommodityPrice(commodityPrice)
                .setInformation(commodityInformation)
                .setCommodityName(commodityName)
                .setImages(upload)
                .setByName("admin");
        if (iCommodityInfoService.save(commodityInfo)) {
            return ResBean.success("添加成功");
        }else{
            return ResBean.error("添加失败");
        }
    }
}
