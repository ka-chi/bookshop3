package com.example.bookshop.controller;/**
 * @Auther: shu'ai
 * @Date: 2021/10/20 14:03
 * @Description:
 */

import com.example.bookshop.constants.StatusConstant;
import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.PageDto;
import com.example.bookshop.pojo.Book;
import com.example.bookshop.pojo.ResultMap;
import com.example.bookshop.service.impl.bookServiceImpl;
import com.example.bookshop.vo.BookVo;
import com.example.bookshop.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName:BookController
 * @Auther: shu'ai
 * @Description: 针对图书的管理
 * @Date: 2021/10/20 14:03
 * @Version: v1.0
 */

@Api("图书相关接口")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    bookServiceImpl bookService;


    /**
     * 上架一本图书，需要当前的商家信息，图书信息，分类信息
     * @return
     */
    @ApiOperation("当前商家上架一本图书")
    @PostMapping("/add/{id}")
    public ResultMap<?> addBook(@PathVariable Integer id ,@RequestBody BookVo bookInfo){
        bookService.add(id,bookInfo);
        return new ResultMap<>(true, StatusConstant.OK,"添加图书成功");
    }

    /**
     * 下架一本图书，需要图书id
     * @return
     */
    @ApiOperation("当前商家下架一本图书")
    @PostMapping("/delete/{bookid}")
    public ResultMap<?> deleteBook(@PathVariable("bookid") Integer bookid){
        bookService.deleteBook(bookid);
        return new ResultMap<>(true, StatusConstant.OK,"下架图书成功");
    }


    /**
     * 查询当前商户的所有图书
     * 传入商家id
     * @return
     */
    @ApiOperation("查询当前商户的所有图书")
    @PostMapping("/queryByShopper/{id}")
    public ResultMap<List<BookVo>> queryByShopper(@PathVariable Integer id){
        return new ResultMap<>(true, StatusConstant.OK,"商家查询图书成功",bookService.queryByShopper(id));
    }

    /**
     * 查询所有图书
     * @return
     */
    @ApiOperation("查询所有图书")
    @PostMapping("/queryByCustomer")
    public ResultMap<PageDto<BookDto>> queryByCustomer(@RequestBody PageVO pageVO){
        return new ResultMap<>(true, StatusConstant.OK,"查询图书成功",bookService.queryByCustomer(pageVO));
    }


    /**
     * 按价格区间查询 @RequestParam Integer begin,@RequestParam Integer end
     * @return
     */
    @PostMapping("/queryByPrice")
    public ResultMap<?> queryByPrice(@RequestParam(defaultValue = "0F") Float begin,@RequestParam(defaultValue = "2000F") Float end){

        return new ResultMap<>(true, StatusConstant.OK,"按照价格查询图书成功",bookService.queryByPrice(begin,end));
    }


}
