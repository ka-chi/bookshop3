package com.example.bookshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bookshop.dto.CommentDto;
import com.example.bookshop.pojo.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther: shu'ai
 * @Date: 2021/10/31 13:16
 * @Description:
 */
@Repository
public interface CommenDao extends BaseMapper<Comment> {
    List<CommentDto> showComment(Integer bookId);
}
