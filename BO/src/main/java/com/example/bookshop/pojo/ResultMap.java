package com.example.bookshop.pojo;/**
 * @Auther: shu'ai
 * @Date: 2021/10/12 10:29
 * @Description:
 */

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName:resultMap
 * @Auther: shu'ai
 * @Description: 统一返回集合，利用泛型保证返回值的同一
 * @Date: 2021/10/12 10:29
 * @Version: v1.0
 */
@Data
public class ResultMap<T> implements Serializable {
    /**
     * 返回的编码，具体会出一个返回编码表
     */
    private Integer code ;
    /**
     *返回结果  成功  失败
     */
    private boolean flag;
    /**
     *返回的信息
     */
    private String message;
    /**
     * 返回数据
     */
    private  T data;

    public ResultMap(){

    }

    public ResultMap(boolean flag, Integer code , String message, Object data){
        this.code = code;
        this.flag = flag;
        this.message =message;
        this.data = (T)data;
    }
    public ResultMap(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
}
