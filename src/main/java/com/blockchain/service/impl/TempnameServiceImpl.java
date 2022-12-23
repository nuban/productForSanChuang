package com.blockchain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blockchain.mapper.TempnameMapper;
import com.blockchain.pojo.ResBean;
import com.blockchain.pojo.Tempname;
import com.blockchain.service.ITempnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class TempnameServiceImpl extends ServiceImpl<TempnameMapper, Tempname> implements ITempnameService {

    @Autowired
    private TempnameMapper tempnameMapper;


    @Override
    public int deleteByid(Integer id) {

        return tempnameMapper.deleteById(id);
    }

    @Override
    public ResBean findall() {

        List<Tempname> tempnames = tempnameMapper.selectList(new QueryWrapper<Tempname>());
        if (!tempnames.isEmpty()){
            return ResBean.success("查询成功",tempnames);
        }else{
            return ResBean.success("无数据",null);
        }
    }

    @Override
    public ResBean add(Tempname tempname) {
        if(tempnameMapper.insert(tempname)!=0){
            return ResBean.success("添加成功");
        }else {
            return ResBean.error("添加失败");
        }


    }
}
