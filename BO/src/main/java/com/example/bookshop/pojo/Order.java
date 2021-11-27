package com.example.bookshop.pojo;/**
 * @Auther: shu'ai
 * @Date: 2021/10/29 14:18
 * @Description:
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName:orderDetailed
 * @Auther: shu'ai
 * @Description: 详细子订单
 * @Date: 2021/10/29 14:18
 * @Version: v1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("orders")
public class Order implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    Integer cId;
    Integer bookId;
    Integer num;
    //String bookName;
    String address;
    //Float price;
    Integer shopId;
    //String author;
    //String log;
    //String shopName;
    String status;   //1已发货，2未发货
    String orderNo;
    String payTime;
    Float sumMoney;
}
