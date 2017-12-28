/*
Navicat MySQL Data Transfer

Source Server         : localhost_pms
Source Server Version : 50633
Source Host           : localhost:3306
Source Database       : MyCalendar

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2017-12-28 18:43:02
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(8) NOT NULL,
  `loginName` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `tel` varchar(14) NOT NULL,
  `email` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '吾同树', 'jin', 'E10ADC3949BA59ABBE56E057F20F883E', '15102751852', 'jins321@gmail.com', '2014-04-26 22:07:37');
INSERT INTO `user` VALUES ('2', '天仙姐姐', 'jie', 'E10ADC3949BA59ABBE56E057F20F883E', '15105201314', '5201314@qq.com', '2014-04-26 22:07:37');
