package com.example.mapper;

import com.example.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Chenzw
* @description 针对表【sys_user】的数据库操作Mapper
* @createDate 2022-10-14 17:09:53
* @Entity generator.entity.SysUser
*/
public interface SysUserMapper extends BaseMapper<SysUser> {
      SysUser findByUsername(String username);
}




