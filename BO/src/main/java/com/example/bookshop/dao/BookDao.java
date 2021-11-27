package com.example.bookshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bookshop.dto.BookDto;
import com.example.bookshop.pojo.Book;
import com.example.bookshop.vo.BookVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: shu'ai
 * @Date: 2021/10/21 14:36
 * @Description:
 */
@Repository
public interface BookDao extends BaseMapper<Book> {

    List<BookDto> queryByPrice(Float begin, Float end);

    List<BookDto> queryByBookname(String bookname);

    List<BookVo> queryByShopper(Integer id);

    List<BookDto> queryHome();

    List<BookDto> queryByTypename1(String typename);
    List<BookDto> queryByTypename2(String typename);
    List<BookDto> queryByTypename3(String typename);
}
