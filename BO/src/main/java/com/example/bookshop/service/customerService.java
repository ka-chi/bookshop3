package com.example.bookshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookshop.pojo.Customer;
import com.example.bookshop.vo.LoginVO;
import com.example.bookshop.vo.CustomerVO;

/**
 * @Auther: shu'ai
 * @Date: 2021/10/16 15:16
 * @Description:
 */
public interface customerService extends IService<Customer> {
    /**
     * 修改顾客或者商家的密码
     */
     void changePassword(LoginVO login);

    /**
     * 注册
     * @param CustomerVO
     */
     void register(CustomerVO CustomerVO);

    /**
     * 更改收获地址
     */
    void updateAddress(String address);

    void updateMoney(Float money);
}
