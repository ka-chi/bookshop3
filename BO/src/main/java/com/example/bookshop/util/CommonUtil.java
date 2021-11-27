package com.example.bookshop.util;/**
 * @Auther: shu'ai
 * @Date: 2021/10/19 08:58
 * @Description:
 */

import com.example.bookshop.dto.PrincipalDto;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName:CommonUtil
 * @Auther: shu'ai
 * @Description: 通用工具类
 * @Date: 2021/10/19 08:58
 * @Version: v1.0
 */

public class CommonUtil {
    public static boolean isBlank(Object object){
        if(object!=null) return false;
        return true;
    }

    /**
     * 获取当前登录的角色
     * @return
     */
    public static PrincipalDto getLoginUser(){
        return (PrincipalDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 判断当前用户名是否已经存在
     */
}
