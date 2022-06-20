package com.example.bookshop.vo;/**
 * @Auther: shu'ai
 * @Date: 2021/11/1 16:23
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName:BookVo
 * @Auther: shu'ai
 * @Description:
 * @Date: 2021/11/1 16:23
 * @Version: v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookVo {

    private Integer id;
    private String bookname;
    private String author;
    private String publishing;
    private Float price;
    private String log;
    private Integer inventory;  //库存

    private String profile; //简介

    private String typename;  //类型名字
}
