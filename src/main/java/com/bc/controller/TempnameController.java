package com.bc.controller;


import com.bc.entity.Tempname;
import com.bc.entity.User;
import com.bc.mapper.TempnameMapper;
import com.bc.mapper.UserMapper;
//import com.bc.utils.Erc_sol_ERC721Contract;
import com.bc.utils.ErrorEnum;
import com.bc.utils.ExceptionDeal;
//import com.bc.utils.RunContract;
import com.bc.utils.FileRead;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
//import org.web3j.abi.datatypes.Address;
//import org.web3j.abi.datatypes.Utf8String;
//import org.web3j.abi.datatypes.generated.Int256;
//import org.web3j.crypto.Credentials;
//import org.web3j.crypto.WalletUtils;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.web3j.protocol.http.HttpService;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ws
 * @since 2022-03-01
 */
@Controller
@RequestMapping("/tempname")
@Slf4j
public class TempnameController {

    @Autowired
    private TempnameMapper tempnameMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 实例化ipfs节点
     */
//    private static IPFS ipfs = new IPFS("/ip4/192.168.1.102/tcp/5001");

    @RequestMapping("/upload")
    @ResponseBody
    public ModelAndView upload(
            @RequestParam("commodityCategory") String commodityCategory,
            @RequestParam("commodityName") String commodityName,
            @RequestParam("commodityPrice")BigDecimal commodityPrice,
            @RequestParam("commodityInformation") String commodityInformation,
            @RequestParam("commodityImages") MultipartFile commodityImages,

            HttpSession session) throws Exception{
        System.out.println("----------------------");
        System.out.println(commodityName);
        System.out.println(commodityPrice);
        System.out.println(commodityInformation);
        System.out.println(commodityImages.getOriginalFilename());
        System.out.println(commodityCategory);

        if (commodityImages == null || commodityInformation == null || commodityName == null | commodityPrice == null){
            log.info("[添加作品]传入数据为空");
        }

        User user = (User)session.getAttribute("user");
        if (user == null){
            log.info("[添加作品]用户未登录");
            throw new ExceptionDeal(ErrorEnum.USER_EXIST);
        }


        if (commodityImages.isEmpty()){
            System.out.println("[上传作品]文件为空");
        }
        String oldFileName = commodityImages.getOriginalFilename();
        // System.currentTimeMillis()  获取时间戳
        String newFileName = user.getUserName() + "_" + System.currentTimeMillis() + "." + oldFileName.substring(oldFileName.lastIndexOf(".")+1);
        System.out.println(System.currentTimeMillis());
        System.out.println(newFileName);
//        String filePath = "D:\\java\\bcnft\\src\\\\main\\resources\\static\\images\\file\\";
        String filePath = "E:\\wampserver\\www\\image\\UploadImage\\";

        File dest = new File(filePath + newFileName);

        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            commodityImages.transferTo(dest);
        }catch(Exception e){
            e.printStackTrace();
        }

        String testPath = "E:\\wampserver\\www\\image\\UploadImage\\" + newFileName;
        boolean flag = FileRead.queryFile(filePath,testPath);
        if(!flag){
            System.out.println("----------*****----------------");
            System.out.println("重复了");
            System.out.println("---------******-----------------");
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("uploadFile");
            modelAndView.addObject("checkData","1");
            return modelAndView;
        }

        Tempname tempname = new Tempname();
        tempname.setCategory(commodityCategory);
        tempname.setCommodityName(commodityName);
        tempname.setCommodityPrice(commodityPrice);
        tempname.setCommodityInformation(commodityInformation);
        tempname.setCommodityImages(newFileName);

        /**
         * IPFS
         */
//         将文件上传至ipfs，并保存在数据库中，首先在ipfs中运行 ipfs daemon
//        NamedStreamable.FileWrapper saveFile = new NamedStreamable.FileWrapper(new File("E:\\wampserver\\www\\image\\"+newFileName));
//        MerkleNode result = ipfs.add(saveFile).get(0);
//        tempname.setCommodityImages(result.hash.toString());

//        File file11=new File("E:\\wampserver\\www\\image\\"+newFileName);
//        String newnam = result.hash.toString() + ".jpg";
//        File newn = new File("E:\\wampserver\\www\\image\\" + newnam);
//        file11.renameTo(newn);

