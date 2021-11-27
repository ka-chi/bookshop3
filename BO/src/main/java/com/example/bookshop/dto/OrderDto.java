package com.example.bookshop.dto;/**
 * @Auther: shu'ai
 * @Date: 2021/10/30 15:44
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName:orderDto
 * @Auther: shu'ai
 * @Description:
 * @Date: 2021/10/30 15:44
 * @Version: v1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto implements Serializable {
    Integer id;
    String orderNo;
    Integer cId;
    Integer bookId;
    Integer num;
    String bookname;
    String address;
    Float price;
    Integer shopId;
    String author;
    String log;
    String shopName;
    String payTime;
    Float sumMoney;
    String status;
    String tel;
    String custName;
    Integer isComment;  //0表示未评价  1表示已评价
}
