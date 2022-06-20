package com.example.bookshop.service.impl;/**
 * @Auther: shu'ai
 * @Date: 2021/10/26 14:56
 * @Description:
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookshop.dao.CustomerDao;
import com.example.bookshop.dao.OrderDao;
import com.example.bookshop.dao.ShopcarDao;
import com.example.bookshop.dto.OrderDto;
import com.example.bookshop.dto.PageDto;
import com.example.bookshop.dto.PrincipalDto;
import com.example.bookshop.dto.ShopcarDto;
import com.example.bookshop.exception.myException;
import com.example.bookshop.pojo.Customer;
import com.example.bookshop.pojo.Order;
import com.example.bookshop.pojo.Shopcar;
import com.example.bookshop.pojo.Type;
import com.example.bookshop.service.orderService;
import com.example.bookshop.util.CommonUtil;
import com.example.bookshop.util.RedisUtil;
import com.example.bookshop.vo.OrderVO;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.bookshop.constants.CommonConstant.*;

/**
 * @ClassName:orderServiceImpl
 * @Auther: shu'ai
 * @Description: 订单
 * @Date: 2021/10/26 14:56
 * @Version: v1.0
 */
@Service
public class orderServiceImpl extends ServiceImpl<OrderDao, Order> implements orderService {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    OrderDao orderDao;
    @Autowired
    ShopcarDao shopcarDao;
    @Autowired
    CustomerDao customerDao;


    Logger logger = LogManager.getLogger(orderServiceImpl.class);

    @Transactional
    @Override
    public void sendToOrder(List<ShopcarDto> list) {
        if(list.size()==0) throw new myException("请先选择购物车内的图书");

        PrincipalDto principalDto = CommonUtil.getLoginUser();
        logger.info(principalDto.toString());
        String address = customerDao.selectOne(new LambdaQueryWrapper<Customer>().eq(Customer::getId,principalDto.getId())).getAddress();
        if(CommonUtil.isBlank(address)) throw new myException("请先填写收获地址---");

//        if(redisUtil.hasKey(principalDto.getUsername())) throw new myException("已存在未支付的订单，请先完成订单");


        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Order> orderList = new ArrayList<>();
        List<OrderDto> orderDtos = new ArrayList<>();
        String orderno = IdWorker.getIdStr();
        Integer shopid = 0;
        ///排序就可以保证同一时间下的同一商家的购物车会被提交到一个订单下
        list = list.stream().sorted(Comparator.comparing(ShopcarDto::getShopId)).collect(Collectors.toList());
        for(ShopcarDto s : list){
            if(shopid!=s.getShopId()){
                orderno = IdWorker.getIdStr();
                shopid = s.getShopId();
            }

            Order order = Order.builder()
                    .payTime(simpleDateFormat.format(date))
                    .status(WFK)
                    .cId(principalDto.getId())
                    .address(principalDto.getAddress())
                    .sumMoney(s.getGoodTotal())
                    .shopId(s.getShopId())
                    .orderNo(orderno)
                    .bookId(s.getBookId())
                    .num(s.getNum())
                    .build();

            OrderDto orderDto = OrderDto.builder()
                    .log(s.getLog())
                    .bookId(s.getBookId())
                    .num(s.getNum())
                    .cId(s.getCId())
                    .address(s.getAddress())
                    .author(s.getAuthor())
                    .orderNo(orderno)
                    .shopName(s.getShopName())
                    .sumMoney(s.getGoodTotal())
                    .build();
            orderList.add(order);
            orderDtos.add(orderDto);
        }

        orderDao.insertList(orderList);
        redisUtil.lSet(principalDto.getUsername(),orderDtos);
        redisUtil.expire(principalDto.getUsername(),1800);
        for(ShopcarDto s:list){
            shopcarDao.deleteById(s.getId());
        }
        logger.info("订单生成----");
        logger.info(orderDtos.toString());
    }



    /**
     * 这里的操作时间可能较长，建议前台界面做延时访问，先把目标界面展示
     * @param
     */
    @Transactional
    @Override
    public Float payOrder(String orderNo) {
        List<Order> orderList = orderDao.selectList(
                new LambdaQueryWrapper<Order>()
                        .select(Order::getSumMoney,Order::getOrderNo)
                        .eq(Order::getOrderNo,orderNo));
        Double sumMoney = orderList.stream().mapToDouble(s->s.getSumMoney()).sum();
        PrincipalDto principalDto = CommonUtil.getLoginUser();
        orderDao.updateState(YFK,orderNo);

        Customer customer = customerDao.selectOne(new LambdaQueryWrapper<Customer>().eq(Customer::getId,principalDto.getId()));
        customer.setMoney(customer.getMoney()-sumMoney.floatValue());
        redisUtil.del(principalDto.getUsername());

        return sumMoney.floatValue();
    }

