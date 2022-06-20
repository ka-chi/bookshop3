package com.example.bookshop.vo;/**
 * @Auther: shu'ai
 * @Date: 2021/11/7 02:54
 * @Description:
 */

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName:PageVO
 * @Auther: shu'ai
 * @Description:
 * @Date: 2021/11/7 02:54
 * @Version: v1.0
 */

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PageVO implements Serializable {
    Integer pageIndex;
    Integer pageSize;
    String typename;
    String condition;
}
