package com.bc.controller;

/**
 * 该页面主要功能是实现页面跳转
 */

import com.bc.entity.User;
import com.bc.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class RedirectController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/{url}")   // 资源访问，访问html页面名字自动跳转到此函数
    public ModelAndView redirect(@PathVariable("url") String url, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(url);
        // 判断用户是否登录
        User user = (User) session.getAttribute("user");
        if(user == null){
            // 未登录
            modelAndView.addObject("carList",new ArrayList<>());
        }else{
            // 登录用户
            modelAndView.addObject("carList",this.shoppingCartService.findList(user.getId()));
        }
        return modelAndView;
    }

    @GetMapping("/")    // 默认页面，
    public String main(){return "redirect:/product/main" + "";}

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon(){
    }

}
