package com.example.bookshop.vo;/**
 * @Auther: shu'ai
 * @Date: 2021/11/8 22:59
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName:OrderVO
 * @Auther: shu'ai
 * @Description:
 * @Date: 2021/11/8 22:59
 * @Version: v1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {
    String status;
    Integer custId;

}
