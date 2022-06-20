package com.example.bookshop.service.impl;/**
 * @Auther: shu'ai
 * @Date: 2021/10/21 20:38
 * @Description:
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookshop.constants.logConstant;
import com.example.bookshop.dao.BookDao;
import com.example.bookshop.dao.TypeDao;
import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.PageDto;
import com.example.bookshop.dto.PrincipalDto;
import com.example.bookshop.exception.myException;
import com.example.bookshop.pojo.Book;
import com.example.bookshop.pojo.Shopper;
import com.example.bookshop.pojo.Type;
import com.example.bookshop.service.bookService;

import com.example.bookshop.util.CommonUtil;
import com.example.bookshop.util.RedisUtil;
import com.example.bookshop.vo.BookVo;
import com.example.bookshop.vo.PageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.bookshop.constants.CommonConstant.ZEROI;

/**
 * @ClassName:bookServiceImpl
 * @Auther: shu'ai
 * @Description: 图书的操作
 * @Date: 2021/10/21 20:38
 * @Version: v1.0
 */
@Service
public class bookServiceImpl extends ServiceImpl<BookDao, Book> implements bookService {
    @Autowired
    BookDao bookDao;
    @Autowired
    TypeDao typeDao;
    @Autowired
    RedisUtil redisUtil;

    Logger logger = LogManager.getLogger(bookServiceImpl.class);


    @Override
    public void add(Integer id,BookVo bookVO) {

        logger.info("===="+bookVO.toString());
        Type type = typeDao.selectOne(
                new LambdaQueryWrapper<Type>()
                .select(Type::getId)
                .eq(Type::getTypename,bookVO.getTypename())
        );



        Book book = Book.builder()
                .bookname(bookVO.getBookname())
                .author(bookVO.getAuthor())
                .inventory(bookVO.getInventory())
                .publishing(bookVO.getPublishing())
                .price(bookVO.getPrice())
                .shopId(id)
                .saleNum(ZEROI)
                .typeId(type.getId())
                .log(bookVO.getLog())  ///暂时用这个
                .build();
        bookDao.insert(book);
        logger.info("用户id"+id+"上架图书"+bookVO.getBookname());
    }

    @Override
    public void deleteBook(Integer bookId) {
//        PrincipalDto principalDto = CommonUtil.getLoginUser();
//        logger.info(principalDto.getShopName()+"的店铺下架图书----");
        bookDao.deleteById(bookId);
        logger.info("的店铺下架图书成功----"+"图书编号:"+bookId);
    }


    /**
     *
     * @param
     * @return
     */
    @Override
    public List<BookVo> queryByShopper(Integer id) {
        List<BookVo> bookVos = bookDao.queryByShopper(id);

        logger.info("==="+bookVos.toString());
        return bookVos;
    }
    /**
     * 这个查询还待检验
     * @param
     * @return
     */
    @Override
    public PageDto<BookDto> queryByCustomer(PageVO pageVO) {
//        logger.info(pageVO.toString());
        String condition = pageVO.getCondition();
        String typename = pageVO.getTypename();
        List<BookDto> list =  null;
        if(CommonUtil.isBlank(condition)&&CommonUtil.isBlank(typename)){
            logger.info("全局获取图书");
            PageHelper.startPage(pageVO.getPageIndex(),pageVO.getPageSize());
           list = bookDao.queryHome();
        }else if(!CommonUtil.isBlank(condition)){
            logger.info("按图书名字查询");
            PageHelper.startPage(pageVO.getPageIndex(),pageVO.getPageSize());
            list = bookDao.queryByBookname(condition);
        }else if(!CommonUtil.isBlank(typename)){
            logger.info("按分类查询");
//            list = redisUtil.hasKey(typename)? Object2Book(redisUtil.lGet(typename,0,-1)):this.queryBookByType(pageVO);
            list = this.queryBookByType(pageVO);
        }
        PageInfo<BookDto> page = new PageInfo<>(list);
//        logger.info("--图书"+page.getList());
        return new PageDto<BookDto>(page.getList(),page.getTotal());
    }


    private List<BookDto> Object2Book(List<Object> lists){
        List<BookDto> spuBos = lists.stream().map(spu -> {
            BookDto spuBo = new BookDto();
            //把spu拷贝到spuBo
            BeanUtils.copyProperties(spu, spuBo);
            return spuBo;
        }).collect(Collectors.toList());
        return spuBos;
    }

    /**
     * 这个查询还待检验
     * @param bookname
     * @return
     */
    @Override
    public List<BookDto> queryByBookName(String bookname) {
       return  bookDao.queryByBookname(bookname);
    }

    @Override
    public List<BookDto> queryByPrice(Float begin, Float end) {
        return bookDao.queryByPrice(begin,end);
    }

    @Override
    public List<BookDto> queryBookByType(PageVO pageVO) {
        logger.info("开始图书分类查询"+pageVO.toString());

        Type type = typeDao.selectOne(
                new LambdaQueryWrapper<Type>()
                .select(Type::getLevel,Type::getId,Type::getTypename,Type::getParentId)
                .eq(Type::getTypename,pageVO.getTypename())
        );
        logger.info("图书的分类级别是"+type.getLevel());
        List<BookDto> bookDtos = null;
        PageHelper.startPage(pageVO.getPageIndex(),pageVO.getPageSize());
        switch (type.getLevel()){
            case 1:{bookDtos = bookDao.queryByTypename1(pageVO.getTypename());break;}//一级分类
            case 2:{bookDtos = bookDao.queryByTypename2(pageVO.getTypename());break;}//二级分类
            case 3:{bookDtos = bookDao.queryByTypename3(pageVO.getTypename());break;}//三级分类
            default:throw new myException("数据异常，不存在该等级分类");
        }
        PageInfo<BookDto> page = new PageInfo<>(bookDtos);
        logger.info("000"+page.getList());
        return bookDtos;
    }




    @Override
    public void updateBook(BookDto bookVO) {

    }
}
