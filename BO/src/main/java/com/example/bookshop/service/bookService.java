package com.example.bookshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.PageDto;
import com.example.bookshop.pojo.Book;
import com.example.bookshop.vo.BookVo;
import com.example.bookshop.vo.PageVO;


import java.util.List;

/**
 * @Auther: shu'ai
 * @Date: 2021/10/21 13:28
 * @Description:
 */
public interface bookService extends IService<Book> {
    /**
     * 增加新图书
     * @param
     */
    void add(Integer id,BookVo bookVO);

    /**
     * 删除图书
     */
    void deleteBook(Integer bookId);
    /**
     * 更新图书信息----考虑不做
     */
    void updateBook(BookDto bookVO);

    /**
     * 查询当前店铺上架的图书
     */
    List<BookVo> queryByShopper(Integer id);

    /**
     * 查询所有图书--供顾客浏览挑选
     */
    public PageDto<BookDto> queryByCustomer(PageVO pageVO);



    /**
     * 模糊查询图书
     */
    List<BookDto> queryByBookName(String bookname);
    /**
     * 价格区间查询图书
     */
    List<BookDto> queryByPrice(Float begin,Float end);
    /**
     * 分类查询图书
     */
    List<BookDto> queryBookByType(PageVO pageVO);
}
