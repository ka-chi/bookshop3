package com.example.bookshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookshop.dto.PageDto;
import com.example.bookshop.pojo.Shopper;
import com.example.bookshop.vo.ShoperVO;

import java.util.List;

/**
 * @Auther: shu'ai
 * @Date: 2021/10/16 19:07
 * @Description:
 */
public interface shopperService extends IService<Shopper> {

    /**
     * 查询未审核的商家
     */
    PageDto<Shopper> queryUnchecked(Integer PageNo,Integer PageSize);

    /**
     * 管理员审核商家
     * @param id
     * @param result
     */
     void checkShop(Integer id,String result);

    /**
     * 注册商家
     * @param registerVO
     */
     void Sregister(ShoperVO registerVO);

    List<Shopper> getShopper();
}
