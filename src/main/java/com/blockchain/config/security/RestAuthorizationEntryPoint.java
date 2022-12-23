package com.blockchain.config.security;

import com.blockchain.pojo.ResBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class RestAuthorizationEntryPoint  implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        //编码格式
        httpServletResponse.setCharacterEncoding("UTF-8");
        //返回类型
        httpServletResponse.setContentType("application/json");
        //获取输出流
        PrintWriter out = httpServletResponse.getWriter();
        ResBean error = ResBean.error("未登录，联系管理员！");

        error.setCode(401);
        out.write(new ObjectMapper().writeValueAsString(error));
        out.flush();
        out.close();
    }
}
