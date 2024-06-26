create schema `spring-seed` if not exist;

use
`spring-seed`;
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `sys_user_id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_name`    VARCHAR(64)  NOT NULL DEFAULT 'NULL' COMMENT '用户名',
    `nick_name`    VARCHAR(64)  NOT NULL DEFAULT 'NULL' COMMENT '昵称',
    `password`     VARCHAR(128) NOT NULL DEFAULT 'NULL' COMMENT '密码',
    `status`       CHAR(1)               DEFAULT '0' COMMENT '账号状态 0-正常 1-停用',
    `email`        VARCHAR(64)           DEFAULT NULL COMMENT '邮箱',
    `phone_number` VARCHAR(32)           DEFAULT NULL COMMENT '手机号',
    `gender`       CHAR(1)               DEFAULT NULL COMMENT '用户性别 0-男 1-女 2-未知',
    `avatar`       VARCHAR(128)          DEFAULT NULL COMMENT '头像',
    `user_type`    CHAR(1)      NOT NULL DEFAULT '1' COMMENT '用户类型 0-管理员 1-普通用户',
    `create_by`    BIGINT(20) DEFAULT NULL COMMENT '创建人的用户id',
    `create_time`  DATETIME              DEFAULT NULL COMMENT '创建时间',
    `update_by`    BIGINT(20) DEFAULT NULL COMMENT '更新人',
    `update_time`  DATETIME              DEFAULT NULL COMMENT '更新时间',
    `del_flag`     INT(11) DEFAULT '0' COMMENT '逻辑删除 0-未删除 1-已删除',
    PRIMARY KEY (`sys_user_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户表'

-- 角色表
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `name`        varchar(128) DEFAULT NULL COMMENT '角色名称',
    `role_key`    varchar(100) DEFAULT NULL COMMENT '角色权限字符串',
    `status`      char(1)      DEFAULT '0' COMMENT '角色状态 0-正常 1-停用',
    `remark`      varchar(500) DEFAULT NULL COMMENT '备注',
    `create_by`   bigint(200) DEFAULT NULL,
    `create_time` datetime     DEFAULT NULL,
    `update_by`   bigint(200) DEFAULT NULL,
    `update_time` datetime     DEFAULT NULL,
    `del_flag`    int(1) DEFAULT '0' COMMENT '逻辑删除 0-未删除 1-已删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 角色-用户关系表
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `user_id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `role_id` bigint(200) NOT NULL DEFAULT '0' COMMENT '角色id',
    PRIMARY KEY (`user_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 菜单表
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `menu_name`   varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '菜单名',
    `path`        varchar(200)         DEFAULT NULL COMMENT '路由地址',
    `component`   varchar(255)         DEFAULT NULL COMMENT '组件路径',
    `visible`     char(1)              DEFAULT '0' COMMENT '菜单状态 0-显示 1-隐藏',
    `status`      char(1)              DEFAULT '0' COMMENT '菜单状态 0-正常 1-停用',
    `perms`       varchar(100)         DEFAULT NULL COMMENT '权限标识',
    `icon`        varchar(100)         DEFAULT '#' COMMENT '菜单图标',
    `remark`      varchar(500)         DEFAULT NULL COMMENT '备注',
    `create_by`   bigint(20) DEFAULT NULL,
    `create_time` datetime             DEFAULT NULL,
    `update_by`   bigint(20) DEFAULT NULL,
    `update_time` datetime             DEFAULT NULL,
    `del_flag`    int(11) DEFAULT '0' COMMENT '逻辑删除 0-未删除 1-已删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

-- 菜单角色关系表
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `role_id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `menu_id` bigint(200) NOT NULL DEFAULT '0' COMMENT '菜单id',
    PRIMARY KEY (`role_id`, `menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
