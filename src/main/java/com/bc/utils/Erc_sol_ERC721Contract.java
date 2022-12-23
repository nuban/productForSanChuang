//package com.bc.utils;
//
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import org.web3j.abi.EventEncoder;
//import org.web3j.abi.TypeReference;
//import org.web3j.abi.datatypes.Address;
//import org.web3j.abi.datatypes.DynamicBytes;
//import org.web3j.abi.datatypes.Event;
//import org.web3j.abi.datatypes.Function;
//import org.web3j.abi.datatypes.Type;
//import org.web3j.abi.datatypes.Utf8String;
//import org.web3j.abi.datatypes.generated.Int256;
//import org.web3j.crypto.Credentials;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.DefaultBlockParameter;
//import org.web3j.protocol.core.RemoteCall;
//import org.web3j.protocol.core.methods.request.EthFilter;
//import org.web3j.protocol.core.methods.response.Log;
//import org.web3j.protocol.core.methods.response.TransactionReceipt;
//import org.web3j.tx.Contract;
//import org.web3j.tx.TransactionManager;
//import rx.Observable;
//import rx.functions.Func1;
//
///**
// * <p>Auto generated code.
// * <p><strong>Do not modify!</strong>
// * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
// * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
// * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
// *
// * <p>Generated with web3j version 3.4.0.
// */
//public class Erc_sol_ERC721Contract extends Contract {
//    private static final String BINARY = "60806040526040518060400160405280600781526020017f7868755f4e4654000000000000000000000000000000000000000000000000008152506000908051906020019062000051929190620000b4565b506040518060400160405280600681526020017f2a5f7c7c5f2a0000000000000000000000000000000000000000000000000000815250600190805190602001906200009f929190620000b4565b50348015620000ad57600080fd5b50620001c9565b828054620000c29062000193565b90600052602060002090601f016020900481019282620000e6576000855562000132565b82601f106200010157805160ff191683800117855562000132565b8280016001018555821562000132579182015b828111156200013157825182559160200191906001019062000114565b5b50905062000141919062000145565b5090565b5b808211156200016057600081600090555060010162000146565b5090565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b60006002820490506001821680620001ac57607f821691505b60208210811415620001c357620001c262000164565b5b50919050565b6116f080620001d96000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c80634115b49e116100665780634115b49e1461010a57806342028a3a1461013a57806395d89b411461016a578063ac31364914610188578063ca38ea73146101a457610093565b806306fdde031461009857806308861a31146100b6578063192193b9146100d25780632bdf2fbb146100ee575b600080fd5b6100a06101d4565b6040516100ad9190610c24565b60405180910390f35b6100d060048036038101906100cb9190610cee565b610266565b005b6100ec60048036038101906100e79190610d2e565b610394565b005b61010860048036038101906101039190610d81565b6105a7565b005b610124600480360381019061011f9190610d81565b610692565b6040516101319190610e03565b60405180910390f35b610154600480360381019061014f9190610d81565b6107fd565b6040516101619190610e34565b60405180910390f35b610172610830565b60405161017f9190610c24565b60405180910390f35b6101a2600480360381019061019d9190610f84565b6108c2565b005b6101be60048036038101906101b99190610d81565b6109c2565b6040516101cb9190610c24565b60405180910390f35b6060600080546101e39061100f565b80601f016020809104026020016040519081016040528092919081815260200182805461020f9061100f565b801561025c5780601f106102315761010080835404028352916020019161025c565b820191906000526020600020905b81548152906001019060200180831161023f57829003601f168201915b5050505050905090565b600073ffffffffffffffffffffffffffffffffffffffff166003600084815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561035a57806003600084815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507f04efafa06cbcb93680d4f187e26695e572834ecc6a9c27a14e61422afca9fe3360405161034d9061108d565b60405180910390a1610390565b7f04efafa06cbcb93680d4f187e26695e572834ecc6a9c27a14e61422afca9fe33604051610387906110f9565b60405180910390a15b5050565b816040516020016103a59190611161565b60405160208183030381529060405280519060200120836040516020016103cc9190611161565b604051602081830303815290604052805190602001201415610422577f7a5eb572804c32669e4e9891a3b2b9d18fe0b602d007a2f636c04a52b83a26b7604051610415906111c8565b60405180910390a16105a2565b826040516020016104339190611161565b604051602081830303815290604052805190602001206003600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660405160200161048d9190611161565b60405160208183030381529060405280519060200120141561056b576003600082815260200190815260200160002060006101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055816003600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507f7a5eb572804c32669e4e9891a3b2b9d18fe0b602d007a2f636c04a52b83a26b760405161055e90611234565b60405180910390a16105a1565b7f7a5eb572804c32669e4e9891a3b2b9d18fe0b602d007a2f636c04a52b83a26b7604051610598906112a0565b60405180910390a15b5b505050565b6040516020016105b6906112f1565b60405160208183030381529060405280519060200120600260008381526020019081526020016000206040516020016105ef919061139a565b604051602081830303815290604052805190602001201415610645577f474c8e245b9fa1d0735e48c69c7cf9f77fd4af3a5710c1758ce024f7619e0878604051610638906113fd565b60405180910390a161068f565b7f474c8e245b9fa1d0735e48c69c7cf9f77fd4af3a5710c1758ce024f7619e087860026000838152602001908152602001600020604051610686919061149d565b60405180910390a15b50565b606060006040516020016106a69190611161565b604051602081830303815290604052805190602001206003600084815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166040516020016107009190611161565b604051602081830303815290604052805190602001201415610756577f68ad6dca4f90cea7612a8f3ef6fe7b22c73092a699f6bbdb41e21adb3c5c7e156040516107499061150b565b60405180910390a16107f8565b6003600083815260200190815260200160002060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660405160200161079a9190611161565b604051602081830303815290604052600490805190602001906107be929190610a62565b507f68ad6dca4f90cea7612a8f3ef6fe7b22c73092a699f6bbdb41e21adb3c5c7e1560046040516107ef91906115c0565b60405180910390a15b919050565b60036020528060005260406000206000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60606001805461083f9061100f565b80601f016020809104026020016040519081016040528092919081815260200182805461086b9061100f565b80156108b85780601f1061088d576101008083540402835291602001916108b8565b820191906000526020600020905b81548152906001019060200180831161089b57829003601f168201915b5050505050905090565b6040516020016108d1906112f1565b604051602081830303815290604052805190602001206002600084815260200190815260200160002060405160200161090a919061139a565b604051602081830303815290604052805190602001201415610988578060026000848152602001908152602001600020908051906020019061094d929190610ae8565b507f61b86dd93582be8d5db1c06ac526ba5d7e0f99b4a2313ddef370aa505d05b7d160405161097b9061162e565b60405180910390a16109be565b7f61b86dd93582be8d5db1c06ac526ba5d7e0f99b4a2313ddef370aa505d05b7d16040516109b59061169a565b60405180910390a15b5050565b600260205280600052604060002060009150905080546109e19061100f565b80601f0160208091040260200160405190810160405280929190818152602001828054610a0d9061100f565b8015610a5a5780601f10610a2f57610100808354040283529160200191610a5a565b820191906000526020600020905b815481529060010190602001808311610a3d57829003601f168201915b505050505081565b828054610a6e9061100f565b90600052602060002090601f016020900481019282610a905760008555610ad7565b82601f10610aa957805160ff1916838001178555610ad7565b82800160010185558215610ad7579182015b82811115610ad6578251825591602001919060010190610abb565b5b509050610ae49190610b6e565b5090565b828054610af49061100f565b90600052602060002090601f016020900481019282610b165760008555610b5d565b82601f10610b2f57805160ff1916838001178555610b5d565b82800160010185558215610b5d579182015b82811115610b5c578251825591602001919060010190610b41565b5b509050610b6a9190610b6e565b5090565b5b80821115610b87576000816000905550600101610b6f565b5090565b600081519050919050565b600082825260208201905092915050565b60005b83811015610bc5578082015181840152602081019050610baa565b83811115610bd4576000848401525b50505050565b6000601f19601f8301169050919050565b6000610bf682610b8b565b610c008185610b96565b9350610c10818560208601610ba7565b610c1981610bda565b840191505092915050565b60006020820190508181036000830152610c3e8184610beb565b905092915050565b6000604051905090565b600080fd5b600080fd5b6000819050919050565b610c6d81610c5a565b8114610c7857600080fd5b50565b600081359050610c8a81610c64565b92915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610cbb82610c90565b9050919050565b610ccb81610cb0565b8114610cd657600080fd5b50565b600081359050610ce881610cc2565b92915050565b60008060408385031215610d0557610d04610c50565b5b6000610d1385828601610c7b565b9250506020610d2485828601610cd9565b9150509250929050565b600080600060608486031215610d4757610d46610c50565b5b6000610d5586828701610cd9565b9350506020610d6686828701610cd9565b9250506040610d7786828701610c7b565b9150509250925092565b600060208284031215610d9757610d96610c50565b5b6000610da584828501610c7b565b91505092915050565b600081519050919050565b600082825260208201905092915050565b6000610dd582610dae565b610ddf8185610db9565b9350610def818560208601610ba7565b610df881610bda565b840191505092915050565b60006020820190508181036000830152610e1d8184610dca565b905092915050565b610e2e81610cb0565b82525050565b6000602082019050610e496000830184610e25565b92915050565b600080fd5b600080fd5b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b610e9182610bda565b810181811067ffffffffffffffff82111715610eb057610eaf610e59565b5b80604052505050565b6000610ec3610c46565b9050610ecf8282610e88565b919050565b600067ffffffffffffffff821115610eef57610eee610e59565b5b610ef882610bda565b9050602081019050919050565b82818337600083830152505050565b6000610f27610f2284610ed4565b610eb9565b905082815260208101848484011115610f4357610f42610e54565b5b610f4e848285610f05565b509392505050565b600082601f830112610f6b57610f6a610e4f565b5b8135610f7b848260208601610f14565b91505092915050565b60008060408385031215610f9b57610f9a610c50565b5b6000610fa985828601610c7b565b925050602083013567ffffffffffffffff811115610fca57610fc9610c55565b5b610fd685828601610f56565b9150509250929050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061102757607f821691505b6020821081141561103b5761103a610fe0565b5b50919050565b7f697066734c696e6b416464207375636365737300000000000000000000000000600082015250565b6000611077601383610b96565b915061108282611041565b602082019050919050565b600060208201905081810360008301526110a68161106a565b9050919050565b7f697066734c696e6b416464206572726f72000000000000000000000000000000600082015250565b60006110e3601183610b96565b91506110ee826110ad565b602082019050919050565b60006020820190508181036000830152611112816110d6565b9050919050565b60008160601b9050919050565b600061113182611119565b9050919050565b600061114382611126565b9050919050565b61115b61115682610cb0565b611138565b82525050565b600061116d828461114a565b60148201915081905092915050565b7f4572726f7254776f4164647265737373417265457175616c0000000000000000600082015250565b60006111b2601883610b96565b91506111bd8261117c565b602082019050919050565b600060208201905081810360008301526111e1816111a5565b9050919050565b7f6e66744f776e6572736869704368616e67655375636365737364656400000000600082015250565b600061121e601c83610b96565b9150611229826111e8565b602082019050919050565b6000602082019050818103600083015261124d81611211565b9050919050565b7f6e66744f776e6572736869704368616e67654661696c65640000000000000000600082015250565b600061128a601883610b96565b915061129582611254565b602082019050919050565b600060208201905081810360008301526112b98161127d565b9050919050565b600081905092915050565b50565b60006112db6000836112c0565b91506112e6826112cb565b600082019050919050565b60006112fc826112ce565b9150819050919050565b60008190508160005260206000209050919050565b600081546113288161100f565b61133281866112c0565b9450600182166000811461134d576001811461135e57611391565b60ff19831686528186019350611391565b61136785611306565b60005b838110156113895781548189015260018201915060208101905061136a565b838801955050505b50505092915050565b60006113a6828461131b565b915081905092915050565b7f4572726f723a20696e707574206e756c6c2c706c6561736520616761696e0000600082015250565b60006113e7601e83610b96565b91506113f2826113b1565b602082019050919050565b60006020820190508181036000830152611416816113da565b9050919050565b6000815461142a8161100f565b6114348186610b96565b9450600182166000811461144f576001811461146157611494565b60ff1983168652602086019350611494565b61146a85611306565b60005b8381101561148c5781548189015260018201915060208101905061146d565b808801955050505b50505092915050565b600060208201905081810360008301526114b7818461141d565b905092915050565b7f696e70757449644e756c6c000000000000000000000000000000000000000000600082015250565b60006114f5600b83610db9565b9150611500826114bf565b602082019050919050565b60006020820190508181036000830152611524816114e8565b9050919050565b60008190508160005260206000209050919050565b6000815461154d8161100f565b6115578186610db9565b945060018216600081146115725760018114611584576115b7565b60ff19831686526020860193506115b7565b61158d8561152b565b60005b838110156115af57815481890152600182019150602081019050611590565b808801955050505b50505092915050565b600060208201905081810360008301526115da8184611540565b905092915050565b7f4e46544164645375636365737300000000000000000000000000000000000000600082015250565b6000611618600d83610b96565b9150611623826115e2565b602082019050919050565b600060208201905081810360008301526116478161160b565b9050919050565b7f4e46544964497345786973740000000000000000000000000000000000000000600082015250565b6000611684600c83610b96565b915061168f8261164e565b602082019050919050565b600060208201905081810360008301526116b381611677565b905091905056fea2646970667358221220a4678c2fdf049b0a1c7760c8f0c4339d4bc5d8913b37428a979761d32c17019764736f6c634300080c0033";
//
//    public static final String FUNC_URIBYID = "URIbyID";
//
//    public static final String FUNC_CREATENFT = "createNFT";
//
//    public static final String FUNC_GETADDRESSBYID = "getAddressByID";
//
//    public static final String FUNC_IPFSBYADD = "ipfsByAdd";
//
//    public static final String FUNC_IPFSLINKADD = "ipfsLinkAdd";
//
//    public static final String FUNC_NAME = "name";
//
//    public static final String FUNC_SYMBOL = "symbol";
//
//    public static final String FUNC_TOKENURI = "tokenURI";
//
//    public static final String FUNC_TRANSFERFROM = "transferFrom";
//
//    public static final Event CREATENFT_EVEN_EVENT = new Event("createNFT_even",
//            Arrays.<TypeReference<?>>asList(),
//            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
//    ;
//
//    public static final Event GETADDRESSBYID_EVEN_EVENT = new Event("getAddressByID_even",
//            Arrays.<TypeReference<?>>asList(),
//            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
//    ;
//
//    public static final Event GETURI_EVEN_EVENT = new Event("getURI_even",
//            Arrays.<TypeReference<?>>asList(),
//            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
//    ;
//
//    public static final Event IPFSLINKADD_EVEN_EVENT = new Event("ipfsLinkAdd_even",
//            Arrays.<TypeReference<?>>asList(),
//            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
//    ;
//
//    public static final Event TRANSFER_EVEN_EVENT = new Event("transfer_even",
//            Arrays.<TypeReference<?>>asList(),
//            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
//    ;
//
//    // 由 protected 改为 public
//    public Erc_sol_ERC721Contract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
//        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
//    }
//
//    protected Erc_sol_ERC721Contract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
//        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
//    }
//
//    public List<CreateNFT_evenEventResponse> getCreateNFT_evenEvents(TransactionReceipt transactionReceipt) {
//        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CREATENFT_EVEN_EVENT, transactionReceipt);
//        ArrayList<CreateNFT_evenEventResponse> responses = new ArrayList<CreateNFT_evenEventResponse>(valueList.size());
//        for (EventValuesWithLog eventValues : valueList) {
//            CreateNFT_evenEventResponse typedResponse = new CreateNFT_evenEventResponse();
//            typedResponse.log = eventValues.getLog();
//            typedResponse.data = (Utf8String) eventValues.getNonIndexedValues().get(0);
//            responses.add(typedResponse);
//        }
//        return responses;
//    }
//
//    public Observable<CreateNFT_evenEventResponse> createNFT_evenEventObservable(EthFilter filter) {
//        return web3j.ethLogObservable(filter).map(new Func1<Log, CreateNFT_evenEventResponse>() {
//            @Override
//            public CreateNFT_evenEventResponse call(Log log) {
//                EventValuesWithLog eventValues = extractEventParametersWithLog(CREATENFT_EVEN_EVENT, log);
//                CreateNFT_evenEventResponse typedResponse = new CreateNFT_evenEventResponse();
//                typedResponse.log = log;
//                typedResponse.data = (Utf8String) eventValues.getNonIndexedValues().get(0);
//                return typedResponse;
//            }
//        });
//    }
//
//    public Observable<CreateNFT_evenEventResponse> createNFT_evenEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
//        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
//        filter.addSingleTopic(EventEncoder.encode(CREATENFT_EVEN_EVENT));
//        return createNFT_evenEventObservable(filter);
//    }
//
//    public List<GetAddressByID_evenEventResponse> getGetAddressByID_evenEvents(TransactionReceipt transactionReceipt) {
//        List<EventValuesWithLog> valueList = extractEventParametersWithLog(GETADDRESSBYID_EVEN_EVENT, transactionReceipt);
//        ArrayList<GetAddressByID_evenEventResponse> responses = new ArrayList<GetAddressByID_evenEventResponse>(valueList.size());
//        for (EventValuesWithLog eventValues : valueList) {
//            GetAddressByID_evenEventResponse typedResponse = new GetAddressByID_evenEventResponse();
//            typedResponse.log = eventValues.getLog();
//            typedResponse.date = (DynamicBytes) eventValues.getNonIndexedValues().get(0);
//            responses.add(typedResponse);
//        }
//        return responses;
//    }
//
//    public Observable<GetAddressByID_evenEventResponse> getAddressByID_evenEventObservable(EthFilter filter) {
//        return web3j.ethLogObservable(filter).map(new Func1<Log, GetAddressByID_evenEventResponse>() {
//            @Override
//            public GetAddressByID_evenEventResponse call(Log log) {
//                EventValuesWithLog eventValues = extractEventParametersWithLog(GETADDRESSBYID_EVEN_EVENT, log);
//                GetAddressByID_evenEventResponse typedResponse = new GetAddressByID_evenEventResponse();
//                typedResponse.log = log;
//                typedResponse.date = (DynamicBytes) eventValues.getNonIndexedValues().get(0);
//                return typedResponse;
//            }
//        });
//    }
//
//    public Observable<GetAddressByID_evenEventResponse> getAddressByID_evenEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
//        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
//        filter.addSingleTopic(EventEncoder.encode(GETADDRESSBYID_EVEN_EVENT));
//        return getAddressByID_evenEventObservable(filter);
//    }
//
//    public List<GetURI_evenEventResponse> getGetURI_evenEvents(TransactionReceipt transactionReceipt) {
//        List<EventValuesWithLog> valueList = extractEventParametersWithLog(GETURI_EVEN_EVENT, transactionReceipt);
//        ArrayList<GetURI_evenEventResponse> responses = new ArrayList<GetURI_evenEventResponse>(valueList.size());
//        for (EventValuesWithLog eventValues : valueList) {
//            GetURI_evenEventResponse typedResponse = new GetURI_evenEventResponse();
//            typedResponse.log = eventValues.getLog();
//            typedResponse.data = (Utf8String) eventValues.getNonIndexedValues().get(0);
//            responses.add(typedResponse);
//        }
//        return responses;
//    }
//
//    public Observable<GetURI_evenEventResponse> getURI_evenEventObservable(EthFilter filter) {
//        return web3j.ethLogObservable(filter).map(new Func1<Log, GetURI_evenEventResponse>() {
//            @Override
//            public GetURI_evenEventResponse call(Log log) {
//                EventValuesWithLog eventValues = extractEventParametersWithLog(GETURI_EVEN_EVENT, log);
//                GetURI_evenEventResponse typedResponse = new GetURI_evenEventResponse();
//                typedResponse.log = log;
//                typedResponse.data = (Utf8String) eventValues.getNonIndexedValues().get(0);
//                return typedResponse;
//            }
//        });
//    }
//
//    public Observable<GetURI_evenEventResponse> getURI_evenEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
//        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
//        filter.addSingleTopic(EventEncoder.encode(GETURI_EVEN_EVENT));
//        return getURI_evenEventObservable(filter);
//    }
//
//    public List<IpfsLinkAdd_evenEventResponse> getIpfsLinkAdd_evenEvents(TransactionReceipt transactionReceipt) {
//        List<EventValuesWithLog> valueList = extractEventParametersWithLog(IPFSLINKADD_EVEN_EVENT, transactionReceipt);
//        ArrayList<IpfsLinkAdd_evenEventResponse> responses = new ArrayList<IpfsLinkAdd_evenEventResponse>(valueList.size());
//        for (EventValuesWithLog eventValues : valueList) {
//            IpfsLinkAdd_evenEventResponse typedResponse = new IpfsLinkAdd_evenEventResponse();
//            typedResponse.log = eventValues.getLog();
//            typedResponse.data = (Utf8String) eventValues.getNonIndexedValues().get(0);
//            responses.add(typedResponse);
//        }
//        return responses;
//    }
//
//    public Observable<IpfsLinkAdd_evenEventResponse> ipfsLinkAdd_evenEventObservable(EthFilter filter) {
//        return web3j.ethLogObservable(filter).map(new Func1<Log, IpfsLinkAdd_evenEventResponse>() {
//            @Override
//            public IpfsLinkAdd_evenEventResponse call(Log log) {
//                EventValuesWithLog eventValues = extractEventParametersWithLog(IPFSLINKADD_EVEN_EVENT, log);
//                IpfsLinkAdd_evenEventResponse typedResponse = new IpfsLinkAdd_evenEventResponse();
//                typedResponse.log = log;
//                typedResponse.data = (Utf8String) eventValues.getNonIndexedValues().get(0);
//                return typedResponse;
//            }
//        });
//    }
//
//    public Observable<IpfsLinkAdd_evenEventResponse> ipfsLinkAdd_evenEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
//        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
//        filter.addSingleTopic(EventEncoder.encode(IPFSLINKADD_EVEN_EVENT));
//        return ipfsLinkAdd_evenEventObservable(filter);
//    }
//
//    public List<Transfer_evenEventResponse> getTransfer_evenEvents(TransactionReceipt transactionReceipt) {
//        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVEN_EVENT, transactionReceipt);
//        ArrayList<Transfer_evenEventResponse> responses = new ArrayList<Transfer_evenEventResponse>(valueList.size());
//        for (EventValuesWithLog eventValues : valueList) {
//            Transfer_evenEventResponse typedResponse = new Transfer_evenEventResponse();
//            typedResponse.log = eventValues.getLog();
//            typedResponse.date = (Utf8String) eventValues.getNonIndexedValues().get(0);
//            responses.add(typedResponse);
//        }
//        return responses;
//    }
//
//    public Observable<Transfer_evenEventResponse> transfer_evenEventObservable(EthFilter filter) {
//        return web3j.ethLogObservable(filter).map(new Func1<Log, Transfer_evenEventResponse>() {
//            @Override
//            public Transfer_evenEventResponse call(Log log) {
//                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVEN_EVENT, log);
//                Transfer_evenEventResponse typedResponse = new Transfer_evenEventResponse();
//                typedResponse.log = log;
//                typedResponse.date = (Utf8String) eventValues.getNonIndexedValues().get(0);
//                return typedResponse;
//            }
//        });
//    }
//
//    public Observable<Transfer_evenEventResponse> transfer_evenEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
//        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
//        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVEN_EVENT));
//        return transfer_evenEventObservable(filter);
//    }
//
//    public RemoteCall<TransactionReceipt> URIbyID(Int256 param0) {
//        final Function function = new Function(
//                FUNC_URIBYID,
//                Arrays.<Type>asList(param0),
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }
//
//    public RemoteCall<TransactionReceipt> createNFT(Int256 id, Utf8String ipfsLink) {
//        final Function function = new Function(
//                FUNC_CREATENFT,
//                Arrays.<Type>asList(id, ipfsLink),
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }
//
//    public RemoteCall<TransactionReceipt> getAddressByID(Int256 id) {
//        final Function function = new Function(
//                FUNC_GETADDRESSBYID,
//                Arrays.<Type>asList(id),
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }
//
//    public RemoteCall<TransactionReceipt> ipfsByAdd(Int256 param0) {
//        final Function function = new Function(
//                FUNC_IPFSBYADD,
//                Arrays.<Type>asList(param0),
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }
//
//    public RemoteCall<TransactionReceipt> ipfsLinkAdd(Int256 id, Address add) {
//        final Function function = new Function(
//                FUNC_IPFSLINKADD,
//                Arrays.<Type>asList(id, add),
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }
//
//    public RemoteCall<TransactionReceipt> name() {
//        final Function function = new Function(
//                FUNC_NAME,
//                Arrays.<Type>asList(),
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }
//
//    public RemoteCall<TransactionReceipt> symbol() {
//        final Function function = new Function(
//                FUNC_SYMBOL,
//                Arrays.<Type>asList(),
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }
//
//    public RemoteCall<TransactionReceipt> tokenURI(Int256 id) {
//        final Function function = new Function(
//                FUNC_TOKENURI,
//                Arrays.<Type>asList(id),
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }
//
//    public RemoteCall<TransactionReceipt> transferFrom(Address from, Address to, Int256 tokenId) {
//        final Function function = new Function(
//                FUNC_TRANSFERFROM,
//                Arrays.<Type>asList(from, to, tokenId),
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }
//
//    public static RemoteCall<Erc_sol_ERC721Contract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
//        return deployRemoteCall(Erc_sol_ERC721Contract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
//    }
//
//    public static RemoteCall<Erc_sol_ERC721Contract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
//        return deployRemoteCall(Erc_sol_ERC721Contract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
//    }
//
//    public static Erc_sol_ERC721Contract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
//        return new Erc_sol_ERC721Contract(contractAddress, web3j, credentials, gasPrice, gasLimit);
//    }
//
//    public static Erc_sol_ERC721Contract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
//        return new Erc_sol_ERC721Contract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
//    }
//
//    public static class CreateNFT_evenEventResponse {
//        public Log log;
//
//        public Utf8String data;
//    }
//
//    public static class GetAddressByID_evenEventResponse {
//        public Log log;
//
//        public DynamicBytes date;
//    }
//
//    public static class GetURI_evenEventResponse {
//        public Log log;
//
//        public Utf8String data;
//    }
//
//    public static class IpfsLinkAdd_evenEventResponse {
//        public Log log;
//
//        public Utf8String data;
//    }
//
//    public static class Transfer_evenEventResponse {
//        public Log log;
//
//        public Utf8String date;
//    }
//}
