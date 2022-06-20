package com.example.bookshop.vo;/**
 * @Auther: shu'ai
 * @Date: 2021/11/7 22:54
 * @Description:
 */

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName:shopcarVO
 * @Auther: shu'ai
 * @Description:
 * @Date: 2021/11/7 22:54
 * @Version: v1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopcarVO {
    Integer bookid;
    Integer custid;
    Integer num;
}
