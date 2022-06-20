package com.example.bookshop.pojo;/**
 * @Auther: shu'ai
 * @Date: 2021/10/14 14:21
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

/**
 * @ClassName:login
 * @Auther: shu'ai
 * @Description: 登录表
 * @Date: 2021/10/14 14:21
 * @Version: v1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("login")
public class Login implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
        private Integer id;

        private  String username;
        private String password;
        private Integer c_id;  //顾客id
        private Integer s_id;  //商家id
        private Integer authentic;
}
