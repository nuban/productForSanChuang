package com.blockchain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title
 * @description 返回前端的状态码
 * @author admin
 * @updateTime
 * @throws
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResBean {
    private long code;
    private String message;
    private Object obj;

    /**
     * @description 成功返回结果
     * @author admin
     */
    public static ResBean success(String message){
        return new ResBean(200,message,null);
    }


    public static ResBean success(String message,Object object){
        return new ResBean(200,message,object);
    }

    /**
     * @description 失败返回结果
     * @author admin
     */
    public static ResBean error(String message){
        return new ResBean(500,message,null);
    }

    public static ResBean error(String message,Object object){
        return new ResBean(500,message,object);
    }


}
