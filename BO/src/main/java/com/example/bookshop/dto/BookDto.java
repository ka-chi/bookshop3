package com.example.bookshop.dto;/**
 * @Auther: shu'ai
 * @Date: 2021/10/21 11:13
 * @Description:
 */

import com.example.bookshop.pojo.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName:BookVO
 * @Auther: shu'ai
 * @Description: 增加图书时的增强类
 * @Date: 2021/10/21 11:13
 * @Version: v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto implements Serializable {

    private Integer id;
    private String bookname;
    private String author;
    private String publishing;
    private Float price;
    private String log;
    private Integer inventory;  //库存
    private String shopname;
    private Integer saleNum;

    private String profile; //简介

    private String typename;  //类型名

}
