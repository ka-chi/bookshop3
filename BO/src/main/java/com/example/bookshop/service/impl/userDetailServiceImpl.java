package com.example.bookshop.service.impl;/**
 * @Auther: shu'ai
 * @Date: 2021/10/18 16:02
 * @Description:
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.bookshop.dao.CustomerDao;
import com.example.bookshop.dao.LoginDao;
import com.example.bookshop.dao.ShopperDao;
import com.example.bookshop.dao.TypeDao;
import com.example.bookshop.dto.PrincipalDto;
import com.example.bookshop.exception.myException;
import com.example.bookshop.pojo.Customer;
import com.example.bookshop.pojo.Login;
import com.example.bookshop.pojo.Shopper;
import com.example.bookshop.pojo.Type;
import com.example.bookshop.util.CommonUtil;
import com.example.bookshop.util.RedisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.bookshop.constants.CommonConstant.*;

/**
 * @ClassName:userDetailServiceImpl
 * @Auther: shu'ai
 * @Description: 用户登录操作
 * @Date: 2021/10/18 16:02
 * @Version: v1.0
 */
@Service
public class userDetailServiceImpl implements UserDetailsService {
    @Autowired
    CustomerDao customerDao;
    @Autowired
    ShopperDao shopperDao;
    @Autowired
    LoginDao loginDao;
    @Autowired
    TypeDao typeDao;
    @Autowired
    RedisUtil redisUtil;

    static Logger logger = LogManager.getLogger(userDetailServiceImpl.class);


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户开始登录---"+username);
        //先查login表确定用户是否存在，存在则继续。
        if(checkInUsername(username)) throw new myException("用户不存在");

        PrincipalDto principalDto = new PrincipalDto();
        String flag = username.substring(0,4);

        switch (flag){
            case CUSTOMER: {
                Customer customer = customerDao.selectOne(
                        new LambdaQueryWrapper<Customer>()
                                .eq(Customer::getUsername,username)
                                .eq(Customer::getAuthentic,ONEI)
                );
                BeanUtils.copyProperties(customer,principalDto);
                logger.info("顾客"+customer.getUsername()+"登录");
                break;
            }
            case SHOPPER :{
                Shopper shopper = shopperDao.selectOne(
                        new LambdaQueryWrapper<Shopper>()
                                .eq(Shopper::getUsername,username)
                                .eq(Shopper::getAuthentic,TWOI)
                                .eq(Shopper::getCheckStatus,ONES)
                );
                if(CommonUtil.isBlank(shopper)) throw new myException("该商家未通过审核");
                BeanUtils.copyProperties(shopper,principalDto);
                logger.info("商家"+shopper.getUsername()+"登录");
                break;
            }
            case SUPER: {
                Login login = loginDao.selectOne(
                        new LambdaQueryWrapper<Login>()
                                .eq(Login::getUsername,username)
                                .eq(Login::getAuthentic,ZEROI)
                );
                logger.info(login.toString());
                principalDto.setAuthentic(login.getAuthentic());
                principalDto.setUsername(login.getUsername());
                principalDto.setPassword(login.getPassword());
                logger.info("管理员"+login.getUsername()+"登录");
                break;
            }
            default: throw new myException("用户非法!!!");
        }

        logger.info("传入角色信息--"+principalDto.toString());
        return principalDto;
    }

    private boolean checkInUsername(String username){
        Login login = loginDao.selectOne(
                new LambdaQueryWrapper<Login>()
                        .select(Login::getUsername,Login::getAuthentic,Login::getS_id,Login::getC_id,Login::getPassword)
                        .eq(Login::getUsername,username)
        );
        return CommonUtil.isBlank(login);
    }

    /**
     * 在各个用户登录时，把类型列表写入缓存--当超级管理员做出对类型的更新的时候，重写缓存
     */
    public void setTypeList(){
        List<Type> list1 = typeDao.selectList(
           new LambdaQueryWrapper<Type>()
           .select(Type::getId,Type::getLevel,Type::getParentId,Type::getTypename)
                .eq(Type::getLevel,1)
        ) ;
        List<Type> list2 = typeDao.selectList(
                new LambdaQueryWrapper<Type>()
                        .select(Type::getId,Type::getLevel,Type::getParentId,Type::getTypename)
                        .eq(Type::getLevel,2)
        ) ;
        List<Type> list3 = typeDao.selectList(
                new LambdaQueryWrapper<Type>()
                        .select(Type::getId,Type::getLevel,Type::getParentId,Type::getTypename)
                        .eq(Type::getLevel,3)
        ) ;
        redisUtil.lSet("type_1",list1);
        redisUtil.lSet("type_2",list1);
        redisUtil.lSet("type_3",list1);
    }
}
