/*
Navicat MySQL Data Transfer

Source Server         : k1
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : quanxian

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-06 11:16:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_function`;
CREATE TABLE `sys_function` (
  `function_id` int(11) NOT NULL AUTO_INCREMENT,
  `function_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`function_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_function
-- ----------------------------
INSERT INTO `sys_function` VALUES ('1', '整体功能操作');
INSERT INTO `sys_function` VALUES ('2', '整体资源操作');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) DEFAULT NULL,
  `descritpion` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `sys_function_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'ROLE_HOME', 'home', '/', null, 'GET', null);
INSERT INTO `sys_permission` VALUES ('2', 'ROLE_ADMIN', 'ABel', '/admin', null, 'POST', null);
INSERT INTO `sys_permission` VALUES ('3', 'ROLE_USER_GET', 'user', '/user/**', null, 'GET', null);
INSERT INTO `sys_permission` VALUES ('4', 'ROLE_USER_POST', 'user', '/user/**', null, 'POST', null);
INSERT INTO `sys_permission` VALUES ('5', 'ROLE_USER_PUT', 'user', '/user/**', null, 'PUT', null);
INSERT INTO `sys_permission` VALUES ('6', 'ROLE_USER_ALL', 'user', '/user/**', null, 'ALL', null);
INSERT INTO `sys_permission` VALUES ('7', '功能资源查询', null, '/api/permissionFunction', null, 'GET', '1');
INSERT INTO `sys_permission` VALUES ('8', '功能插入', null, '/api/function', null, 'POST', '1');
INSERT INTO `sys_permission` VALUES ('9', '功能更新', null, '/api/function', null, 'PUT', '1');
INSERT INTO `sys_permission` VALUES ('10', '功能删除', null, '/api/function', null, 'DELETE', '1');
INSERT INTO `sys_permission` VALUES ('11', '资源插入', null, '/api/permission', null, 'GET', '2');
INSERT INTO `sys_permission` VALUES ('12', '资源更新', null, '/api/permission', null, 'PUT', '2');
INSERT INTO `sys_permission` VALUES ('13', '资源删除', null, '/api/permission', null, 'DELETE', '2');
INSERT INTO `sys_permission` VALUES ('14', '资源历史', null, '/api/history', null, 'GET', '1');

-- ----------------------------
-- Table structure for sys_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_role`;
CREATE TABLE `sys_permission_role` (
  `permission_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_role_id` int(11) DEFAULT NULL,
  `sys_permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`permission_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission_role
-- ----------------------------
INSERT INTO `sys_permission_role` VALUES ('1', '1', '1');
INSERT INTO `sys_permission_role` VALUES ('2', '1', '2');
INSERT INTO `sys_permission_role` VALUES ('4', '1', '6');
INSERT INTO `sys_permission_role` VALUES ('5', '2', '1');
INSERT INTO `sys_permission_role` VALUES ('6', '2', '3');
INSERT INTO `sys_permission_role` VALUES ('7', '1', '7');
INSERT INTO `sys_permission_role` VALUES ('8', '1', '8');
INSERT INTO `sys_permission_role` VALUES ('9', '1', '9');
INSERT INTO `sys_permission_role` VALUES ('10', '1', '10');
INSERT INTO `sys_permission_role` VALUES ('11', '1', '11');
INSERT INTO `sys_permission_role` VALUES ('12', '1', '12');
INSERT INTO `sys_permission_role` VALUES ('13', '1', '13');
INSERT INTO `sys_permission_role` VALUES ('14', '1', '14');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_USER');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `role_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_user_id` int(11) DEFAULT NULL,
  `sys_role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '1', '1');
INSERT INTO `sys_role_user` VALUES ('2', '2', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '6d789d4353c72e4f625d21c6b7ac2982');
INSERT INTO `sys_user` VALUES ('2', 'abel', '583299e871fe3e6db8d2f98285a721ad');
