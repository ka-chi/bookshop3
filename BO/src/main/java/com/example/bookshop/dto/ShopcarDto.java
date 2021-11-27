package com.example.bookshop.dto;/**
 * @Auther: shu'ai
 * @Date: 2021/10/26 14:08
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName:shopcarVO
 * @Auther: shu'ai
 * @Description: 接受前端购物车数据
 * @Date: 2021/10/26 14:08
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopcarDto implements Serializable {
    Integer id;
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
    Float goodTotal;
}
