/*
Navicat MySQL Data Transfer

Source Server         : localhost_pms
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : MyCalendar

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2017-12-28 15:07:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for leader_info
-- ----------------------------
DROP TABLE IF EXISTS `leader_info`;
CREATE TABLE `leader_info` (
  `leaderid` int(11) NOT NULL,
  `leader_name` varchar(255) DEFAULT NULL,
  `leader_position` varchar(255) DEFAULT NULL,
  `leader_age` int(11) DEFAULT NULL,
  `leader_sex` varchar(255) DEFAULT NULL,
  `isdelete` int(11) DEFAULT NULL,
  PRIMARY KEY (`leaderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leader_info
-- ----------------------------

-- ----------------------------
-- Table structure for leader_schedule
-- ----------------------------
DROP TABLE IF EXISTS `leader_schedule`;
CREATE TABLE `leader_schedule` (
  `id` int(11) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `leaderid` int(11) DEFAULT NULL,
  `start` varchar(255) DEFAULT NULL,
  `isdelete` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leader_schedule
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `price` float NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '鱼香肉丝', '12', '1');
INSERT INTO `menu` VALUES ('2', '粉蒸排骨', '15', '2');
INSERT INTO `menu` VALUES ('3', '土豆回锅肉', '15', '3');
INSERT INTO `menu` VALUES ('4', '红烧肉', '20', '3');
INSERT INTO `menu` VALUES ('5', '韭菜炒鸡蛋', '12', '3');
INSERT INTO `menu` VALUES ('6', '青椒肉丝', '12', '1');
INSERT INTO `menu` VALUES ('7', '麻辣豆腐', '15', '1');
INSERT INTO `menu` VALUES ('8', '辣子鸡丁', '15', '1');
INSERT INTO `menu` VALUES ('9', '地三鲜', '10', '3');
INSERT INTO `menu` VALUES ('10', '剁椒鱼头', '30', '2');
INSERT INTO `menu` VALUES ('11', '毛豆牛肉', '18', '1');
INSERT INTO `menu` VALUES ('12', '千张肉丝', '15', '3');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `room_position` varchar(32) DEFAULT NULL,
  `room_size` int(11) DEFAULT NULL,
  `isdelete` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('1', '会议室一', '一楼', '3', '0');
INSERT INTO `room` VALUES ('2', '会议室二', '二楼', '2', '0');
INSERT INTO `room` VALUES ('3', '会议室三', '三楼', '5', '0');
INSERT INTO `room` VALUES ('4', '会议室四', '四楼', '20', '0');

-- ----------------------------
-- Table structure for room_schedule
-- ----------------------------
DROP TABLE IF EXISTS `room_schedule`;
CREATE TABLE `room_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(256) NOT NULL,
  `roomid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `create_date` datetime NOT NULL,
  `room_sh_status` varchar(32) DEFAULT NULL,
  `room_idea` varchar(32) DEFAULT NULL,
  `check_userid` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room_schedule
-- ----------------------------
INSERT INTO `room_schedule` VALUES ('1', '羽毛球赛程讨论会', '1', '2', '2017-12-30 16:37:12', '2017-12-30 19:22:12', '2014-04-29 00:09:20', '0', '', '');
INSERT INTO `room_schedule` VALUES ('2', '全球环境保护会议', '1', '1', '2017-12-29 13:02:12', '2017-12-29 15:32:12', '2014-04-29 00:09:20', '1', '', '1');
INSERT INTO `room_schedule` VALUES ('3', '国庆假期出游动员大会', '1', '1', '2017-12-27 09:02:12', '2017-12-27 10:02:12', '2014-04-29 00:09:20', '5', null, null);
INSERT INTO `room_schedule` VALUES ('4', 'XX项目评审', '1', '1', '2017-12-28 11:02:12', '2017-12-28 13:02:12', '2014-04-29 00:09:20', '0', null, null);
INSERT INTO `room_schedule` VALUES ('5', '测试时教大家我', '1', '1', '2017-12-29 02:20:00', '2017-12-29 02:25:00', '2017-12-28 11:44:13', '4', null, null);
INSERT INTO `room_schedule` VALUES ('6', '乱码第二个', '1', '1', '2017-12-30 05:35:00', '2017-12-30 05:40:00', '2017-12-28 11:44:26', '3', null, null);
INSERT INTO `room_schedule` VALUES ('7', '中秋团员啦', '1', '1', '2017-12-31 04:40:00', '2017-12-31 06:25:00', '2017-12-28 11:44:37', '2', '', '');
INSERT INTO `room_schedule` VALUES ('8', '会议内容r23r32', '1', '1', '2017-12-30 11:50:00', '2017-12-30 13:20:00', '2017-12-28 11:48:00', '1', null, null);
INSERT INTO `room_schedule` VALUES ('9', '会议内容', '1', '1', '2017-12-28 14:10:00', '2017-12-28 18:15:00', '2017-12-28 11:58:00', '0', '', '');

-- ----------------------------
-- Table structure for t_user_employer
-- ----------------------------
DROP TABLE IF EXISTS `t_user_employer`;
CREATE TABLE `t_user_employer` (
  `id` int(11) NOT NULL,
  `user_employer_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user_employer
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_user_menu`;
CREATE TABLE `t_user_menu` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user_menu
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_organiz
-- ----------------------------
DROP TABLE IF EXISTS `t_user_organiz`;
CREATE TABLE `t_user_organiz` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user_organiz
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_organiz_employer
-- ----------------------------
DROP TABLE IF EXISTS `t_user_organiz_employer`;
CREATE TABLE `t_user_organiz_employer` (
  `id` int(11) NOT NULL,
  `user_organiz_name` varchar(255) DEFAULT NULL,
  `user_organiz_id` int(11) DEFAULT NULL,
  `user_employer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user_organiz_employer
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_organiz_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user_organiz_user`;
CREATE TABLE `t_user_organiz_user` (
  `id` int(11) NOT NULL,
  `user_organiz_name` varchar(255) DEFAULT NULL,
  `user_user_id` int(11) DEFAULT NULL,
  `user_organiz_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user_organiz_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_role_id` int(11) DEFAULT NULL,
  `user_role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role_menu`;
CREATE TABLE `t_user_role_menu` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_role_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role_user`;
CREATE TABLE `t_user_role_user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_user_id` int(11) DEFAULT NULL,
  `user_role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(8) NOT NULL,
  `loginName` varchar(32) NOT NULL,
  `password` varchar(16) NOT NULL,
  `tel` varchar(14) NOT NULL,
  `email` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '吾同树', 'jin', '123', '15102751852', 'jins321@gmail.com', '2014-04-26 22:07:37');
INSERT INTO `user` VALUES ('2', '天仙姐姐', 'jie', '123', '15105201314', '5201314@qq.com', '2014-04-26 22:07:37');

-- ----------------------------
-- Table structure for user_menu
-- ----------------------------
DROP TABLE IF EXISTS `user_menu`;
CREATE TABLE `user_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `menuid` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `order_date` datetime NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_menu
-- ----------------------------
INSERT INTO `user_menu` VALUES ('1', '1', '1', '1', '2017-12-28 11:02:12', '2014-04-26 22:09:49');
INSERT INTO `user_menu` VALUES ('2', '1', '3', '1', '2017-12-27 11:02:12', '2014-04-26 22:09:49');
INSERT INTO `user_menu` VALUES ('3', '1', '2', '1', '2017-12-29 11:02:12', '2014-04-29 00:09:20');
INSERT INTO `user_menu` VALUES ('4', '1', '4', '2', '2017-12-28 11:02:12', '2014-04-26 22:09:49');
INSERT INTO `user_menu` VALUES ('5', '1', '5', '2', '2017-12-27 11:02:12', '2014-04-26 22:09:49');
INSERT INTO `user_menu` VALUES ('6', '1', '6', '2', '2017-12-29 11:02:12', '2014-04-29 00:09:20');
