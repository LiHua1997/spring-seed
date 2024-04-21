package com.sticu.springseed.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sticu.springseed.model.entity.user.SysMenu;
import com.sticu.springseed.service.SysMenuService;
import com.sticu.springseed.mapper.SysMenuMapper;
import org.springframework.stereotype.Service;

/**
* @author st_dev
* @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
* @createDate 2024-04-21 11:14:09
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService{

}
