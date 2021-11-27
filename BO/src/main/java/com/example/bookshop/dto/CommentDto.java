package com.example.bookshop.dto;/**
 * @Auther: shu'ai
 * @Date: 2021/11/12 14:31
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName:CommentDto
 * @Auther: shu'ai
 * @Description:
 * @Date: 2021/11/12 14:31
 * @Version: v1.0
 */

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CommentDto {
    String custName;
    Integer bookId;
    String  comment;
    Integer star;
    String cTime;

}
