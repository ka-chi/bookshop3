package com.example.bookshop.vo;/**
 * @Auther: shu'ai
 * @Date: 2021/11/5 17:39
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName:typeVO
 * @Auther: shu'ai
 * @Description:
 * @Date: 2021/11/5 17:39
 * @Version: v1.0
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeVO {
    Integer id;
    Integer parent_id;
    String label;
    List<TypeVO> children;
}
