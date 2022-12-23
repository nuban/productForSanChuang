//package com.bc.utils;
//
//import org.web3j.protocol.admin.Admin;
//import org.web3j.protocol.core.methods.request.Transaction;
//import org.web3j.protocol.http.HttpService;
//
//import java.io.IOException;
//import java.math.BigInteger;
//import java.util.List;
//import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
//
///// 本科同学测试
//public class test {
//    public static void main(String[] args) throws IOException {
//        Admin web3j = Admin.build(new HttpService("http://192.168.1.102:8545"));
//        List<String> accounts = web3j.ethAccounts().send().getAccounts();
//        String account1 = accounts.get(0);//获取第一个账户的信息
//        String account2 = accounts.get(1);//获取第二个账户的信息
//        String from = account1;  //发送方
//        String to = account2;     //接收方
//        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from,"123456").send();
//        BigInteger nonce = null;
//        BigInteger gasPrice = null;
//        BigInteger gasLimit = null;
//        BigInteger value = new BigInteger("100000");
//
//        Transaction transaction = new Transaction(from,nonce,gasPrice,gasLimit,to,value,"55736572207472616e73666572");
//        String transactionHash = web3j.ethSendTransaction(transaction).send().getTransactionHash();
//        System.out.println("交易号码：" + transactionHash);
//    }
//}
