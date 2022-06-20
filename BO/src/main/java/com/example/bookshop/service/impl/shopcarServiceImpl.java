package com.example.bookshop.service.impl;/**
 * @Auther: shu'ai
 * @Date: 2021/10/26 13:51
 * @Description:
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookshop.dao.BookDao;
import com.example.bookshop.dao.ShopcarDao;
import com.example.bookshop.dto.PrincipalDto;
import com.example.bookshop.dto.ShopcarDto;
import com.example.bookshop.pojo.Book;
import com.example.bookshop.pojo.Shopcar;
import com.example.bookshop.service.shopcarService;
import com.example.bookshop.util.CommonUtil;

import com.example.bookshop.vo.ShopcarVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.bookshop.constants.CommonConstant.ONEI;


/**
 * @ClassName:shopcarServiceImpl
 * @Auther: shu'ai
 * @Description: 购物车服务
 * @Date: 2021/10/26 13:51
 * @Version: v1.0
 */

@Service
public class shopcarServiceImpl extends ServiceImpl<ShopcarDao, Shopcar> implements shopcarService {

    @Autowired
    BookDao bookDao;
    @Autowired
    ShopcarDao shopcarDao;

    Logger logger = LogManager.getLogger(shopcarServiceImpl.class);

    @Transactional
    @Override
    public void add(ShopcarVO shopcarVO) {
//        PrincipalDto principalDto = CommonUtil.getLoginUser();
        Shopcar shopcar =  shopcarDao.selectOne(
                new LambdaQueryWrapper<Shopcar>()
                .eq(Shopcar::getBookId, shopcarVO.getBookid())
                .eq(Shopcar::getCId,shopcarVO.getCustid())
        );

        if(shopcar!=null) {
            //如果购物车里已经有了该商品，就更新数量
            logger.info("购物车已经有："+shopcar.toString());
            shopcar.setNum(shopcar.getNum()+1);
            shopcarDao.update(shopcar,new LambdaQueryWrapper<Shopcar>()
                    .eq(Shopcar::getBookId,shopcarVO.getBookid())
                    .eq(Shopcar::getCId,shopcarVO.getCustid())
                );
        }else{
            addToShopcar(shopcarVO.getBookid(),shopcarVO.getCustid());
        }

    }

    @Override
    public void delete(Integer shopcarId) {

        shopcarDao.deleteById(shopcarId);
        logger.info("删除成功==="+shopcarId);
    }

    @Override
    public void update(ShopcarVO shopcarVO) {
        logger.info(shopcarVO.toString());
        shopcarDao.updateOne(shopcarVO.getBookid(),shopcarVO.getCustid(),shopcarVO.getNum());
        logger.info("-----更新购物车");
    }

    @Override
    public void deleteByList(List<Integer> idlist) {
        PrincipalDto principalDto = CommonUtil.getLoginUser();
        for(Integer id :idlist){
            shopcarDao.delete(new LambdaQueryWrapper<Shopcar>()
                    .eq(Shopcar::getBookId,id).eq(Shopcar::getCId,principalDto.getId()));
        }
    }

    @Override
    public List<ShopcarDto> getAll(Integer custid) {

        List<ShopcarDto> shopcars = shopcarDao.getAll(custid);
//        logger.info("获取"+custid+"----shopcar"+shopcars.toString());
        return shopcars;
    }

    public void addToShopcar(Integer bookid,Integer id){
        Book book = bookDao.selectById(bookid);
        Shopcar shopcar = Shopcar.builder()
                .bookId(bookid)
                .cId(id)
                .num(ONEI)
                .price(book.getPrice())
                .build();
        shopcarDao.insert(shopcar);
        //logger.info(book.getBookname()+"被加入了"+principalDto.getName()+"的购物车");
        logger.info("添加购物车 成功");
    }

}
