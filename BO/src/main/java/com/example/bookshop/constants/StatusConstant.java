package com.example.bookshop.constants;/**
 * @Auther: shu'ai
 * @Date: 2021/10/15 14:07
 * @Description:
 */

/**
 * @ClassName:StatusConstant
 * @Auther: shu'ai
 * @Description: 自定义状态码
 * @Date: 2021/10/15 14:07
 * @Version: v1.0
 */

public class StatusConstant {

    /**
     * 成功
     */
    public static final int OK = 20000;

    /**
     * 失败
     */
    public static final int ERROR = 20001;

    /**
     * 系统异常
     */
    public static final int SYSTEM_ERROR = 50000;

    /**
     * 未登录
     */
    public static final int NOT_LOGIN = 40001;

    /**
     * 没有操作权限
     */
    public static final int AUTHORIZED = 40003;

}