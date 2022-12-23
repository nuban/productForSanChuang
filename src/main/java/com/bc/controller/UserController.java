package com.bc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bc.entity.CommodityInfo;
import com.bc.entity.OrderInfo;
import com.bc.entity.User;
import com.bc.mapper.CommodityInfoMapper;
import com.bc.mapper.OrderInfoMapper;
import com.bc.mapper.UserMapper;
import com.bc.service.CommodityInfoService;
import com.bc.service.ShoppingCartService;
import com.bc.service.UserService;
import com.bc.utils.ErrorEnum;
import com.bc.utils.ExceptionDeal;
import com.bc.utils.UserDataValidation;
import com.bc.utils.VerificationData;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
//import org.web3j.protocol.admin.Admin;
//import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
//import org.web3j.protocol.core.methods.request.Transaction;
//import org.web3j.protocol.http.HttpService;

import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ws
 * @since 2022-02-21
 */
@Controller
@RequestMapping("/user")
@Slf4j // 用于日志
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommodityInfoService commodityInfoService;
    @Autowired
    private CommodityInfoMapper commodityInfoMapper;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public String login(@Valid UserDataValidation userDataValidation, BindingResult bindingResult, HttpSession session){

        if(bindingResult.hasErrors()) {
            log.info("[用户登录]--用户的信息不能为空"); // 日志打印
            System.out.println(this.userService.login(userDataValidation));
            throw new ExceptionDeal(ErrorEnum.USER_INFO_NULL);  // 抛出异常
        }
        System.out.println();
        User userlogin = this.userService.login(userDataValidation);
        session.setAttribute("user",userlogin);
        return "redirect:/product/main";
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
//    @ResponseBody
    public String register(@Valid UserDataValidation userDataValidation, BindingResult bindingResult) throws IOException {
        // 进行数据验证的方法，@Valid的作用是将前端数据封装传入UserDataValidation进行检查
        // BindingResult 的作用是获取汇总错误信息

        // 非空校验
        if(bindingResult.hasErrors()){
            log.info("用户注册中用户的信息不能为空"); // 日志打印
            throw new ExceptionDeal(ErrorEnum.USER_INFO_NULL);  // 抛出异常
        }
        User AddUser = this.userService.register(userDataValidation);
        if(AddUser == null){
            log.info("用户数据插入失败");
            throw new ExceptionDeal(ErrorEnum.USER_LOGIN);
        }

        return "redirect:/login";  // 重定向，不加redirect是转发，看url地址

    }


    /**
     * 当前用户管理页面的用户订单列表
     */
    @GetMapping("/orderList")
    public ModelAndView orderList(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null){
            log.info("[用户管理页面]用户未登录");
            throw new ExceptionDeal(ErrorEnum.USER_EXIST);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderList");
        modelAndView.addObject("orderList");
        modelAndView.addObject("carList",this.shoppingCartService.findList(user.getId()));
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buyer_id", user.getId());
        List<OrderInfo> orderInfos = this.orderInfoMapper.selectList(queryWrapper);
        modelAndView.addObject("ordershop",orderInfos);
        return modelAndView;
    }

    /**
     * 用户充值功能
     */
    @GetMapping("/recharge/{price}")
    public ModelAndView recharge1(HttpSession session, @PathVariable("price") BigInteger price) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null){
            log.info("[用户管理页面]用户未登录");
            throw new ExceptionDeal(ErrorEnum.USER_EXIST);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userInfo");
        modelAndView.addObject("userInfo");
        modelAndView.addObject("carList",this.shoppingCartService.findList(user.getId()));
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("id",user.getId());
        List<User> dis = this.userMapper.selectList(queryWrapper);

        /**
         * 区块链充值
         */
//        Admin web3j = Admin.build(new HttpService("http://192.168.1.102:8545"));
//        List<String> acc = web3j.ethAccounts().send().getAccounts();    // 获取账户地址，主要目的获取管理员转账账户
//        String from = acc.get(0);
//        String to = dis.get(0).getMoneryAdd();      // 用户得到转账金额的账户
////        String to = acc.get(1);
//        String remarks = "55736572207472616e73666572";  // user transfer
//        BigInteger nonce = null;
//        BigInteger gasPrice = null;
//        BigInteger gasLimit = null;
//        BigInteger prices = new BigInteger("1000000000000000000");
//        BigInteger transferAmount = price.multiply(prices);
//        // 解锁账户进行交易
//        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from,"123456").send();
//        if (personalUnlockAccount.accountUnlocked()){
//            Transaction transaction = new Transaction(from,nonce,gasPrice,gasLimit,to,transferAmount,remarks);
//            // 返回交易hash
//            String transactionHash = web3j.ethSendTransaction(transaction).send().getTransactionHash();
//            System.out.println(transactionHash);
//        }else{
//            log.info("[充值]解锁账户失败");
//        }


        int a = this.userMapper.updateBalance(user.getId(),price.add(dis.get(0).getBalancePrice()));
        if (a == 1){

        }else {
            System.out.println("修改失败");
        }

        // 刷新页面
        QueryWrapper<User> queryWrapper_new = new QueryWrapper<User>();
        queryWrapper_new.eq("id",user.getId());
        List<User> recharge = this.userMapper.selectList(queryWrapper_new);
        modelAndView.addObject("recharge",recharge);
        return modelAndView;
    }

    @PostMapping("/recharge")
    public ModelAndView recharge(HttpSession session, @Valid BigInteger price ) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            log.info("[用户管理页面]用户未登录");
            throw new ExceptionDeal(ErrorEnum.USER_EXIST);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("myproduct");
        modelAndView.addObject("userInfo");
