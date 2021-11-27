package com.example.bookshop.handler;/**
 * @Auther: shu'ai
 * @Date: 2021/10/15 14:01
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import com.example.bookshop.constants.StatusConstant;
import com.example.bookshop.pojo.ResultMap;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName:LogoutSuccessHandlerImpl
 * @Auther: shu'ai
 * @Description:
 * @Date: 2021/10/15 14:01
 * @Version: v1.0
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(new ResultMap<>(true,StatusConstant.OK,"成功注销")));
    }
}
