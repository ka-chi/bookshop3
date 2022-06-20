package com.example.bookshop.vo;/**
 * @Auther: shu'ai
 * @Date: 2021/10/16 16:16
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName:LoginVO
 * @Auther: shu'ai
 * @Description: 加强登录类
 * @Date: 2021/10/16 16:16
 * @Version: v1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginVO implements Serializable {
    private  String username;
    private String password;
    private String newPassword;

}
