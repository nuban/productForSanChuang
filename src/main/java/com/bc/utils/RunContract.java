//package com.bc.utils;
//
//import org.web3j.crypto.CipherException;
//import org.web3j.crypto.Credentials;
//import org.web3j.crypto.WalletUtils;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.http.HttpService;
//
//import java.io.IOException;
//import java.math.BigInteger;
//
//public class RunContract {
//    private static String CONTRACT_ADDRESS = "";
//    public static String getContractAddress() {
//        return CONTRACT_ADDRESS;
//    }
//
//    public static void setContractAddress(String contractAddress) {
//        CONTRACT_ADDRESS = contractAddress;
//    }
//    public static boolean test() throws Exception {
//        // 部署合约
//        Web3j web3j = Web3j.build(new HttpService("http://192.168.1.102:8545"));
//        BigInteger gasPrices = web3j.ethGasPrice().send().getGasPrice();
//        BigInteger gasLimit = new BigInteger("4700000");
//        // 获取第一个账户
//        String firstAccouts = web3j.ethAccounts().send().getAccounts().get(0);
//        Credentials credentials = WalletUtils.loadCredentials("123456", "D:\\java\\bcnft\\src\\main\\java\\com\\bc\\utils\\UTC--2022-03-07T12-03-58.txt");
//        // 部署合约
//        Erc_sol_ERC721Contract deployCon = Erc_sol_ERC721Contract.deploy(web3j,credentials,gasPrices,gasLimit).send();
//        // 部署完成后打印合约地址
//        System.out.println(deployCon.getContractAddress());
//        setContractAddress(deployCon.getContractAddress());
//        // 判断部署的合约是否可用
//        System.out.println(deployCon.isValid());
//        return  deployCon.isValid();
////        return true;
//    }
//
//
//}
