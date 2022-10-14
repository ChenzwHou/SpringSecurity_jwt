package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.UserRoles;
import com.example.mapper.UserRolesMapper;
import com.example.service.UserRolesService;
import org.springframework.stereotype.Service;

/**
* @author Chenzw
* @description 针对表【user_roles】的数据库操作Service实现
* @createDate 2022-10-14 17:09:53
*/
@Service
public class UserRolesServiceImpl extends ServiceImpl<UserRolesMapper, UserRoles>
    implements UserRolesService {

}




