package com.example.service;

import com.example.common.R;
import com.example.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Chenzw
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2022-10-14 17:09:53
*/
public interface SysUserService extends IService<SysUser> {
   R login(SysUser sysUser);

   SysUser findByUsername(String username);
}
