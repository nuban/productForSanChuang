//package com.bc.utils;
//
//import org.web3j.abi.datatypes.Address;
//import org.web3j.abi.datatypes.Utf8String;
//import org.web3j.abi.datatypes.generated.Int256;
//import org.web3j.crypto.Credentials;
//import org.web3j.crypto.WalletUtils;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.web3j.protocol.http.HttpService;
//import java.math.BigInteger;
//import java.util.List;
//
//public class Testcontract {
//
//
//    public static void main(String[] arg) throws Exception {
//
//        Web3j web3j = Web3j.build(new HttpService("http://192.168.1.102:8545"));
//
//        BigInteger gasPrices = web3j.ethGasPrice().send().getGasPrice();
//        System.out.println(gasPrices);
//
////        BigInteger gasPrice = new BigInteger("100000000");
//        BigInteger gasLimit = new BigInteger("4700000");
//
//        // 获取第一个账户
//        String firstAccouts = web3j.ethAccounts().send().getAccounts().get(0);
//        System.out.println("firstAccouts address : " + firstAccouts);
//
//        // 设置钱包账户
//        Credentials credentials = WalletUtils.loadCredentials("123456", "D:\\java\\bcnft\\src\\main\\java\\com\\bc\\utils\\UTC--2022-03-07T12-03-58.txt");
//        System.out.println(credentials.getAddress());
//
//        // 部署合约
////        Test_sol_testETH deployContract = Test_sol_testETH.deploy(web3j, credentials, gasPrices, gasLimit).send();
//        Erc_sol_ERC721Contract deployCon = Erc_sol_ERC721Contract.deploy(web3j,credentials,gasPrices,gasLimit).send();
////        Con_sol_con deployCon = Con_sol_con.deploy(web3j,credentials,gasPrices,gasLimit).send();
//
//        // 部署完成后打印合约地址
////        System.out.println(deployContract.getContractAddress());
//        System.out.println("------------*****------------");
//        System.out.println(deployCon.getContractAddress());
//        System.out.println("------------*****-----------");
//
//        // 判断部署的合约是否可用
//        System.out.println(deployCon.isValid());
//        System.out.println(deployCon.isValid());
//
//
////        Test_sol_testETH testContract = new Test_sol_testETH("0x78fdff535bef7be046a850194fb984261285c70e",web3j,
////                credentials,gasPrices,gasLimit);
//        // 调用合约
////        Test_sol_testETH testContract = new Test_sol_testETH(deployContract.getContractAddress(),web3j,
////                credentials,gasPrices,gasLimit);
////
////        TransactionReceipt transactionReceipt = testContract.getabc().send();
//////        String stat = transactionReceipt.getStatus();
////        List<Test_sol_testETH.GetEventEventResponse> getEventEventResponses = testContract.getGetEventEvents(transactionReceipt);
////        System.out.println("-----*****-------");
////        System.out.println(getEventEventResponses.get(0).i);
////        System.out.println("******----******");
//
////        Con_sol_con testcon = new Con_sol_con(deployCon.getContractAddress(),web3j,credentials,gasPrices,gasLimit);
////        TransactionReceipt transactionReceipt1 = testCon.add(new Int256(1),new Int256(10)).send();
////        List<Con_sol_con.DisplayEventResponse> displayEventResponses = testCon.getDisplayEvents(transactionReceipt1);
////        System.out.println(displayEventResponses.get(0).i);
//
////        Erc_sol_ERC721Contract testcon = new Erc_sol_ERC721Contract(deployCon.getContractAddress(),web3j,credentials,gasPrices,gasLimit);
////        TransactionReceipt transactionReceipt = testcon.createNFT(new Int256(1),new Utf8String("qmasdfals")).send();
////        List<Erc_sol_ERC721Contract.CreateNFT_evenEventResponse> createNFT_evenEventResponses = testcon.getCreateNFT_evenEvents(transactionReceipt);
////        System.out.println("------创造NFT--------");
////        System.out.println(createNFT_evenEventResponses.get(0).data);
////        System.out.println(transactionReceipt.getTransactionHash());
////        System.out.println("------创造NFT--------");
//
////        TransactionReceipt transactionReceipt3 = testcon.tokenURI(new Int256(1)).send();
////        List<Erc_sol_ERC721Contract.GetURI_evenEventResponse> gettt = testcon.getGetURI_evenEvents(transactionReceipt3);
////        System.out.println("------NFT绑定的ipfs--------");
////        System.out.println(gettt.get(0).data);
////        System.out.println("----------------------------");
////
////        TransactionReceipt transactionReceipt1 = testcon.ipfsLinkAdd(new Int256(1),new Address("0x78731D3Ca6b7E34aC0F824c42a7cC18A495cabaB")).send();
////        List<Erc_sol_ERC721Contract.IpfsLinkAdd_evenEventResponse> aa = testcon.getIpfsLinkAdd_evenEvents(transactionReceipt1);
////        System.out.println("------ipfs连接地址--------");
////        System.out.println(aa.get(0).data);
////        System.out.println("--------------------------");
////
////        TransactionReceipt transactionReceipt2 = testcon.transferFrom(new Address("0x78731D3Ca6b7E34aC0F824c42a7cC18A495cabaB"),
////                new Address("0x03C6FcED478cBbC9a4FAB34eF9f40767739D1Ff7"), new Int256(1)).send();
////        List<Erc_sol_ERC721Contract.IpfsLinkAdd_evenEventResponse> bb = testcon.getIpfsLinkAdd_evenEvents(transactionReceipt2);
////        System.out.println("------NFT所有权更改--------");
////        System.out.println(bb.get(0).data);
////        System.out.println("-------------------------");
//
////        TransactionReceipt transactionReceipt = testcon.createNFT(new Int256(1),new Utf8String("qmasdfals")).send();
////        List<Erc_sol_ERC721Contract.CreateNFT_evenEventResponse> createNFT_evenEventResponses = testcon.getCreateNFT_evenEvents(transactionReceipt);
////        System.out.println("------createNFT --------");
////        System.out.println(createNFT_evenEventResponses.get(0).data);
////        System.out.println(transactionReceipt.getTransactionHash());
////        System.out.println("----------------------------");
////
////        TransactionReceipt transactionReceipt1 = testcon.ipfsLinkAdd(new Int256(1),new Address("0x3bf242f32af8b4998b38a2578700632b8195e4da")).send();
////        List<Erc_sol_ERC721Contract.IpfsLinkAdd_evenEventResponse> ipfsLinkAdd_evenEventResponses = testcon.getIpfsLinkAdd_evenEvents(transactionReceipt1);
////        System.out.println("-------ipfs bangding address ---------");
////        System.out.println(ipfsLinkAdd_evenEventResponses.get(0).data);
////        System.out.println("----------------------------");
//
////        TransactionReceipt transactionReceipt4 = testcon.getAddressByID(new Int256(1)).send();
////        List<Erc_sol_ERC721Contract.GetAddressByID_evenEventResponse>  aaa = testcon.getGetAddressByID_evenEvents(transactionReceipt4);
////        System.out.println("---------****111****---------");
////        System.out.println(aaa.get(0).date);
////        System.out.println("----------------------------");
////        TransactionReceipt transactionReceipt4 = testcon.tokenURI(new Int256(1)).send();
////        List<Erc_sol_ERC721Contract.GetURI_evenEventResponse>  aaa = testcon.getGetURI_evenEvents(transactionReceipt4);
////        System.out.println("---------****111****---------");
////        System.out.println(aaa.get(0).data);
////        System.out.println("----------------------------");
//
//
//
////        TransactionReceipt transactionReceipt2 = testcon.transferFrom(new Address("0x5B38Da6a701c568545dCfcB03FcB875f56beddC4"),new Address("0x14723A09ACff6D2A60DcdF7aA4AFf308FDDC160C"),new Int256(1)).send();
////        List<Erc_sol_ERC721Contract.Transfer_evenEventResponse> transfer_evenEventResponses = testcon.getTransfer_evenEvents(transactionReceipt2);
////        TransactionReceipt transactionReceipt = testcon.transferFrom(new Address("0x3bf242f32af8b4998b38a2578700632b8195e4da"),new Address("0x124a3d6b6d93d143f3d3eeb353fefc4100c81668"),new Int256(1)).send();
////        List<Erc_sol_ERC721Contract.Transfer_evenEventResponse> transfer_evenEventResponses = testcon.getTransfer_evenEvents(transactionReceipt);
////        System.out.println("-----tranfer--------");
////        System.out.println(transfer_evenEventResponses.get(0).date);
////        System.out.println(transactionReceipt.getTransactionHash());
////        System.out.println("-------------------------");
////
////        TransactionReceipt transactionReceipt3 = testcon.getAddressByID(new Int256(1)).send();
////        List<Erc_sol_ERC721Contract.GetAddressByID_evenEventResponse>  aa = testcon.getGetAddressByID_evenEvents(transactionReceipt3);
////        System.out.println("---------****222****---------");
////        System.out.println(aa.get(0).date);
////        System.out.println("----------------------------");
//
//    }
//}