        /**
         * 将IPFS链接上传至区块链，生成 NFT
         * 创建 nft   createNFT
         */
//        Web3j web3j = Web3j.build(new HttpService("http://192.168.1.102:8545"));
//        BigInteger gasPrices = web3j.ethGasPrice().send().getGasPrice();
//        BigInteger gasLimit = new BigInteger("4700000");
//        Credentials credentials = WalletUtils.loadCredentials("123456", "D:\\java\\bcnft\\src\\main\\java\\com\\bc\\utils\\UTC--2022-03-07T12-03-58.txt");
//        Erc_sol_ERC721Contract testcon = new Erc_sol_ERC721Contract(RunContract.getContractAddress(),web3j,credentials,gasPrices,gasLimit);
//
//        testcon.createNFT(new Int256(1),new Utf8String(result.hash.toString())).send();
//        TransactionReceipt transactionReceipt = testcon.createNFT(new Int256(1),new Utf8String(result.hash.toString())).send();
//        List<Erc_sol_ERC721Contract.CreateNFT_evenEventResponse> createNFT_evenEventResponses = testcon.getCreateNFT_evenEvents(transactionReceipt);
//        System.out.println(createNFT_evenEventResponses.get(0).data);
//        System.out.println(transactionReceipt.getTransactionHash());
        /**
         * 将ipfs链接 与 用户地址进行绑定
         * ipfsLinkAdd
         *
         * 怎么设置 nft 的唯一id值
         */
//        TransactionReceipt transactionReceipt1 = testcon.ipfsLinkAdd(new Int256(1),new Address(user.getMoneryAdd())).send();
//        List<Erc_sol_ERC721Contract.IpfsLinkAdd_evenEventResponse> ipfsLinkAdd_evenEventResponses = testcon.getIpfsLinkAdd_evenEvents(transactionReceipt1);
//        System.out.println(ipfsLinkAdd_evenEventResponses.get(0).data);


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String t = df.format(new Date());

        tempname.setCreateTime(t);
        tempname.setUserId(user.getId());
        ModelAndView modelAndView = new ModelAndView();
        int insert = this.tempnameMapper.insert(tempname);
        if (insert == 0){
            log.info("[添加作品]添加作品失败");
        }else {

            modelAndView.setViewName("uploadFile");
            modelAndView.addObject("addData","作品添加成功，等待审核中...");

        }

        return modelAndView;
    }


