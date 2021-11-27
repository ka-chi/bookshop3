package com.example.bookshop.handler;/**
 * @Auther: shu'ai
 * @Date: 2021/10/12 16:01
 * @Description:
 */


import com.alibaba.fastjson.JSON;
import com.example.bookshop.constants.StatusConstant;
import com.example.bookshop.dto.PrincipalDto;
import com.example.bookshop.pojo.ResultMap;
import com.example.bookshop.util.CommonUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName:AuthenticationSuccessHandlerImpl
 * @Auther: shu'ai
 * @Description: 登录成功后的处理
 * @Date: 2021/10/12 16:01
 * @Version: v1.0
 */

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(new ResultMap<PrincipalDto>(true, StatusConstant.OK,"登录成功", CommonUtil.getLoginUser())));
    }

}