    @Override
    public void cancelOrder(String orderNo) {
        logger.info("即将取消的订单号"+orderNo);
        //取消已经提交的订单
        orderDao.updateState(YQX,orderNo);
//        redisUtil.del(principalDto.getUsername());
    }

    /**
     * 顾客修改订单收货地址
     * @param orderNo
     * @param address
     */
    @Override
    public void updateAddress(String orderNo, String address) {
        orderDao.updateAddress(orderNo,address);
    }

    /**
     *
     * @return
     */
    @Override
    public List<PageDto<OrderDto>> selectByCustomer(OrderVO orderVO) {
        logger.info("进入测试点0---");
        logger.info(orderVO.toString());

        String status = orderVO.getStatus();
//        PrincipalDto principalDto = CommonUtil.getLoginUser();
        String address = "";
//        List<List<OrderDto>> lists = new ArrayList<>();
//        PageDto<List<OrderDto>> pageDto =new PageDto<>();
        List<OrderDto> orderDtos = orderDao.selectByCustomer(orderVO.getCustId(),status);
        List<PageDto<OrderDto>> dtoList = new ArrayList<>();
//        logger.info(principalDto.getId());
        if(orderDtos.size()==0) throw new myException("暂无数据");



        switch (status){
            case WFK:
            case YFH:
            case YQX:
            case YWC:
            case YFK: {
                //已付款这里做查询
                PageDto<OrderDto> pageDto =new PageDto<>();
                String orderNo = "0";
                List<OrderDto> list = new ArrayList<>();
//                list = list.stream().sorted((s1,s2)->s1.getShopId().compareTo(s2.getShopId())).collect(Collectors.toList());
                for(OrderDto o : orderDtos){
                    if (orderNo.equals(o.getOrderNo())) {
                            list.add(o);
                    }else {
                        if(orderNo.equals("0")){ address = o.getAddress();orderNo = o.getOrderNo(); list.add(o);continue;}
                        if(!CommonUtil.isBlank(o.getAddress())) {address = o.getAddress();}
                        covertPageDto(pageDto, list, address,orderNo);
                        dtoList.add(pageDto);
                        orderNo = o.getOrderNo();
                        pageDto =new PageDto<>();
                        list = new ArrayList<>();
                        list.add(o);
                    }
                }
                covertPageDto(pageDto, list, address,orderNo);
                dtoList.add(pageDto);
                logger.info("==="+dtoList);
                return dtoList;
            }
            default: throw new myException("系统异常-传入的常量：订单状态不对");
        }

    }

    /**
     * 商家获取自己待发货的商品
     * @param
     * @return
     */
    @Override
    public List<PageDto<OrderDto>> selectByShopper(OrderVO orderVO) {
        logger.info("测试1----");

        String status = orderVO.getStatus();

        List<OrderDto> orderDtos = orderDao.selectByShopper(orderVO.getCustId(),status);

        logger.info(orderVO.toString());
        logger.info(orderDtos.toString());

        if(orderDtos.size()==0) return null;

        List<PageDto<OrderDto>> dtoList = new ArrayList<>();

        switch (status){
            case WFK:
            case YFH:
            case YQX:
            case YWC:
            case YFK: {
                //已付款这里做查询

                PageDto<OrderDto> pageDto =new PageDto<>();
                String orderNo = "0";
                List<OrderDto> list = new ArrayList<>();

                String address = "";
                for(OrderDto o : orderDtos){
                    address = o.getAddress();
                    if (orderNo.equals(o.getOrderNo())) {
                        list.add(o);
                    }else {
                        if(orderNo.equals("0")){ orderNo = o.getOrderNo(); list.add(o);continue;}
                        orderNo = o.getOrderNo();
                        covertPageDto(pageDto, list, address,orderNo);
                        dtoList.add(pageDto);
                        pageDto =new PageDto<>();
                        list = new ArrayList<>();
                        list.add(o);
                    }
                }
                covertPageDto(pageDto, list, address,orderNo);
                dtoList.add(pageDto);
//                logger.info("===shopper的dtoList"+dtoList);
                return dtoList;
            }
            default: throw new myException("系统异常-传入的常量：订单状态不对");
        }
    }

    /**
     * 发货
     * @param orderNos
     */
    @Override
    public void sendBooks(String orderNos){
        //考虑效率还是在xml文件写sql语句更快
                    orderDao.updateState(YFH,orderNos);
                    logger.info("订单"+orderNos+"已发货");

    }

    @Override
    public void afterGetBook(String orderNo) {
        orderDao.updateState(YWC,orderNo);
    }



    private void covertPageDto(PageDto pageDto,List<OrderDto> list,String address,String orderNo){
        if(list.size()==0) return;

        pageDto.setRecordList(list);
        pageDto.setPayTime(list.get(0).getPayTime());
        pageDto.setAddress(address);
        Double sum = list.stream().mapToDouble(n->n.getSumMoney()).sum();
        pageDto.setSumMoney(sum.floatValue());
        pageDto.setOrderNo(orderNo);
    }

}
