package com.example.Filter;

import com.example.service.impl.UserDetailsServiceImpl;
import com.example.util.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//token认证 在接口访问前进行过滤
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    //请求前获取请求头信息token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         //获取token
        String header = request.getHeader(tokenHeader);
        //判断token是否存在 头部是否匹配
        if (header!=null && header.startsWith(tokenHead)){
            //拿到token主体
            String token = header.substring(tokenHead.length());
            //拿到token用户名
            String username = tokenUtil.getUsernameByToken(token);
            //token存在，但没有登录信息的话  SecurityContextHolder在刷新的用户信息拿到
            if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                //没有登录信息直接登录
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //判断token是否有效
                if (tokenUtil.isExpiration(token) && username.equals(userDetails.getUsername())) {
                    //刷新security中的用户信息
                    UsernamePasswordAuthenticationToken authenticationToken=
                   new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                  authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                  SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }filterChain.doFilter(request,response);
    }
}
