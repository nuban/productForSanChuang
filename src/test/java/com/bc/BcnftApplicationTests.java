package com.bc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bc.entity.ShoppingCart;
import com.bc.entity.User;
import com.bc.mapper.ShoppingCartMapper;
import com.bc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.web3j.abi.datatypes.Utf8String;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
class BcnftApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

//    @Test
//    public void updatedata(){
//        int a = this.userMapper.updateBalance(1,300);
//        System.out.println(a);
//    }

//    @Test
//    public void display(User user){
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_name",user.getUserName());
//        User aa = this.userMapper.selectOne(queryWrapper);
//        System.out.println(aa.getUserName()+"--"+aa.getMoneryAdd());
//    }

    @Test
    public void display1(User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("password",user.getPassword());
        List<User> userlist= this.userMapper.selectList(queryWrapper);
        System.out.println(userlist.get(0).getUserName() + "--" +
                userlist.get(0).getPassword() + "--" + userlist.get(0).getMoneryAdd());
    }

    @Test
    void abc(){
        User user1 = new User();
        user1.setPassword("123456");
        display1(user1);
    }

    @Test
    void aaaf(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id","1");
        List<User> users = this.userMapper.selectList(queryWrapper);
        System.out.println(users.get(0).getMoneryAdd());
    }

    @Test
    void creatTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String t = df.format(new Date());
        System.out.println("当前时间为：" + t);// new Date()为获取当前系统时间
    }

    @Test
    void num(){
        String seriaNumber = null;
        try {
            StringBuffer result = new StringBuffer();
            for(int i=0;i<16;i++) {
                result.append(Integer.toHexString(new Random().nextInt(10)));
            }
            seriaNumber =  result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("随机数：" + seriaNumber);
    }

    @Test
    void an(){
        long startTime = System.currentTimeMillis();
        String str = "1 bfasjdlgjalsjdlfsdbgjasdowljgabnlhajldfjsngweodjfjalsjgasdjfohgoasdhfwoegasd";
        char[] strChar=str.toCharArray();
        String result="";
        for(int i=0;i<strChar.length;i++){
            String te = Integer.toBinaryString(strChar[i]);
            int len = te.length();
            if(len != 8){
                for(int j = 0; j< 8 - len; j++){
                    te = "0" + te;
                }
            }
            result += te+ " ";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        System.out.println(result);
    }
    @Test
    void sss(){
        //List<ShoppingCart> shoppingCart = shoppingCartMapper.selectListss();
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectList(null);
        System.out.println(shoppingCarts);
    }

//    @Test
//    void bi(){
//        Utf8String aa = new Utf8String("nftOwnershipChangeSuccessded");
//        if(aa.equals(new Utf8String("nftOwnershipChangeSuccessded")) ){
//            System.out.println("yes");
//        }else {
//            System.out.println("no");
//        }
//    }

//    @Test
//    void addMonery(){
//        String add = "0x124a3d6b6d93d143f3d3eeb353fefc4100c81668";
//        BigInteger price = new BigInteger(String.valueOf(11));
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("monery_add",add);
//        List<User> users = this.userMapper.selectList(queryWrapper);
//        BigInteger monery = users.get(0).getBalancePrice();
//        BigInteger newMonery = monery.add(price);
//        this.userMapper.updateBalance(users.get(0).getId(),newMonery);
//
//    }

//    @Test
//    void reduce(){
//        String add = "0x124a3d6b6d93d143f3d3eeb353fefc4100c81668";
//        BigInteger price = new BigInteger(String.valueOf(35));
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("monery_add", add);
//        List<User> users = this.userMapper.selectList(queryWrapper);
//        BigInteger monery = users.get(0).getBalancePrice();
//        BigInteger newMonery = monery.subtract(price);
//        this.userMapper.updateBalance(users.get(0).getId(), newMonery);
//    }
}
