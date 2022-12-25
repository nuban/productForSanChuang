package com.bc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bc.entity.CommodityInfo;
import com.bc.entity.OrderInfo;
import com.bc.entity.ShoppingCart;
import com.bc.entity.User;
import com.bc.mapper.CommodityInfoMapper;
import com.bc.mapper.OrderInfoMapper;
import com.bc.mapper.UserMapper;
import com.bc.service.CommodityInfoService;
import com.bc.service.ShoppingCartService;
import com.bc.utils.ErrorEnum;
import com.bc.utils.ExceptionDeal;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ws
 * @since 2022-02-23
 */
@Controller
@RequestMapping("/product")
@Slf4j
public class CommodityInfoController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommodityInfoService commodityInfoService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private CommodityInfoMapper commodityInfoMapper;


    @GetMapping("/main")
    public ModelAndView main(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");

        // 判断是否为登录用户
        User user = (User) session.getAttribute("user");
        if(user == null){
            // 未登录
            modelAndView.addObject("carList",new ArrayList<>());
        }else{
            // 登录用户
            QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("new_address",user.getMoneryAdd());
            modelAndView.addObject("carList",this.shoppingCartService.list(queryWrapper));
        }
        // 返回产品数据库中的所有数据
        QueryWrapper<CommodityInfo> commodityData = new QueryWrapper<>();
        commodityData = commodityData.eq("state","0");
        List<CommodityInfo> queryWrapper = this.commodityInfoMapper.selectList(commodityData);
        modelAndView.addObject("product",queryWrapper);
        System.out.println(queryWrapper);
        QueryWrapper<CommodityInfo> auction = new QueryWrapper<>();
        auction = auction.eq("state","1");
        List<CommodityInfo> auction1 = this.commodityInfoMapper.selectList(auction);
        modelAndView.addObject("auction",auction1);
        System.out.println(auction);
        return modelAndView ;
    }

    @GetMapping("/productList")
    public ModelAndView productLists(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");


        // 返回产品数据库中的所有数据
        List<CommodityInfo> commodityData = this.commodityInfoService.queryData();
        System.out.println(commodityData);
        modelAndView.addObject("product",commodityData);
        return modelAndView ;
    }

