//package com.bc.utils;
//
//import org.web3j.crypto.*;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.admin.Admin;
//import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
//import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
//import org.web3j.protocol.core.DefaultBlockParameter;
//import org.web3j.protocol.core.DefaultBlockParameterName;
//import org.web3j.protocol.core.methods.request.Transaction;
//import org.web3j.protocol.core.methods.response.EthGetBalance;
//
//
//
//import org.web3j.protocol.http.HttpService;
//import org.web3j.utils.Convert;
//
//import java.io.File;
//import java.io.IOException;
//import java.math.BigInteger;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.NoSuchAlgorithmException;
//import java.security.NoSuchProviderException;
//import java.util.List;
//
//public class TestETH {
//    public static void main(String[] arg) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException, CipherException {
//
//
//        Admin web3j = Admin.build(new HttpService("http://192.168.1.102:8545"));
////        String web3Client = web3j.web3ClientVersion().send().getWeb3ClientVersion();
////        // 获取账户地址
//        List<String> acc = web3j.ethAccounts().send().getAccounts();
//        System.out.println(acc.get(0));
//        System.out.println(acc.get(1));
//////
////        // 获取账户余额
//////        String str1 = acc.get(0);
//////        EthGetBalance balance = web3j.ethGetBalance(str1, DefaultBlockParameter.valueOf("latest")).send();
//////        String blanceEth = Convert.fromWei(balance.getBalance().toString(),Convert.Unit.ETHER).toPlainString().concat("ether");
//////        System.out.println(blanceEth);
//////
////        // 创建账户
//////        String name = web3j.personalNewAccount("12345678").send().getAccountId();
//////        System.out.println(name);
////////
////        String from = acc.get(0);
////        String to = acc.get(1);
////        String fujia = "6d794e616d65497357616e6753656e416e64546869734973426c6f636b436861696e576557696c6c57696e";
////        BigInteger nonce = null;
////        BigInteger gasPrice = null;
////        BigInteger gasLimit = null;
////        BigInteger value = new BigInteger("1000000000000000000");
////        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount(from,"123456").send();
////        System.out.println("--------------------");
////        System.out.println(personalUnlockAccount.accountUnlocked());  // false  or true
////        System.out.println("------------------");
////        Transaction transaction = new Transaction(from,nonce,gasPrice,gasLimit,to,value,fujia);
////        String transactionHash = web3j.ethSendTransaction(transaction).send().getTransactionHash();
////        System.out.println(transactionHash);
//
////        BigInteger balance1 = web3j.ethGetBalance(from, DefaultBlockParameterName.LATEST).send().getBalance();
////        BigInteger balance2 = web3j.ethGetBalance(to, DefaultBlockParameterName.LATEST).send().getBalance();
////        System.out.println("账户一的余额："+balance1);
////        System.out.println("账户二的余额："+balance2);
//
//
//
////        // 获取钱包文件
////        String fileName="";
////        String filePath="D:\\java\\bcnft\\src\\main\\java\\com\\bc\\utils\\";
////        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
//////        BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();
//////        String privateKey = privateKeyInDec.toString(16);
////        WalletFile aWallet = Wallet.createLight("123456", ecKeyPair);
////        String address = aWallet.getAddress();
////        System.out.println(address);
////        fileName = filePath + WalletUtils.generateNewWalletFile("123456",new File(filePath),false);
////
////
////        // 读取钱包文件
////        Credentials credentials = WalletUtils.loadCredentials("123456",fileName);
////        String address = credentials.getAddress();
////        BigInteger publicKey = credentials.getEcKeyPair().getPublicKey();
////        BigInteger privatekey = credentials.getEcKeyPair().getPrivateKey();
////        System.out.println(address);
////        System.out.println(publicKey);
////        System.out.println(privatekey);
//
//
////        System.out.println("abc");
//    }
//}
