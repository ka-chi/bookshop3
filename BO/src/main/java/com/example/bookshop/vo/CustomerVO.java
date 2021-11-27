package com.example.bookshop.vo;/**
 * @Auther: shu'ai
 * @Date: 2021/10/15 16:06
 * @Description:
 */

import com.example.bookshop.constants.regexpConstant;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 * @ClassName:registerVO
 * @Auther: shu'ai
 * @Description: 用于注册使用的封装
 * @Date: 2021/10/15 16:06
 * @Version: v1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "注册")
public class CustomerVO implements Serializable {

    @NotBlank(message = "昵称不能为空")
    private String name;

    @NotBlank(message = "性别不能为空")
    private String sex;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "电话号码不能为空")
    @Pattern(regexp = regexpConstant.MOBILE_PATTERN,message = ("电话号码不合法"))
    private String tel;


}
