package com.bc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bc.entity.CommodityInfo;
import com.bc.entity.ShoppingCart;
import com.bc.entity.User;
import com.bc.mapper.CommodityInfoMapper;
import com.bc.mapper.ShoppingCartMapper;
import com.bc.service.CommodityInfoService;
import com.bc.service.ShoppingCartService;
import com.bc.utils.ErrorEnum;
import com.bc.utils.ExceptionDeal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ws
 * @since 2022-02-24
 */
@Controller
@RequestMapping("/shoppingCart")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private CommodityInfoService commodityInfoService;
    @Autowired
    private CommodityInfoMapper commodityInfoMapper;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    /**
     * 添加购物车
     * @param proudctId
     * @param price
     * @return
     */
    @GetMapping("/add/{productId}/{price}")
    public String add(
            @PathVariable("productId") Integer proudctId,
            @PathVariable("price") BigDecimal price,
            HttpSession session
            ){
        if(proudctId == null || price == null){
            log.info("[购物车]关键字为空");
            throw new ExceptionDeal(ErrorEnum.SEARCH_NULL);
        }
//        ModelAndView modelAndView = new ModelAndView();
        // 判断用户是否登录
        User user = (User) session.getAttribute("user");
        if(user == null){
            // 未登录
            log.info("[购物车]未登录状态");
            throw new ExceptionDeal(ErrorEnum.NO_LOGGING);
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        QueryWrapper<CommodityInfo> queryWrappercmd = new QueryWrapper<>();
        queryWrappercmd.eq("id", proudctId);
        CommodityInfo commodityInfo = this.commodityInfoMapper.selectOne(queryWrappercmd);

        shoppingCart.setCommodityId(proudctId);
        shoppingCart.setCmdName(commodityInfo.getCommodityName());
        shoppingCart.setCmdPrice(commodityInfo.getCommodityPrice());
        shoppingCart.setCmdAddress(commodityInfo.getByAddress());
        shoppingCart.setNewAddress(user.getMoneryAdd());
        shoppingCart.setUserId(user.getId());
        shoppingCart.setCmdImages(commodityInfo.getImages());

        // 在写一步，通过购物车中商品的id查询商品数据库对应的数据并赋给shoppongCart，并添加到购物车数据库中
        boolean add = this.shoppingCartService.add(shoppingCart);
        if (!add){
            log.info("[添加购物车]添加购物车失败");
            throw new ExceptionDeal(ErrorEnum.ADD_SHOPPING_FAIL);
        }

        return "redirect:/shoppingCart/get";
    }

    // 查看购物车
    @GetMapping("/get")
    public String get(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        // 判断用户是否登录
        User user = (User) session.getAttribute("user");
        if(user == null){
            // 未登录
            log.info("[购物车]未登录状态");
            throw new ExceptionDeal(ErrorEnum.NO_LOGGING);
        }
//        modelAndView.setViewName("settlement1");
//        modelAndView.addObject("carList",this.shoppingCartService.findList(user.getId()));
//        return modelAndView;
        modelAndView.addObject("carList",this.shoppingCartService.findList(user.getId()));
        return "redirect:/product/myproduct";
    }


    // 购物车中删除商品
    @GetMapping("/deleteCart/{id}")
    public String deleteCart(@PathVariable("id") Integer id,HttpSession session){
        if(id == null){
            log.info("[购物车]删除购物车商品失败");
            throw new ExceptionDeal(ErrorEnum.SEARCH_NULL);
        }
        // 判断用户是否登录
        User user = (User) session.getAttribute("user");
        if(user == null){
            // 未登录
            log.info("[购物车]未登录状态");
            throw new ExceptionDeal(ErrorEnum.NO_LOGGING);
        }
        // 删除商品
        System.out.println("------------");
        System.out.println(id);
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("commodity_id", id);
        int flag = this.shoppingCartMapper.delete(queryWrapper);
//        int flag = this.shoppingCartMapper.deleteById(id);
        if (flag != 1){
            log.info("[删除购物车]数据删除失败");
            throw new ExceptionDeal(ErrorEnum.ADD_SHOPPING_FAIL);
        }
        return "redirect:/shoppingCart/get";
    }


    // 确认结算，交易NFT
    @GetMapping("/confirm")
    public ModelAndView confirmNft(HttpSession session){
        // 判断用户是否登录
        User user = (User) session.getAttribute("user");
        if(user == null){
            // 未登录
            log.info("[购物车]未登录状态");
            throw new ExceptionDeal(ErrorEnum.NO_LOGGING);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement2");
        modelAndView.addObject("carList",this.shoppingCartService.findList(user.getId()));
        return modelAndView;
    }

    /**
     * 详情页面购买到购物车中
     * @param proudctId
     * @param price
     * @param session
     * @return
     */
    @GetMapping("/buy/{productId}/{price}")
    public ModelAndView buy(
            @PathVariable("productId") Integer proudctId,
            @PathVariable("price") BigDecimal price,
            HttpSession session
    ){
        if(proudctId == null || price == null){
            log.info("[购物车]关键字为空");
            throw new ExceptionDeal(ErrorEnum.SEARCH_NULL);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement2");
        // 判断用户是否登录
        User user = (User) session.getAttribute("user");
        if(user == null){
            // 未登录
            log.info("[购物车]未登录状态");
            throw new ExceptionDeal(ErrorEnum.NO_LOGGING);
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        QueryWrapper<CommodityInfo> queryWrappercmd = new QueryWrapper<>();
        queryWrappercmd.eq("id", proudctId);
        CommodityInfo commodityInfo = this.commodityInfoMapper.selectOne(queryWrappercmd);

        shoppingCart.setCommodityId(proudctId);
        shoppingCart.setCmdName(commodityInfo.getCommodityName());
        shoppingCart.setCmdPrice(commodityInfo.getCommodityPrice());
        shoppingCart.setCmdAddress(commodityInfo.getByAddress());
        shoppingCart.setNewAddress(user.getMoneryAdd());
        shoppingCart.setUserId(user.getId());
        shoppingCart.setCmdImages(commodityInfo.getImages());

        // 在写一步，通过购物车中商品的id查询商品数据库对应的数据并赋给shoppongCart，并添加到购物车数据库中
        boolean add = this.shoppingCartService.add(shoppingCart);
        if (!add){
            log.info("[添加购物车]添加购物车失败");
            throw new ExceptionDeal(ErrorEnum.ADD_SHOPPING_FAIL);
        }
        modelAndView.addObject("carList",this.shoppingCartService.findList(user.getId()));
        return modelAndView;
    }
}

