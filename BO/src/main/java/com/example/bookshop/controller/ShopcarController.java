package com.example.bookshop.controller;/**
 * @Auther: shu'ai
 * @Date: 2021/10/26 10:52
 * @Description:
 */

import com.example.bookshop.constants.StatusConstant;
import com.example.bookshop.dto.ShopcarDto;
import com.example.bookshop.pojo.ResultMap;

import com.example.bookshop.service.impl.bookServiceImpl;
import com.example.bookshop.service.impl.shopcarServiceImpl;
import com.example.bookshop.vo.ShopcarVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName:shopcarController
 * @Auther: shu'ai
 * @Description: 购物车
 * @Date: 2021/10/26 10:52
 * @Version: v1.0
 */

@RestController
@RequestMapping("/shopcar")
@Api("购物车模块")
public class ShopcarController {

    @Autowired
    shopcarServiceImpl shopcarService;

    Logger logger = LogManager.getLogger(bookServiceImpl.class);

    @PostMapping("/add")
    @ApiOperation("从浏览界面加入图书到购物车")
    public ResultMap<?> add(@RequestBody ShopcarVO shopcarVO){
        logger.info(shopcarVO.toString());
        shopcarService.add(shopcarVO);
        return new ResultMap<>(true, StatusConstant.OK,"购物车添加成功");
    }

    @DeleteMapping("/delete/{shopcarId}")
    @ApiOperation("在购物车界面删除商品")
    public ResultMap<?> delete(@PathVariable("shopcarId") Integer shopcarId){
        shopcarService.delete(shopcarId);
        return new ResultMap<>(true, StatusConstant.OK,"购物车删除成功");
    }

    @PostMapping("/update")
    @ApiOperation("从浏览界面加入图书到购物车")
    public ResultMap<?> update(@RequestBody ShopcarVO shopcarVO){
        shopcarService.update(shopcarVO);
        return new ResultMap<>(true, StatusConstant.OK,"购物车更新成功");
    }

    @GetMapping("/getALL/{custid}")
    @ApiOperation("获取当前顾客的所有购物车")
    public ResultMap<List<ShopcarDto>> getAll(@PathVariable("custid") Integer custid){
        return new ResultMap<>(true, StatusConstant.OK,"获取购物车成功",shopcarService.getAll(custid));
    }



}
