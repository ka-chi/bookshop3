package com.example.bookshop.config;/**
 * @Auther: shu'ai
 * @Date: 2021/10/26 09:26
 * @Description:
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.bookshop.constants.CommonConstant;
import com.example.bookshop.dao.TypeDao;
import com.example.bookshop.pojo.Type;

import com.example.bookshop.util.RedisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

import static com.example.bookshop.constants.CommonConstant.*;

/**
 * @ClassName:interceptor
 * @Auther: shu'ai
 * @Description: 程序运行监听
 * @Date: 2021/10/26 09:26
 * @Version: v1.0
 */

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class interceptor {

    @Autowired
    TypeDao typeDao;
    @Autowired
    RedisUtil redisUtil;

    static Logger logger = LogManager.getLogger(interceptor.class);

    public interceptor(){}

    @PostConstruct
    public void init(){

        redisUtil.del(TYPE1);
        redisUtil.del(TYPE2);
        redisUtil.del(TYPE3);
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

        redisUtil.lSetALL(TYPE1,list1);
        redisUtil.lSetALL(TYPE2,list2);
        redisUtil.lSetALL(TYPE3,list3);

        logger.info("缓存载入成功---"+redisUtil.lGet(TYPE2,0,-1));
    }

    @PreDestroy
    public void predestory(){

    }

}
