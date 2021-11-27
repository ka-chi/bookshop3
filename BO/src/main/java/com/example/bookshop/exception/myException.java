package com.example.bookshop.exception;


import java.io.Serializable;

/**
 * @ClassName:myException
 * @Auther: shu'ai
 * @Description: 自定义异常
 * @Date: 2021/10/15 14:21
 * @Version: v1.0
 */

public class myException extends RuntimeException implements Serializable {
    public  myException(String message){
        super(message);
    }
}
