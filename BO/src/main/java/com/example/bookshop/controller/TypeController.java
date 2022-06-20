package com.example.bookshop.controller;/**
 * @Auther: shu'ai
 * @Date: 2021/10/24 22:09
 * @Description:
 */



import com.example.bookshop.constants.StatusConstant;
import com.example.bookshop.pojo.ResultMap;
import com.example.bookshop.pojo.Type;
import com.example.bookshop.service.impl.typeServiceImpl;
import com.example.bookshop.vo.TypeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:TypeController
 * @Auther: shu'ai
 * @Description: 图书分类的操作
 * @Date: 2021/10/24 22:09
 * @Version: v1.0
 */

@RestController
@RequestMapping("/type")
@Api("分类控制层")
public class TypeController {

    @Autowired
    typeServiceImpl typeService;

    @ApiOperation("添加分类")
    @PostMapping("/add")
    ResultMap<?> add(@RequestBody Type type){
        typeService.add(type);
        return new ResultMap<>(true, StatusConstant.OK,"标签添加成功");
    }
    @ApiOperation("删除分类")
    @GetMapping("/delete/{id}")
    ResultMap<?> delete(@PathVariable Integer id){
        typeService.delete(id);
        return new ResultMap<>(true, StatusConstant.OK,"标签删除成功");
    }


    /**
     * 通过分类名获取子级分类，
     */
    @ApiOperation("获取子分类")
    @GetMapping("/getChildType")
    ResultMap<List<Type>> getChildType(String typename){
        return new ResultMap<>(true, StatusConstant.OK,"取值成功",typeService.getChildType(typename));
    }

    @ApiOperation("通过分类级别获取各级分类")
    @GetMapping("/getTypeByLevel/{level}")
    ResultMap<List<Type>> getTypeByLevel(@PathVariable("level") Integer level){
        return new ResultMap<>(true, StatusConstant.OK,"取值成功",typeService.getByLevel(level));
    }

    @GetMapping("/getAllType")
    ResultMap<List<TypeVO>> getAllType(){
        return new ResultMap<>(true, StatusConstant.OK,"取值成功",typeService.getAllType());
    }


}
