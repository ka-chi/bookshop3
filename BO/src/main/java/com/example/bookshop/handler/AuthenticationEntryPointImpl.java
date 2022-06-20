package com.example.bookshop.handler;/**
 * @Auther: shu'ai
 * @Date: 2021/11/15 13:31
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import com.example.bookshop.constants.StatusConstant;
import com.example.bookshop.pojo.ResultMap;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName:AuthenticationEntryPointImpl
 * @Auther: shu'ai
 * @Description:
 * @Date: 2021/11/15 13:31
 * @Version: v1.0
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(new ResultMap<>(false, StatusConstant.NOT_LOGIN, "请登录")));
    }
}
