package com.example.bookshop.pojo;/**
 * @Auther: shu'ai
 * @Date: 2021/10/20 14:15
 * @Description:
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName:book
 * @Auther: shu'ai
 * @Description: 图书实体类
 * @Date: 2021/10/20 14:15
 * @Version: v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("book")
public class Book implements Serializable {
    //商品：id==书号，书名，作者，出版社，印次，价格，图片，库存，商铺，分类，内容简介，备用字段
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String bookname;
    private String author;
    private String publishing;
    private Float price;
    private String log;
    private Integer inventory;  //库存
    private Integer shopId;
    private Integer saleNum;
    private String profile; //简介
    private Integer typeId;

}
