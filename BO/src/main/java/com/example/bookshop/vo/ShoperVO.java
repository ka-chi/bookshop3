package com.example.bookshop.vo;/**
 * @Auther: shu'ai
 * @Date: 2021/11/1 12:52
 * @Description:
 */

import com.example.bookshop.constants.regexpConstant;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Default;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @ClassName:ShoperVO
 * @Auther: shu'ai
 * @Description: 商家注册
 * @Date: 2021/11/1 12:52
 * @Version: v1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoperVO {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "商铺名不能为空")
    private String shopName;

    @NotBlank(message = "商铺地址不能为空")
    private String shopAddress;

    @NotBlank(message = "经营类型不能为空")
    private String type;

    @NotBlank(message = "注册资金不能为空")
    private float registerMoney;

    @NotBlank(message = "电话不能为空")
    @Pattern(regexp = regexpConstant.MOBILE_PATTERN,message = ("电话不合法"))
    private String tel;

}
