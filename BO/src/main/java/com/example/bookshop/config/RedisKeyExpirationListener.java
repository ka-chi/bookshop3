package com.example.bookshop.config;/**
 * @Auther: shu'ai
 * @Date: 2021/10/31 20:57
 * @Description:
 */

/**
 * @ClassName:RedisKeyExpirationListener
 * @Auther: shu'ai
 * @Description:
 * @Date: 2021/10/31 20:57
 * @Version: v1.0
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.bookshop.dao.OrderDao;
import com.example.bookshop.pojo.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import static com.example.bookshop.constants.CommonConstant.WFK;
import static com.example.bookshop.constants.CommonConstant.YQX;



@Primary
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Autowired
    OrderDao orderDao;

    Logger logger = LogManager.getLogger(RedisKeyExpirationListener.class);

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * Redis-key失效监听事件，所有key 失效都会走此方法
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        //  获取失效的key
        String expiredKey = message.toString();

        Order order = orderDao.selectOne(
                new LambdaQueryWrapper<Order>()
                        .select(Order::getCId)
                        .eq(Order::getOrderNo,expiredKey)
        );
        orderDao.updateState(WFK,expiredKey);
        logger.info("订单"+expiredKey+"已过期");
    }
}

