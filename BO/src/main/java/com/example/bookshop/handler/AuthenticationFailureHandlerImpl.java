package com.example.bookshop.handler;/**
 * @Auther: shu'ai
 * @Date: 2021/10/14 21:18
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import com.example.bookshop.constants.StatusConstant;
import com.example.bookshop.pojo.ResultMap;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName:AuthenticationFailureHandlerImpl
 * @Auther: shu'ai
 * @Description: 失败后
 * @Date: 2021/10/14 21:18
 * @Version: v1.0
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(new ResultMap<>(false, StatusConstant.ERROR,e.getMessage())));
    }
}
