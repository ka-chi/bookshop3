package com.example.bookshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bookshop.dto.ShopcarDto;
import com.example.bookshop.pojo.Shopcar;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: shu'ai
 * @Date: 2021/10/26 13:36
 * @Description:
 */
@Repository
public interface ShopcarDao extends BaseMapper<Shopcar> {
    void updateOne(@Param("bookid") Integer bookid,@Param("cid") Integer cid,@Param("num") Integer num);

    List<ShopcarDto> getAll(@Param("c_id") Integer c_id);
}
