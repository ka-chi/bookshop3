package com.example.bookshop.controller;/**
 * @Auther: shu'ai
 * @Date: 2021/10/15 14:39
 * @Description:
 */


import com.example.bookshop.constants.StatusConstant;
import com.example.bookshop.pojo.ResultMap;
import com.example.bookshop.service.impl.customerServiceImpl;
import com.example.bookshop.vo.LoginVO;
import com.example.bookshop.vo.CustomerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @ClassName:userAuthController
 * @Auther: shu'ai
 * @Description: 用户的相关操作
 * @Date: 2021/10/15 14:39
 * @Version: v1.0
 */

@Api("用户模块")
@RestController
public class CustomerController {

    @Autowired
    private customerServiceImpl customerService;


    /**
     *
     * @return
     */
    @ApiOperation("顾客注册")
    @PostMapping("/Cregister")
    public ResultMap<?> register(@Valid @RequestBody CustomerVO customer){
        customerService.register(customer);
        return new ResultMap<>(true, StatusConstant.OK,"注册成功");
    }


    /**
     * 输入原有密码更改密码
     * @return
     */
    @ApiOperation("根据原有密码更改密码")
    @PostMapping("/changePassword")
    public ResultMap<?> changePassword(@RequestBody LoginVO login){
        customerService.changePassword(login);
        return new ResultMap<>(true, StatusConstant.OK,"密码更改成功成功");
    }

    /**
     * 根据短信修改密码
     */
    @PostMapping("/cpbp")
    public ResultMap<?> changePasswordByPhone(){

        return new ResultMap<>(true, StatusConstant.OK,"密码更改成功成功");
    }

    @PostMapping("/updateAddress/{address}")
    public ResultMap<?> updateAddress(@PathVariable("address") String address){
        customerService.updateAddress(address);
        return new ResultMap<>(true, StatusConstant.OK,"收获地址更改成功");
    }

    /**
     * 充值
     */
    @PostMapping("/recharge/{money}")
    public  ResultMap<?> recharge(@PathVariable("money")  Float money){
        customerService.updateMoney(money);
        return new ResultMap<>(true, StatusConstant.OK,"充值成功");
    }


}
