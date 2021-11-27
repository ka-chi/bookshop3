package com.example.bookshop.controller;/**
 * @Auther: shu'ai
 * @Date: 2021/10/26 13:35
 * @Description:
 */

import com.example.bookshop.constants.StatusConstant;
import com.example.bookshop.dto.OrderDto;
import com.example.bookshop.dto.PageDto;
import com.example.bookshop.dto.ShopcarDto;
import com.example.bookshop.pojo.ResultMap;
import com.example.bookshop.service.impl.orderServiceImpl;
import com.example.bookshop.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName:OrderController
 * @Auther: shu'ai
 * @Description: 订单模块
 * @Date: 2021/10/26 13:35
 * @Version: v1.0
 */
@Api("订单")
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    orderServiceImpl orderService;

    Logger logger = LogManager.getLogger(orderServiceImpl.class);

    @PostMapping("/send2order")
    @ApiOperation("下单")
    public ResultMap<?> send2order(@RequestBody List<ShopcarDto> list){
        orderService.sendToOrder(list);
        return new ResultMap<>(true, StatusConstant.OK,"下单成功");
    }

    @PostMapping("/payOrder/{orderNo}")
    @ApiOperation("付款")
    public ResultMap<Float> payOrder(@PathVariable("orderNo") String orderNo){
        return new ResultMap<>(true, StatusConstant.OK,"付款成功",orderService.payOrder(orderNo));
    }

    @PostMapping("/afterGetBook/{orderNo}")
    @ApiOperation("确认收货")
    public ResultMap<?> afterGetBook(@PathVariable("orderNo") String orderNo){
        orderService.afterGetBook(orderNo);
        return new ResultMap<>(true, StatusConstant.OK,"付款成功");
    }

    @PostMapping("/sendBooks/{orderNo}")
    @ApiOperation("商家发货")
    public ResultMap<?> sendBooks(@PathVariable("orderNo") String orderNo){
        orderService.sendBooks(orderNo);
        return new ResultMap<>(true, StatusConstant.OK,"取值成功");
    }

    @PostMapping("/cancelOrder/{orderNo}")
    @ApiOperation("取消订单")
    public ResultMap<?> cancelOrder(@PathVariable("orderNo") String orderNo){
        orderService.cancelOrder(orderNo);
        return new ResultMap<>(true, StatusConstant.OK,"取消订单");
    }

    @PostMapping("/selectByCustomer")
    @ApiOperation("顾客获取订单")
    public ResultMap<List<PageDto<OrderDto>>> selectByCustomer(@RequestBody OrderVO orderVO){
        return new ResultMap<>(true, StatusConstant.OK,"取值成功",orderService.selectByCustomer(orderVO));
    }

    @PostMapping("/selectByShopper")
    @ApiOperation("商家获取订单")
    public ResultMap<List<PageDto<OrderDto>>> selectByShopper(@RequestBody OrderVO orderVO){
        logger.info("orderController"+orderService.selectByShopper(orderVO));
        return new ResultMap<>(true, StatusConstant.OK,"取值成功",orderService.selectByShopper(orderVO));
    }



    @PostMapping("/updateAddress/{orderNo}/{address}")
    @ApiOperation("顾客改收货地址")
    public ResultMap<?> sendBooks(@PathVariable("orderNo") String orderNo,@PathVariable("address") String address){
        orderService.updateAddress(orderNo,address);
        return new ResultMap<>(true, StatusConstant.OK,"取值成功");
    }



}
