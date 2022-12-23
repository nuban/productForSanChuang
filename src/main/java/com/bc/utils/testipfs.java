//package com.bc.utils;
//
////import io.ipfs.api.IPFS;
////import io.ipfs.api.MerkleNode;
////import io.ipfs.api.NamedStreamable;
//
//import java.io.File;
//import java.io.IOException;
//
//public class testipfs {
//    /**
//     * 实例化ipfs节点
//     */
//    private static IPFS ipfs = new IPFS("/ip4/192.168.1.106/tcp/5001");
//
//    /**
//     * 添加文件
//     */
//    public static void add() throws IOException {
//        NamedStreamable.FileWrapper saveFile = new NamedStreamable.FileWrapper(new File("D:\\java\\bcnft\\src\\main\\resources\\static\\images\\baby_4.jpg"));
//        MerkleNode result = ipfs.add(saveFile).get(0);
//        System.out.println(result.hash.toString());
//    }
//    public static void main(String[] arg) throws IOException{
//        add();
//        System.out.println("aaaa");
//    }
//}
