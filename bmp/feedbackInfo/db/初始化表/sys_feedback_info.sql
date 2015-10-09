/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : 422init_create

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2013-05-16 09:07:46
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `sys_feedback_info`
-- ----------------------------
DROP TABLE IF EXISTS `sys_feedback_info`;
CREATE TABLE `sys_feedback_info` (
  `FEEDBACK_INFO_ID` varchar(39) NOT NULL default '',
  `FEEDBACK_TYPE` int(11) default NULL,
  `FEEDBACK_TITLE` varchar(100) default NULL,
  `STATUS` int(11) default NULL,
  `CONTENT` longtext,
  `DEPARTMENT` varchar(39) default NULL,
  `ORGAN_ID` varchar(39) default NULL,
  `create_person` varchar(39) default NULL,
  `create_time` datetime default NULL,
  `modify_person` varchar(39) default NULL,
  `modify_time` datetime default NULL,
  PRIMARY KEY  (`FEEDBACK_INFO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_feedback_info
-- ----------------------------
