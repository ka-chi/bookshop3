package com.example.bookshop.controller;/**
 * @Auther: shu'ai
 * @Date: 2021/10/18 15:23
 * @Description:
 */

import com.example.bookshop.constants.StatusConstant;
import com.example.bookshop.dto.PageDto;
import com.example.bookshop.pojo.Shopper;
import com.example.bookshop.pojo.ResultMap;
import com.example.bookshop.service.impl.ShopperServiceImpl;
import com.example.bookshop.vo.ShoperVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName:ShopController
 * @Auther: shu'ai
 * @Description: 商家操作
 * @Date: 2021/10/18 15:23
 * @Version: v1.0
 */
@Api("商家模块")
@RestController
public class ShopController {
    @Autowired
    private ShopperServiceImpl shopperService;


    @ApiOperation("商家注册")
    @PostMapping("/Sregister")
    public ResultMap<?> register(@RequestBody ShoperVO shopper){
        shopperService.Sregister(shopper);
        return new ResultMap<>(true, StatusConstant.OK,"注册成功");
    }
    /**
     * 管理员审核商家
     * 前端传两个值，商家id，审核结果
     * @return
     */
    @ApiOperation("管理员审核商家")
    @GetMapping("/checkShop/{id}/{result}")
    public ResultMap<?> checkShop(@PathVariable("id") Integer id, @PathVariable("result") String result){
        shopperService.checkShop(id,result);
        return new ResultMap<>(true, StatusConstant.OK,"审核完成");
    }

    /**
     * 管理员查看待审核的商家
     */
    @ApiOperation("管理员查看待审核商家")
    @GetMapping("/queryUnchecked")
    public ResultMap<PageDto<Shopper>> queryUnchecked(
            @RequestParam(defaultValue = "1")Integer PageNo,
            @RequestParam(defaultValue = "5")Integer PageSize){
        return new ResultMap<>(true, StatusConstant.OK,"查询成功",shopperService.queryUnchecked(PageNo, PageSize));
    }

    @ApiOperation("管理员查看正式商家")
    @GetMapping("/getShopper")
    public ResultMap<List<Shopper>> getShopper(){
        return new ResultMap<>(true, StatusConstant.OK,"查询成功",shopperService.getShopper());
    }

}
