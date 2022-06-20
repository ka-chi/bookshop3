package com.example.bookshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookshop.pojo.Type;
import com.example.bookshop.vo.TypeVO;

import java.util.List;
import java.util.Map;

/**
 * @Auther: shu'ai
 * @Date: 2021/10/24 22:56
 * @Description:
 */
public interface typeService extends IService<Type> {

    /**
     * 新增各级分类--传入实体类type包括分级
     */
    void add(Type type);

    /**
     * 删除某个分类
     */
    void delete(Integer id);



    List<Type> getByLevel(Integer level);



    /**
     * 触摸一级菜单响应二级菜单----也考虑在前端写死保证响应界面速度
     */
    List<Type> getChildType(String typename);

    /**
     * 获取父级分类
     */
//    List<Type> getFatherType(String typename);

    List<TypeVO> getAllType();


}
