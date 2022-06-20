package com.example.bookshop.service.impl;/**
 * @Auther: shu'ai
 * @Date: 2021/10/16 19:07
 * @Description:
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookshop.dao.ShopperDao;
import com.example.bookshop.dto.PageDto;
import com.example.bookshop.exception.myException;
import com.example.bookshop.pojo.Shopper;
import com.example.bookshop.service.shopperService;

import com.example.bookshop.vo.ShoperVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.example.bookshop.constants.CommonConstant.*;
import static com.example.bookshop.constants.logConstant.headimage;

/**
 * @ClassName:shopeperServiceImpl
 * @Auther: shu'ai
 * @Description: 商家相关操作
 * @Date: 2021/10/16 19:07
 * @Version: v1.0
 */
@Service
public class ShopperServiceImpl extends ServiceImpl<ShopperDao, Shopper> implements shopperService {
    @Autowired
    private ShopperDao shopperDao;


    Logger logger = LogManager.getLogger(ShopperServiceImpl.class);
    @Override
    public PageDto<Shopper> queryUnchecked(Integer PageNo,Integer PageSize) {
        logger.info("-----查询未审核的商家------");
        PageHelper.startPage(PageNo,PageSize);
        //这个地方需要验证一下查询,
        List<Shopper> list= shopperDao.selectList(
                new LambdaQueryWrapper<Shopper>().eq(Shopper::getCheckStatus,ZEROS));
        //用PageInfo对结果进行包装
        PageInfo<Shopper> page = new PageInfo<>(list);
        return new PageDto<Shopper>(page.getList(),page.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void checkShop(Integer id,String result) {
        logger.info("-----审核商家------");
        logger.info("id"+id);
        logger.info("result"+result);
        Shopper shop = shopperDao.selectById(id);
        shop.setCheckStatus(result);
        shopperDao.updateById(shop);

        //做一次通知--短信通知
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void Sregister(ShoperVO registerVO) {
        logger.info("-----商家"+registerVO.getUsername()+"注册----");
        if(checkUser(registerVO.getUsername(),registerVO.getShopName())) throw new myException("商家用户名已存在");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //2.商家注册
            Shopper shop = Shopper.builder()
                    .authentic(TWOI)
                    .checkStatus(ZEROS)  //0待审核，1审核通过，2未通过
                    .log(headimage)///这里写一张图片地址，默认商家图标
                    .tel(registerVO.getTel())
                    .shopName(registerVO.getShopName())
                    .registerMoney(registerVO.getRegisterMoney())
                    .username("shop"+registerVO.getUsername())
                    .password(BCrypt.hashpw(registerVO.getPassword(), BCrypt.gensalt()))
                    .type(registerVO.getType())
                    .shopAddress(registerVO.getShopAddress())
                    .registerTime(simpleDateFormat.format(date))
                    .build();
            shopperDao.insert(shop);
            //做一次通知到管理员；考虑websocket或者netty
        logger.info("-----商家"+registerVO.getUsername()+"注册成功----");
        }

        @Override
        public List<Shopper> getShopper(){
        List<Shopper> list = shopperDao.selectList(
                new LambdaQueryWrapper<Shopper>()
                .eq(Shopper::getCheckStatus,ONES)
        );
        return list;
    }

    /**
     * 校验用户数据是否合法
     *待测试
     * @param
     * @return 合法状态
     */
    private Boolean checkUser(String login,String shopname) {

        Shopper login1 = shopperDao.selectOne(
                new LambdaQueryWrapper<Shopper>()
                        .select(Shopper::getUsername)
                        .eq(Shopper::getUsername, login)
                        .or().eq(Shopper::getShopName, shopname) );
        return Objects.nonNull(login1);
    }



}
