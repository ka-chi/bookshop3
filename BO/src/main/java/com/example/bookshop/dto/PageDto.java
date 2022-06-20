package com.example.bookshop.dto;/**
 * @Auther: shu'ai
 * @Date: 2021/10/16 19:35
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName:PageDto
 * @Auther: shu'ai
 * @Description: 分页、
 * @Date: 2021/10/16 19:35
 * @Version: v1.0
 */
@Data
@Builder
@AllArgsConstructor
public class PageDto<T> {

    /**
     * 分页列表
     */
    private List<T> recordList;

//    private List<List<T>> lists;
    /**
     * 总数
     */
    private Long count;
    /**
     * 计数用
     */
    private Integer pointer;
    /**
    本订单总价格
     */
    private Float sumMoney;

    private String address;

    private  String payTime;

    private String orderNo;

    public PageDto(List<T> list,Long c){
        this.recordList = list;
        this.count = c;
    }
    public PageDto(){
    }
}
