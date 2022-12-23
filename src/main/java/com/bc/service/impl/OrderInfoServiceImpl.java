package com.bc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bc.entity.CommodityInfo;
import com.bc.entity.OrderInfo;
import com.bc.entity.ShoppingCart;
import com.bc.entity.User;
import com.bc.mapper.CommodityInfoMapper;
import com.bc.mapper.OrderInfoMapper;
import com.bc.mapper.ShoppingCartMapper;
import com.bc.mapper.UserMapper;
import com.bc.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.bc.utils.Erc_sol_ERC721Contract;
//import com.bc.utils.RunContract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
//import org.web3j.abi.datatypes.Address;
//import org.web3j.abi.datatypes.Utf8String;
//import org.web3j.abi.datatypes.generated.Int256;
//import org.web3j.crypto.Credentials;
//import org.web3j.crypto.WalletUtils;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.web3j.protocol.http.HttpService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ws
 * @since 2022-02-27
 */
@Service
@Slf4j
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private CommodityInfoMapper commodityInfoMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean commitData(User user) throws Exception {
        // 创建订单表
        QueryWrapper<ShoppingCart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        List<ShoppingCart> allOrder = this.shoppingCartMapper.selectList(queryWrapper);
        OrderInfo orderInfo = new OrderInfo();
        for (int i = 0; i < allOrder.size(); i++){


            orderInfo.setCommodityName(allOrder.get(i).getCmdName());
            orderInfo.setCommodityId(allOrder.get(i).getCommodityId());
            orderInfo.setCommodityPrice(allOrder.get(i).getCmdPrice());
            orderInfo.setCommodityImages(allOrder.get(i).getCmdImages());

            // 增加减少对应用户的价格


            // 查询商品介绍
            QueryWrapper<CommodityInfo> queryInfo = new QueryWrapper<>();
            queryInfo.eq("id", allOrder.get(i).getCommodityId());
            List<CommodityInfo> comInfo = this.commodityInfoMapper.selectList(queryInfo);

            orderInfo.setCommodityInfo(comInfo.get(0).getInformation());
            orderInfo.setSellerAdd(allOrder.get(i).getCmdAddress());
            orderInfo.setBuyerId(allOrder.get(i).getUserId());
            orderInfo.setBuyerAdd(allOrder.get(i).getNewAddress());


            /**
             * 改变商品的所有权，即进行NFT的售卖
             * 获取交易成功后的合约地址，作为商品订单号
             * transferFrom
             */
//            Web3j web3j = Web3j.build(new HttpService("http://192.168.1.102:8545"));
//            BigInteger gasPrices = web3j.ethGasPrice().send().getGasPrice();
//            BigInteger gasLimit = new BigInteger("4700000");
//            Credentials credentials = WalletUtils.loadCredentials("123456", "D:\\java\\bcnft\\src\\main\\java\\com\\bc\\utils\\UTC--2022-03-07T12-03-58.txt");
//            Erc_sol_ERC721Contract testcon = new Erc_sol_ERC721Contract(RunContract.getContractAddress(),web3j,credentials,gasPrices,gasLimit);

//            TransactionReceipt transactionReceipt1 = testcon.ipfsLinkAdd(new Int256(1),new Address("0x3bf242f32af8b4998b38a2578700632b8195e4da")).send();
//            List<Erc_sol_ERC721Contract.IpfsLinkAdd_evenEventResponse> ipfsLinkAdd_evenEventResponses = testcon.getIpfsLinkAdd_evenEvents(transactionReceipt1);
//            System.out.println("-------ipfs bangding address ---------");
//            System.out.println(ipfsLinkAdd_evenEventResponses.get(0).data);
//            System.out.println("----------------------------");


//            TransactionReceipt transactionReceipt = testcon.transferFrom(new Address(allOrder.get(i).getCmdAddress()),new Address(allOrder.get(i).getNewAddress()),new Int256(1)).send();
//            List<Erc_sol_ERC721Contract.Transfer_evenEventResponse> transfer_evenEventResponses = testcon.getTransfer_evenEvents(transactionReceipt);
//            Utf8String temp = transfer_evenEventResponses.get(0).date;
//            orderInfo.setOrderHash(transactionReceipt.getTransactionHash());
//            if(transfer_evenEventResponses.get(0).date.equals(new Utf8String("nftOwnershipChangeSuccessded"))){
//                orderInfo.setOrderHash(transactionReceipt.getTransactionHash());
//            }else{
//                orderInfo.setOrderHash("orderFaild");
//            }

            orderInfo.setOrderHash("dingdanHASH");


            // 添加时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String t = df.format(new Date());
            orderInfo.setCreatTime(t);

            int insert = this.orderInfoMapper.insert(orderInfo);
            if (insert != 1){
                log.info("[提交订单]创建订单数据失败");
            }
        }

        System.out.println("-----");
        System.out.println(allOrder.size());

        // 删除当前用户购物车
        QueryWrapper<ShoppingCart> queryWrapperShop = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        int delete = this.shoppingCartMapper.delete(queryWrapperShop);
        if (delete != 1){
            log.info("[提交订单]删除购物车数据失败");
        }

        return true;
    }

//    @Override
    public void addMonery(String add, BigInteger price){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("monery_add",add);
        List<User> users = this.userMapper.selectList(queryWrapper);
        BigInteger monery = users.get(0).getBalancePrice();
        BigInteger newMonery = monery.add(price);
        this.userMapper.updateBalance(users.get(0).getId(),newMonery);

    }

    public void reduceMonery(String add, BigInteger price){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("monery_add", add);
        List<User> users = this.userMapper.selectList(queryWrapper);
        BigInteger monery = users.get(0).getBalancePrice();
        BigInteger newMonery = monery.subtract(price);
        this.userMapper.updateBalance(users.get(0).getId(), newMonery);
    }
}