//        modelAndView.addObject("carList", this.shoppingCartService.findList(user.getId()));
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();

        queryWrapper.eq("id",user.getId());
        List<User> dis = this.userMapper.selectList(queryWrapper);

        /**
         * 区块链充值
         */
//        Admin web3j = Admin.build(new HttpService("http://192.168.1.102:8545"));
//        List<String> acc = web3j.ethAccounts().send().getAccounts();    // 获取账户地址，主要目的获取管理员转账账户
//        String from = acc.get(0);
//        String to = dis.get(0).getMoneryAdd();      // 用户得到转账金额的账户
////        String to = acc.get(1);
//        String remarks = "55736572207472616e73666572";  // user transfer
//        BigInteger nonce = null;
//        BigInteger gasPrice = null;
//        BigInteger gasLimit = null;
//        BigInteger prices = new BigInteger("1000000000000000000");
//        BigInteger transferAmount = price.multiply(prices);
//        // 解锁账户进行交易
//        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from,"123456").send();
//        if (personalUnlockAccount.accountUnlocked()){
//            Transaction transaction = new Transaction(from,nonce,gasPrice,gasLimit,to,transferAmount,remarks);
//            // 返回交易hash
//            String transactionHash = web3j.ethSendTransaction(transaction).send().getTransactionHash();
//            System.out.println(transactionHash);
//        }else{
//            log.info("[充值]解锁账户失败");
//        }

//        int a = this.userMapper.updateBalance(user.getId(),price.add(dis.get(0).getBalancePrice()));
//        if(a == 1){
//            // 刷新页面
//        }else {
//            System.out.println("修改失败");
//        }

        // 刷新页面
        QueryWrapper<User> queryWrapper_new = new QueryWrapper<User>();
        queryWrapper_new.eq("id",user.getId());
        List<User> recharge = this.userMapper.selectList(queryWrapper_new);
        ((User) session.getAttribute("user")).setBalancePrice(recharge.get(0).getBalancePrice());

//        QueryWrapper<CommodityInfo> queryWrapper_a = new QueryWrapper<CommodityInfo>();
//        queryWrapper_a.eq("by_name",user.getUserName());
        List<CommodityInfo> commodityData = this.commodityInfoService.queryData();
        System.out.println(commodityData);
        modelAndView.addObject("product",commodityData);
        modelAndView.addObject("carList", this.shoppingCartService.findList(user.getId()));


//        modelAndView.addObject("recharge",recharge);


//        QueryWrapper<User> id = queryWrapper.eq("id", user.getId());
//
//        BigInteger sum = new BigInteger(String.valueOf(user.getBalancePrice().intValue() +price.intValue()));
//        user.setBalancePrice(sum);
//        this.userMapper.updateById(user);
////        List<User> dis = this.userMapper.selectList(queryWrapper);
//        System.out.println("修改的价格" + sum);
        return modelAndView;
    }


    /**
     * 上传头像
     * @param headimage
     * @param session
     * @return
     * @throws IOException
     */
    /**
     * 实例化ipfs节点
     */
//    private static IPFS ipfs = new IPFS("/ip4/192.168.1.102/tcp/5001");
    @RequestMapping("/updateheadimage")
    @ResponseBody
    public ModelAndView updateheadimage(@RequestParam("headimage") MultipartFile headimage, HttpSession session)throws IOException{
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(headimage.getOriginalFilename());
        User user = (User)session.getAttribute("user");
        if (user == null){
            log.info("用户未登录");
            throw new ExceptionDeal(ErrorEnum.USER_EXIST);
        }
        if (headimage.isEmpty()){
            System.out.println("[上传作品]文件为空");
        }
        String oldFileName = headimage.getOriginalFilename();
        String newFileName = user.getUserName() + "_" + System.currentTimeMillis() + "." +oldFileName.substring(oldFileName.lastIndexOf(".")+1);
        System.out.println(System.currentTimeMillis());
        System.out.println(newFileName);
        String filePath = "E:\\wampserver\\www\\image\\headImage\\";
//        String filePath = "D:\\PHPlearn\\";
        File dest = new File(filePath + newFileName);
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            headimage.transferTo(dest);
        }catch(Exception e){
            e.printStackTrace();
        }

        user.setHeadImage(newFileName);
//        NamedStreamable.FileWrapper saveFile = new NamedStreamable.FileWrapper(new File("D:\\java\\bcnft\\src\\main\\resources\\static\\images\\userhead\\"+newFileName));
//        MerkleNode result = ipfs.add(saveFile).get(0);
//        user.setHeadImage(result.hash.toString());

//        user.setHeadImage(newFileName);
        this.userMapper.updateById(user);
        //添加收藏和我的NFT
        modelAndView.addObject("carList",this.shoppingCartService.findList(user.getId()));
        List<CommodityInfo> commodityData = this.commodityInfoService.queryData();
        modelAndView.addObject("product",commodityData);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",user.getId());
        List<User> userhead = this.userMapper.selectList(queryWrapper);
        ((User) session.getAttribute("user")).setHeadImage(userhead.get(0).getHeadImage());

        // 返回数据库中所有订单信息
        QueryWrapper<OrderInfo> orderinfoData = new QueryWrapper<>();
        orderinfoData.eq("buyer_id",user.getId());
        modelAndView.addObject("orderList",this.orderInfoMapper.selectList(orderinfoData));
        System.out.println(orderinfoData);
        modelAndView.setViewName("myproduct");


        return modelAndView;
    }
}

/**
 * 竞价
 * id  commodity_id  price_address new_price  create_time
 * 页面显示 最新价格，出价人地址，时间
 * 用户上传自己的出价，系统显示最新出价，一旦出价不可撤回（首先系统判断用户上传的价格是否小于账号余额并判断是否大于最新价格）
 * 作品拥有者在作品页面显示目前的最新价格，如果同意则进行交易
 */