package com.example.bookshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookshop.dto.ShopcarDto;
import com.example.bookshop.pojo.Shopcar;
import com.example.bookshop.vo.ShopcarVO;

import java.util.List;

/**
 * @Auther: shu'ai
 * @Date: 2021/10/26 13:48
 * @Description:
 */
public interface shopcarService extends IService<Shopcar> {

    /**
     * 这个意义上的增只是在图书浏览界面的添加到购物车
     * @param
     */
    void add(ShopcarVO shopcarVO);

    /**
     * 需要前端提供图书id
     * @param
     */
    void delete(Integer bookId);

    /**
     * 这里的操作是在购物车界面增加数量或减少数量的更新
     * 前端做出延迟加载，，订单提交的时候做出数据删除
     * @param
     */
    void update(ShopcarVO shopcarVO);

    /**
     * 批量删除
     */
    void deleteByList(List<Integer> idlist);

    /**
     * 先查出所有的购物车数据，再根据shopid分别装入集合
     * @return
     */
    List<ShopcarDto>getAll(Integer custid);
}
