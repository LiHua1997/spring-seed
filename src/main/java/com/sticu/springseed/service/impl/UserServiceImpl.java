package com.sticu.springseed.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sticu.springseed.common.ErrorCode;
import com.sticu.springseed.model.dto.user.UserAddRequest;
import com.sticu.springseed.model.entity.user.LoginUser;
import com.sticu.springseed.model.entity.user.User;
import com.sticu.springseed.model.vo.LoginUserVO;
import com.sticu.springseed.service.UserCache;
import com.sticu.springseed.service.UserService;
import com.sticu.springseed.mapper.UserMapper;
import com.sticu.springseed.utils.JwtUtils;
import com.sticu.springseed.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author songt
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2024-01-14 15:26:39
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserCache userCache;
    @Override
    public long addUser(UserAddRequest userAddRequest) {
        String userName = userAddRequest.getUserName();
        String password = userAddRequest.getPassword();
        String checkPassword = userAddRequest.getCheckPassword();

        // 1. 校验
        ThrowUtils.throwIf(StringUtils.isAnyBlank(userName, password, checkPassword), ErrorCode.PARAMS_ERROR, "参数为空");
        ThrowUtils.throwIf(userName.length() < 4, ErrorCode.PARAMS_ERROR, "用户账号过短");
        ThrowUtils.throwIf(password.length() < 8 || checkPassword.length() < 8, ErrorCode.PARAMS_ERROR, "用户密码过短");

        // 密码和校验密码相同
        ThrowUtils.throwIf(!password.equals(checkPassword), ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
        synchronized (userName.intern()) {
            // 账户不能重复
            long count = this.baseMapper.selectCount(Wrappers.<User>lambdaQuery().eq(User::getUserName, userName));
            ThrowUtils.throwIf(count > 0, ErrorCode.PARAMS_ERROR, "账号重复");

            // 2. 加密
            String encryptPassword = new BCryptPasswordEncoder().encode(password);
            // 3. 插入数据
            User user = new User();
            BeanUtils.copyProperties(userAddRequest, user);
            user.setPassword(encryptPassword);
            boolean saveResult = this.save(user);
            ThrowUtils.throwIf(!saveResult, ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");

            return user.getSysUserId();
        }
    }

    @Override
    public LoginUserVO login(String userAccount, String userPassword) {
        // 1. 校验
        ThrowUtils.throwIf(StringUtils.isAnyBlank(userAccount, userPassword), ErrorCode.PARAMS_ERROR, "参数为空");
        ThrowUtils.throwIf(userAccount.length() < 4, ErrorCode.PARAMS_ERROR, "账号错误");
        ThrowUtils.throwIf(userPassword.length() < 8, ErrorCode.PARAMS_ERROR,  "密码错误");

        // 2. 认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userAccount,userPassword);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        ThrowUtils.throwIf(Objects.isNull(authenticate), ErrorCode.PARAMS_ERROR, "用户名或密码错误");

        // 3. 生成Token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getSysUserId().toString();
        String jwt = JwtUtils.createJWT(userId);

        //4. 缓存登录态
        userCache.setLoginUser(loginUser);

        return LoginUserVO.builder().token(jwt).build();
    }
}