//    @GetMapping("/myproduct")
//    public ModelAndView myproduct(HttpSession session){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("myproduct");
//
//        // 判断是否为登录用户
//        User user = (User) session.getAttribute("user");
//        if(user == null){
//            // 未登录
//            modelAndView.addObject("carList",new ArrayList<>());
//        }else{
//            // 登录用户
//            QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("new_address",user.getMoneryAdd());
//            modelAndView.addObject("carList",this.shoppingCartService.list(queryWrapper));
//        }
//        // 返回产品数据库中的所有数据
//        List<CommodityInfo> commodityData = this.commodityInfoService.queryData();
//        System.out.println(commodityData);
//        modelAndView.addObject("product",commodityData);
//        return modelAndView ;
//    }

    // 搜索功能
    @PostMapping("/search")
    public ModelAndView search(String keyWord,HttpSession session){
        if(keyWord == null){
            log.info("[搜索功能]关键字搜索为空");
            throw new ExceptionDeal(ErrorEnum.SEARCH_NULL);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        // 判断用户是否登录
        User user = (User) session.getAttribute("user");
        if(user == null){
            // 未登录
            modelAndView.addObject("carList",new ArrayList<>());
        }else{
            // 登录用户

        }

        // 进行模糊查询
        QueryWrapper<CommodityInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("commodity_name",keyWord);
        List<CommodityInfo> list = this.commodityInfoService.list(queryWrapper);
        modelAndView.addObject("queryLike",list);

        return modelAndView;
    }

    // 跳转商品详情页面
//    @GetMapping("/detail/{id}")
//    public ModelAndView detail(@PathVariable("id") Integer id,HttpSession session){
//        System.out.println("-------------56456465456456sdf-------------------");
//        if(id == null){
//            log.info("[商品详情]关键字参数为空");
//            throw new ExceptionDeal(ErrorEnum.SEARCH_NULL);
//        }
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("productDetail");
//        // 判断用户是否登录
//        User user = (User) session.getAttribute("user");
//        if(user == null){
//            // 未登录
//            modelAndView.addObject("carList",new ArrayList<>());
//        }else{
//            // 登录用户
//            modelAndView.addObject("carList",this.shoppingCartService.findList(user.getId()));
//        }
//        // 商品详情功能实现
//        modelAndView.addObject("commodityDetail",this.commodityInfoService.getById(id));
//        return modelAndView;
//    }

    @GetMapping("/myproduct")
    public ModelAndView myproduct(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("myproduct");

        // 判断是否为登录用户
        User user = (User) session.getAttribute("user");
        if(user == null){
            // 未登录
            modelAndView.addObject("carList",new ArrayList<>());
        }else{
            // 登录用户
            QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("new_address",user.getMoneryAdd());
            modelAndView.addObject("carList",this.shoppingCartService.list(queryWrapper));
//            modelAndView.addObject("carList",this.shoppingCartService.findList(user.getId()));

        }
        // 返回产品数据库中的所有数据
//        List<CommodityInfo> commodityData = this.commodityInfoService.queryData();
//        System.out.println(commodityData);
//        modelAndView.addObject("product",commodityData);
        // 返回自己上传的作品
        QueryWrapper<CommodityInfo> queryWrapper_cmd = new QueryWrapper<>();
        queryWrapper_cmd.eq("by_name",user.getUserName());
        List<CommodityInfo> commodityInfos = this.commodityInfoMapper.selectList(queryWrapper_cmd);
        modelAndView.addObject("product",commodityInfos);

        // 返回数据库中所有订单信息
        QueryWrapper<OrderInfo> orderinfoData = new QueryWrapper<>();
        orderinfoData.eq("buyer_id",user.getId());
        modelAndView.addObject("orderList",this.orderInfoMapper.selectList(orderinfoData));
        System.out.println(orderinfoData);
        QueryWrapper<CommodityInfo> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("auctionid",user.getId());
        List<CommodityInfo> auction = this.commodityInfoMapper.selectList(queryWrapper1);
        modelAndView.addObject("auction",auction);
        return modelAndView ;
    }

    @GetMapping("/category/{category}")
    public ModelAndView findcategory(@PathVariable("category")Integer category,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        // 返回产品数据库中的所有数据
        QueryWrapper<CommodityInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category",category);
        List<CommodityInfo> List = this.commodityInfoMapper.selectList(queryWrapper);
        modelAndView.addObject("product",List);
        System.out.println(List);
        return modelAndView ;
    }

    /**
     * 跳转到详情页面
     * @param id
     * @param session
     * @return
     */
    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Integer id,HttpSession session){
        if(id == null){
            log.info("[商品详情]关键字参数为空");
            throw new ExceptionDeal(ErrorEnum.SEARCH_NULL);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productDetail");
        // 判断用户是否登录
        User user = (User) session.getAttribute("user");
        if(user == null){
            // 未登录
            modelAndView.addObject("carList",new ArrayList<>());
        }else{
            // 登录用户
            modelAndView.addObject("carList",this.shoppingCartService.findList(user.getId()));
        }
        // 商品详情功能实现
        CommodityInfo commodityInfo = this.commodityInfoService.getById(id);
        modelAndView.addObject("commodityDetail",commodityInfo);
//        QueryWrapper queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(user,commodityInfoService.getById(id));
        User author = userMapper.selectOne(new QueryWrapper<User>().eq("user_name",commodityInfo.getByName()));
        modelAndView.addObject("author",author);
        System.out.println(author);

        return modelAndView;
    }

    //商品竞价
    @GetMapping("/auction/{id}")
    public ModelAndView auction(@PathVariable("id") Integer id,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auction");
        User user = (User) session.getAttribute("user");
        if(user == null){
            // 未登录
            log.info("[购物车]未登录状态");
            throw new ExceptionDeal(ErrorEnum.NO_LOGGING);

        }
        // 商品详情功能实现

        modelAndView.addObject("commodityDetail",this.commodityInfoService.getById(id));
        CommodityInfo commodityInfo = this.commodityInfoService.getById(id);
//        QueryWrapper queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(user,commodityInfoService.getById(id));
        System.out.println(user);
        //作者查询
        User author = userMapper.selectOne(new QueryWrapper<User>().eq("monery_add",commodityInfo.getByAddress()));
        modelAndView.addObject("author",author);
        return modelAndView;
    }
    //加价功能
    @PostMapping("/addprice/{id}")
    public ModelAndView addPrice(@PathVariable("id")Integer id, @Valid BigDecimal auctionprice, HttpSession session){System.out.println(auctionprice);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auction");
        User user = (User) session.getAttribute("user");
        CommodityInfo commodityInfo = this.commodityInfoService.getById(id);
        commodityInfo.setAuctionid(user.getId());
        commodityInfo.setAuction(commodityInfo.getAuction().add(auctionprice));

        this.commodityInfoService.updateById(commodityInfo);
        modelAndView.addObject("auction",commodityInfo);
        // 商品详情功能实现

        modelAndView.addObject("commodityDetail",this.commodityInfoService.getById(id));
        CommodityInfo commodityInfo2 = this.commodityInfoService.getById(id);
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(user,commodityInfoService.getById(id));
        System.out.println(user);
        //作者查询
        User author = userMapper.selectOne(new QueryWrapper<User>().eq("monery_add",commodityInfo.getByAddress()));
        modelAndView.addObject("author",author);
        return modelAndView;
    }
    //竞拍功能
    @GetMapping("/toauction/{id}")
    public String toAuction(@PathVariable("id")Integer id,HttpSession session){
        User user = (User) session.getAttribute("user");
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("id",id);
        CommodityInfo commodityInfo = this.commodityInfoMapper.selectOne(queryWrapper);
        System.out.println(commodityInfo);
        BigDecimal num = new BigDecimal(2);
        commodityInfo.setAuction(commodityInfo.getCommodityPrice().divide(num));
        System.out.println(commodityInfo);
        commodityInfo.setState("1");
        System.out.println(commodityInfo);
        this.commodityInfoMapper.update(commodityInfo,queryWrapper);
        return "redirect:/product/myproduct";
    }




}

