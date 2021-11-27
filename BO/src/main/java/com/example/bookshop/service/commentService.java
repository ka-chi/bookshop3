package com.example.bookshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookshop.dto.CommentDto;
import com.example.bookshop.pojo.Comment;
import com.example.bookshop.vo.CommentVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther: shu'ai
 * @Date: 2021/10/31 13:32
 * @Description:
 */
public interface commentService extends IService<Comment> {

    void addComment( CommentVO commentVO);

    List<CommentDto> showComment(Integer bookId);
}
