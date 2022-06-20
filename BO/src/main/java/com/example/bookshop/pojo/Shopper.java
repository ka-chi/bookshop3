package com.example.bookshop.pojo;/**
 * @Auther: shu'ai
 * @Date: 2021/10/15 16:08
 * @Description:
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

/**
 * @ClassName:shopper
 * @Auther: shu'ai
 * @Description: 商家基础类
 * @Date: 2021/10/15 16:08
 * @Version: v1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("shopper")
public class Shopper implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;
    private String password;
    private String shopName;
    private String shopAddress;
    private String type;
    private float registerMoney;
    private String tel;
    private String log;//标志
    /**
     * 审核状态
     */
    private String checkStatus;
    private String registerTime;
    private Integer authentic;

}