//    @RequestMapping("/upload")
//    @ResponseBody
//    public ModelAndView upload(@RequestParam("commodityName") String commodityName,
//                               @RequestParam("commodityPrice")BigDecimal commodityPrice,
//                               @RequestParam("commodityInformation") String commodityInformation,
//                               @RequestParam("commodityImages") MultipartFile commodityImages,
//                               HttpSession session) throws Exception{
//        System.out.println("----------------------");
//        System.out.println(commodityName);
//        System.out.println(commodityPrice);
//        System.out.println(commodityInformation);
//        System.out.println(commodityImages.getOriginalFilename());
//
//        if (commodityImages == null || commodityInformation == null || commodityName == null | commodityPrice == null){
//            log.info("[添加作品]传入数据为空");
//        }
//
//        User user = (User)session.getAttribute("user");
//        if (user == null){
//            log.info("[添加作品]用户未登录");
//            throw new ExceptionDeal(ErrorEnum.USER_EXIST);
//        }
//
//        if (commodityImages.isEmpty()){
//            System.out.println("[上传作品]文件为空");
//        }
//        String oldFileName = commodityImages.getOriginalFilename();
//        // System.currentTimeMillis()  获取时间戳
//        String newFileName = user.getUserName() + "_" + System.currentTimeMillis() + "." + oldFileName.substring(oldFileName.lastIndexOf(".")+1);
//        System.out.println(System.currentTimeMillis());
//        System.out.println(newFileName);
//        String filePath = "D:\\java\\bcnft\\src\\main\\resources\\static\\images\\file\\";
//
//        File dest = new File(filePath + newFileName);
//
//        if (!dest.getParentFile().exists()){
//            dest.getParentFile().mkdirs();
//        }
//        try{
//            commodityImages.transferTo(dest);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        Tempname tempname = new Tempname();
//        tempname.setCommodityName(commodityName);
//        tempname.setCommodityPrice(commodityPrice);
//        tempname.setCommodityInformation(commodityInformation);
//
//        /**
//         * IPFS
//         */
//        // 将文件上传至ipfs，并保存在数据库中，首先在ipfs中运行 ipfs daemon
////        tempname.setCommodityImages(newFileName);
////        NamedStreamable.FileWrapper saveFile = new NamedStreamable.FileWrapper(new File("D:\\java\\bcnft\\src\\main\\resources\\static\\images\\file\\"+newFileName));
////        MerkleNode result = ipfs.add(saveFile).get(0);
//////        System.out.println(result.hash.toString());
////        tempname.setCommodityImages(result.hash.toString());
//
//        /**
//         * 将IPFS链接上传至区块链，生成 NFT
//         * 创建 nft   createNFT
//         */
////        Web3j web3j = Web3j.build(new HttpService("http://192.168.1.102:8545"));
////        BigInteger gasPrices = web3j.ethGasPrice().send().getGasPrice();
////        BigInteger gasLimit = new BigInteger("4700000");
////        Credentials credentials = WalletUtils.loadCredentials("123456", "D:\\java\\bcnft\\src\\main\\java\\com\\bc\\utils\\UTC--2022-03-07T12-03-58.txt");
////        Erc_sol_ERC721Contract testcon = new Erc_sol_ERC721Contract(RunContract.getContractAddress(),web3j,credentials,gasPrices,gasLimit);
////
////        TransactionReceipt transactionReceipt = testcon.createNFT(new Int256(1),new Utf8String(result.hash.toString())).send();
////        List<Erc_sol_ERC721Contract.CreateNFT_evenEventResponse> createNFT_evenEventResponses = testcon.getCreateNFT_evenEvents(transactionReceipt);
////        System.out.println(createNFT_evenEventResponses.get(0).data);
////        System.out.println(transactionReceipt.getTransactionHash());
//        /**
//         * 将ipfs链接 与 用户地址进行绑定
//         * ipfsLinkAdd
//         *
//         * 怎么设置 nft 的唯一id值
//         */
////        TransactionReceipt transactionReceipt1 = testcon.ipfsLinkAdd(new Int256(1),new Address(user.getMoneryAdd())).send();
////        List<Erc_sol_ERC721Contract.IpfsLinkAdd_evenEventResponse> ipfsLinkAdd_evenEventResponses = testcon.getIpfsLinkAdd_evenEvents(transactionReceipt1);
////        System.out.println(ipfsLinkAdd_evenEventResponses.get(0).data);
//
//
//
//        // 1、用户页面上传至temp数据库
//        // 2、后台读取temp数据库的数据进行判断
//        // 3、后台审核通过后上传至 ipfs中，获取返回的值，并一起传至commodity表中
//        // 4、读取ipfs链接并添加水印下载至本地，进行读取
//
//
//
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        String t = df.format(new Date());
//
//        tempname.setCreateTime(t);
//        tempname.setUserId(user.getId());
//        ModelAndView modelAndView = new ModelAndView();
//        int insert = this.tempnameMapper.insert(tempname);
//        if (insert == 0){
//            log.info("[添加作品]添加作品失败");
//        }else {
//
//            modelAndView.setViewName("uploadFile");
//            modelAndView.addObject("addData","作品添加成功，等待审核中...");
//
//        }
//
//        return modelAndView;
//    }

    @GetMapping("uploadfile")
    public ModelAndView uploadfile(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("uploadFile");

        return modelAndView;
    }
    @PostMapping("updateheadimg")
    public ModelAndView updateheadimg(){
        System.out.println("可以了");
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}

