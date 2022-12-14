package com.example.common.handler;

import com.example.common.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//当用户未登录和token过期时访问
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
      response.setStatus(401);
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
        PrintWriter o = response.getWriter();
        o.write(new ObjectMapper().writeValueAsString(R.fail("您尚未登录")));
        o.flush();
        o.close();
    }
}
