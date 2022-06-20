package com.example.bookshop.pojo;/**
 * @Auther: shu'ai
 * @Date: 2021/10/31 13:29
 * @Description:
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName:comment
 * @Auther: shu'ai
 * @Description: 评论
 * @Date: 2021/10/31 13:29
 * @Version: v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    String comment;
    Integer star;
    String cTime;
    Integer cId;
    Integer bookId;
}
