package com.example.bookshop.vo;/**
 * @Auther: shu'ai
 * @Date: 2021/11/12 11:16
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName:commentVO
 * @Auther: shu'ai
 * @Description:
 * @Date: 2021/11/12 11:16
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentVO {
    Integer bookId;
    Integer custId;
    Integer star;
    String comment;
}
