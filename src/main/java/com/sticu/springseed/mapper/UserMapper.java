package com.sticu.springseed.mapper;

import com.sticu.springseed.model.entity.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author st
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2024-01-14 15:26:39
* @Entity com.sticu.springseed.model.entity.user.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




