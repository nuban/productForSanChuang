package com.blockchain.controller;


import com.blockchain.pojo.LoginUser;
import com.blockchain.pojo.ResBean;
import com.blockchain.pojo.User;
import com.blockchain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class LoginController {


    @Autowired
    private IUserService iUserService;

    @PostMapping("/login")
    public ResBean login(@RequestBody LoginUser loginUser, HttpServletRequest request){

        //System.out.println(loginUser);
        return iUserService.login(loginUser.getUsername(),loginUser.getPassword(),loginUser.getCode(), request);
    }

    @GetMapping("/logout")
    public ResBean logout(){
        return ResBean.success("注销成功");
    }

    @GetMapping("user/info")
    public ResBean getUserInfo(Principal principal){
        if(null == principal){
            return null;
        }
        String username = principal.getName();
        User user = iUserService.getUserByUsername(username);
        user.setPassword(null);
        return ResBean.success("成功",user);
    }



}
