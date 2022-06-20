package com.example.bookshop.controller;/**
 * @Auther: shu'ai
 * @Date: 2021/10/31 12:54
 * @Description:
 */

import com.example.bookshop.constants.CommonConstant;
import com.example.bookshop.constants.StatusConstant;
import com.example.bookshop.dto.CommentDto;
import com.example.bookshop.pojo.Comment;
import com.example.bookshop.pojo.ResultMap;
import com.example.bookshop.service.impl.commentServiceImpl;
import com.example.bookshop.vo.CommentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName:CommentController
 * @Auther: shu'ai
 * @Description: 评论系统
 * @Date: 2021/10/31 12:54
 * @Version: v1.0
 */
@Api("评论模块")
@RequestMapping("/comment")
@RestController
public class CommentController {

    @Autowired
    commentServiceImpl commentService;
    /**
     * 添加评论
     */
    @ApiOperation("通过订单添加评论到图书")
    @PostMapping("/addComment")
    ResultMap<?> addComment(@RequestBody CommentVO commentVO){
            commentService.addComment(commentVO);
        return new ResultMap<>(true, StatusConstant.OK,"添加评论成功");
    }


    @ApiOperation("展示评论")
    @GetMapping("/showComment/{bookId}")
    ResultMap<List<CommentDto>> showComment(@PathVariable("bookId") Integer bookId){
        return new ResultMap<>(true, StatusConstant.OK,"搜索评论成功",commentService.showComment(bookId));
    }


}
