package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.R;
import com.example.entity.SysUser;
import com.example.mapper.SysUserMapper;
import com.example.service.SysUserService;
import com.example.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author Chenzw
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2022-10-14 17:09:53
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService {
   @Autowired
   SysUserMapper sysUserMapper;
   @Autowired
    UserDetailsServiceImpl userDetailsService;
   @Autowired
   private PasswordEncoder passwordEncoder;
   @Value("${jwt.tokenHead}")
   private String tokenHead;
   @Autowired
   private TokenUtil tokenUtil;

    @Override
    public R login(SysUser sysUser) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(sysUser.getUsername());
        if (userDetails==null||!passwordEncoder.matches(sysUser.getPassword(),userDetails.getPassword())){
            return R.fail("账号密码错误！！！");
        }
          if (!userDetails.isEnabled()){
              return R.fail("该账号已禁用！");
          }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
       //获取token
        String token = tokenUtil.generateToken(userDetails);
        Map<String,String > map=new HashMap<>(2);
        map.put("tokenHead",tokenHead);
        map.put("token",token);
        return R.succ(200,"登录成功！",map);
    }

    @Override
    public SysUser findByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return sysUserMapper.selectOne(queryWrapper);
    }
}




