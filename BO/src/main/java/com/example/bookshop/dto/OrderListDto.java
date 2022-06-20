package com.example.bookshop.dto;/**
 * @Auther: shu'ai
 * @Date: 2021/11/9 10:38
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName:OrderListDto
 * @Auther: shu'ai
 * @Description:
 * @Date: 2021/11/9 10:38
 * @Version: v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListDto {
    List<List<OrderDto>> recordList;

    Float totalPrice;
}
