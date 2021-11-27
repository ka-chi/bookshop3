package com.example.bookshop.pojo;/**
 * @Auther: shu'ai
 * @Date: 2021/10/26 13:38
 * @Description:
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName:Shopcar
 * @Auther: shu'ai
 * @Description: 购物车基类
 * @Date: 2021/10/26 13:38
 * @Version: v1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("shopcar")
public class Shopcar implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer cId;
    private Integer bookId;
    private Integer num;
    private float price;
}
