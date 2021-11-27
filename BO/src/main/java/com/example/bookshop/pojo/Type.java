package com.example.bookshop.pojo;/**
 * @Auther: shu'ai
 * @Date: 2021/10/21 14:01
 * @Description:
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName:Type
 * @Auther: shu'ai
 * @Description: 图书分类
 * @Date: 2021/10/21 14:01
 * @Version: v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("type")
public class Type implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private  Integer parentId;

    private String typename;

    /**
     * 分类等级
     */
    private Integer level;

}
