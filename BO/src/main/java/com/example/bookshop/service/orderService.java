package com.example.bookshop.service;/**
 * @Auther: shu'ai
 * @Date: 2021/10/26 14:52
 * @Description:
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookshop.dto.OrderDto;
import com.example.bookshop.dto.PageDto;
import com.example.bookshop.dto.ShopcarDto;
import com.example.bookshop.pojo.Order;
import com.example.bookshop.vo.OrderVO;

import java.util.List;

/**
 * @ClassName:orderService
 * @Auther: shu'ai
 * @Description: 订单
 * @Date: 2021/10/26 14:52
 * @Version: v1.0
 */

public interface orderService extends IService<Order> {

    /**
     * 提交购物车到订单--先到有半小时时间的缓存----过期将状态改为取消写入订单库---完成写入订单库
     */
    void sendToOrder(List<ShopcarDto> list);

    /**
     * 支付订单--输入订单编号
     */
    Float payOrder(String orderNo);

    /**
     * 取消订单--输入订单编号
     */
    void cancelOrder(String orderNo);

    /**
     * 修改地址
     */
    void updateAddress(String orderNo, String address);

//    /**
//     * 查询该用户订单
//     */

    List<PageDto<OrderDto>>selectByCustomer(OrderVO orderVO);

    List<PageDto<OrderDto>> selectByShopper(OrderVO orderVO);

    /**
     * 商家发货
     */
    void sendBooks(String orderNos);

    void afterGetBook(String orderNo);
}
