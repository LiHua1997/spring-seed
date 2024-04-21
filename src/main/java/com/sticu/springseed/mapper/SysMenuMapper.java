package com.sticu.springseed.mapper;

import com.sticu.springseed.model.entity.user.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author st_dev
* @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
* @createDate 2024-04-21 11:14:09
* @Entity com.sticu.springseed.model.entity.user.SysMenu
*/
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> listPermissionsByUserId(Long id);
}
