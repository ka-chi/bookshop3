package com.example.bookshop.dao;/**
 * @Auther: shu'ai
 * @Date: 2021/10/15 14:51
 * @Description:
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bookshop.pojo.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName:userDao
 * @Auther: shu'ai
 * @Description: 用户的数据操作  接口
 * @Date: 2021/10/15 14:51
 * @Version: v1.0
 */

@Repository
public interface CustomerDao extends BaseMapper<Customer> {

     void updateAddress(@Param("address") String address, @Param("id") Integer id);
}
