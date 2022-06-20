package com.example.bookshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bookshop.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: shu'ai
 * @Date: 2021/10/29 15:13
 * @Description:
 */
public interface OrderDetailedDao extends BaseMapper<Order> {
    void insertList(@Param("list") List<Order> list);
}
