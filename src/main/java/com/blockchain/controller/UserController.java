package com.blockchain.controller;

import com.blockchain.pojo.ResBean;
import com.blockchain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xbh
 * @since 2022-03-08
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/findall")
    public ResBean findAll() {
        return iUserService.findall();
    }
    @DeleteMapping("/delete/{id}")
    public ResBean deleteByid(@PathVariable Integer id) {
        return iUserService.deleteByid(id);

    }

}
