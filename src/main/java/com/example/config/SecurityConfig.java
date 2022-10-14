package com.example.config;

import com.example.common.handler.JwtAccessDeniedHandler;
import com.example.common.handler.JwtAuthenticationEntryPoint;
import com.example.Filter.JwtAuthenticationFilter;
import com.example.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
   @Autowired
    JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Autowired
   JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService())
               .passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable();
      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

      http.authorizeHttpRequests().anyRequest().authenticated();
      http.headers().cacheControl();
      //token过lu器
      http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

      //没有登录权限返回结果
      http.exceptionHandling().
      authenticationEntryPoint(jwtAuthenticationEntryPoint)
      .accessDeniedHandler(jwtAccessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/user/login");
    }









   @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
   }


}
