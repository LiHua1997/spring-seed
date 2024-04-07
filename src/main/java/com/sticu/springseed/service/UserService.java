package com.sticu.springseed.service;

import com.sticu.springseed.model.dto.user.UserAddRequest;
import com.sticu.springseed.model.entity.user.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sticu.springseed.model.vo.LoginUserVO;

/**
* @author songt
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2024-01-14 15:26:39
*/
public interface UserService extends IService<User> {

    long addUser(UserAddRequest userAddRequest);

    LoginUserVO login(String userAccount, String userPassword);
}
