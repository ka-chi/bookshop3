package com.example.bookshop.pojo;/**
 * @Auther: shu'ai
 * @Date: 2021/10/12 16:36
 * @Description:
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName:customer
 * @Auther: shu'ai
 * @Description: 顾客 基础类
 * @Date: 2021/10/12 16:36
 * @Version: v1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("customer")
public class Customer implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private  Integer id ;

    private String name;

    private String sex;

    private String username;

    private String password;

    private String tel;

    private String address;

    /**
     * 钱包余额
     */
    private float money;

    private String registerTime;
    /**
     * 权限
     */
    private Integer authentic;


}
