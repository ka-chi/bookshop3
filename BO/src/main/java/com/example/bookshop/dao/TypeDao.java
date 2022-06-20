package com.example.bookshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bookshop.pojo.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: shu'ai
 * @Date: 2021/10/24 16:42
 * @Description:
 */
@Repository
public interface TypeDao extends BaseMapper<Type> {
    List<Integer> selectBookByType(Integer id);

    Integer getLevel(String typename);

    List<Type> getChildType(String typename);

}
