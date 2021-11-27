package com.example.bookshop.service.impl;/**
 * @Auther: shu'ai
 * @Date: 2021/10/16 15:18
 * @Description:
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookshop.dao.CustomerDao;
import com.example.bookshop.dto.PrincipalDto;
import com.example.bookshop.exception.myException;
import com.example.bookshop.pojo.Customer;
import com.example.bookshop.pojo.Shopper;
import com.example.bookshop.service.customerService;
import com.example.bookshop.util.CommonUtil;
import com.example.bookshop.vo.LoginVO;
import com.example.bookshop.vo.CustomerVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static com.example.bookshop.constants.CommonConstant.*;

/**
 * @ClassName:customerServiceImpl
 * @Auther: shu'ai
 * @Description: 顾客的服务层
 * @Date: 2021/10/16 15:18
 * @Version: v1.0
 */
@Service
public class customerServiceImpl extends ServiceImpl<CustomerDao, Customer> implements customerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private com.example.bookshop.dao.ShopperDao shopperDao;
    @Autowired
    private com.example.bookshop.dao.LoginDao loginDao;

    Logger logger = LogManager.getLogger(customerServiceImpl.class);


    @Override
    public void changePassword(LoginVO login) {
        logger.info("-----开始更改密码------");
        PrincipalDto plogin = CommonUtil.getLoginUser();


        Integer auth = plogin.getAuthentic();
        if(auth==ONEI){

            Customer login1 = customerDao.selectOne(
                    new LambdaQueryWrapper<Customer>()
                            .select(Customer::getUsername).eq(Customer::getUsername, login.getUsername()));
            if (login.getPassword().equals(login1.getPassword())){
                login1.setPassword(BCrypt.hashpw(login.getNewPassword(), BCrypt.gensalt()));
                customerDao.updateById(login1);
            }
            else{
                throw new myException("原密码输入错误");
            }
            logger.info("-----顾客更改密码完毕------");
        }
        if(auth==TWOI){
            Shopper login1 = shopperDao.selectOne(
                    new LambdaQueryWrapper<Shopper>()
                            .select(Shopper::getUsername).eq(Shopper::getUsername, login.getUsername()));
            if (login.getPassword().equals(login1.getPassword())){
                login1.setPassword(BCrypt.hashpw(login.getNewPassword(), BCrypt.gensalt()));
                shopperDao.updateById(login1);
            }
            else{
                throw new myException("原密码输入错误");
            }
            logger.info("-----商家更改密码完毕------");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(CustomerVO CustomerVO) {
        logger.info("------顾客开始注册");

        //    private String name;
        //
        //    private String sex;
        //
        //    private String username;
        //
        //    private String password;
        //
        //    private String tel;

//        if(!CommonUtil.isBlank(registerVO.getUsername())) throw new myException("用户名为空");
//        if(!CommonUtil.isBlank(registerVO.getSex())) throw new myException("性别为空");
//        if(!CommonUtil.isBlank(registerVO.getTel())) throw new myException("电话为空");
//        if(!CommonUtil.isBlank(registerVO.getPassword())) throw new myException("密码为空");
//        if(!CommonUtil.isBlank(registerVO.getName())) throw new myException("昵称为空");

        if(checkUser(CustomerVO.getUsername())) throw new myException("该用户名已被注册");
        //1.顾客注册   login表里通过触发器操作
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Customer c = Customer.builder()
                    .money(ZEROF)
                    .sex(CustomerVO.getSex())
                    .name(CustomerVO.getName())
                    .authentic(ONEI)
                    .registerTime(simpleDateFormat.format(date))
                    .username("cust"+ CustomerVO.getUsername())
                    .password(BCrypt.hashpw(CustomerVO.getPassword(), BCrypt.gensalt()))
                    .tel(CustomerVO.getTel())
                    .build();
            customerDao.insert(c);
        logger.info("-----顾客注册成功"+c.getUsername());
    }

    @Override
    public void updateAddress(String address) {
        PrincipalDto plogin = CommonUtil.getLoginUser();
        customerDao.updateAddress(address,plogin.getId());
        logger.info(plogin.getName()+"的收获地址已更新----");
    }

    @Override
    public void updateMoney(Float money) {
        PrincipalDto principalDto = CommonUtil.getLoginUser();
        Customer customer = customerDao.selectOne(new LambdaQueryWrapper<Customer>().eq(Customer::getId,principalDto.getId()));
        customer.setMoney(customer.getMoney()+money);
        customerDao.updateById(customer);
    }


    /**
     * 校验用户数据是否合法
     *
     * @param
     * @return 合法状态
     */
    private Boolean checkUser(String login) {
//        if (!user.getCode().equals(redisService.get(CODE_KEY + user.getUsername()))) {
//            throw new ServeException("验证码错误！");
//        }
        //查询用户名是否存在
        Customer login1 = customerDao.selectOne(
                new LambdaQueryWrapper<Customer>()
                .select(Customer::getUsername).eq(Customer::getUsername, login));
        return Objects.nonNull(login1);
    }





}
