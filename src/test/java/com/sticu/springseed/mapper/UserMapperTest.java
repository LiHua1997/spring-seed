package com.sticu.springseed.mapper;

import com.sticu.springseed.model.entity.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
