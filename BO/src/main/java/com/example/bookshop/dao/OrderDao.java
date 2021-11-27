package com.example.bookshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bookshop.dto.OrderDto;
import com.example.bookshop.pojo.Order;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: shu'ai
 * @Date: 2021/10/26 13:37
 * @Description:
 */
@Repository
public interface OrderDao extends BaseMapper<Order> {
    //把ostatus转变为status
    void updateState(@Param("status") String status ,@Param("orderNo") String orderNo);

//    void updateState2(@Param("status") String status ,@Param("orderNo") String orderNo);

    List<Order> selectByCid(Integer cid);

    void insertList(@Param("list") List<Order> list);

    List<OrderDto> selectByCustomer(@Param("cid") Integer cid, @Param("status") String status);

    List<OrderDto> selectByShopper(@Param("sid") Integer sid, @Param("status") String status);

    Float getSum(@Param("cid") Integer cid);

    List<Order> selectByOrderNo(String orderNo);


    void updateAddress(@Param("orderNo") String orderNo, @Param("address") String address);
}
