package com.example.bookshop.service.impl;/**
 * @Auther: shu'ai
 * @Date: 2021/10/31 13:33
 * @Description:
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookshop.dao.CommenDao;
import com.example.bookshop.dao.OrderDao;
import com.example.bookshop.dto.CommentDto;
import com.example.bookshop.pojo.Comment;
import com.example.bookshop.pojo.Order;
import com.example.bookshop.service.commentService;
import com.example.bookshop.vo.CommentVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName:commentServiceImpl
 * @Auther: shu'ai
 * @Description:
 * @Date: 2021/10/31 13:33
 * @Version: v1.0
 */
@Service
public class commentServiceImpl extends ServiceImpl<CommenDao, Comment> implements commentService {

    @Autowired
    OrderDao orderDao;
    @Autowired
    CommenDao commenDao;
    Logger logger = LogManager.getLogger(commentServiceImpl.class);

    @Override
    public void addComment(CommentVO commentVO) {
        logger.info("commentVO:"+commentVO);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Comment comment1 = Comment.builder()
                    .comment(commentVO.getComment())
                    .bookId(commentVO.getBookId())
                    .cId(commentVO.getCustId())
                    .cTime(simpleDateFormat.format(date))
                    .star(commentVO.getStar())
                    .build();
            commenDao.insert(comment1);

    }

    @Override
    public List<CommentDto> showComment(Integer bookId) {
        List<CommentDto> lists = commenDao.showComment(bookId);
        logger.info("评论"+lists.toString());
        return lists;
    }
}
