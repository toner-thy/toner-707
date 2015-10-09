/*
Navicat MySQL Data Transfer

Source Server         : 90
Source Server Version : 50022
Source Host           : 192.168.1.90:3306
Source Database       : bmp-test-platform-171

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2012-12-26 09:23:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bm_key_part`
-- ----------------------------
DROP TABLE IF EXISTS `bm_key_part`;
CREATE TABLE `bm_key_part` (
  `KEY_PART_ID` varchar(39) NOT NULL,
  `PART_NAME` varchar(50) default NULL COMMENT '涉密部位名称',
  `DUTY_PERSON` varchar(39) default NULL COMMENT '负责人',
  `SECRECY_LEVEL` int(11) default NULL COMMENT '涉密等级（1.核心2.重要3.一般）',
  `Location` varchar(100) default NULL COMMENT '具体位置',
  `MANAGER_RULE` int(11) default NULL COMMENT '是否建立保密制度(1.是 2.不是)',
  `SEC_SCOPE` varchar(3000) default NULL COMMENT '涉密工作事项及范围',
  `SKILL_MEASURE` varchar(200) default NULL COMMENT '技防措施',
  `ORGAN_ID` varchar(39) default NULL COMMENT '单位ID',
  `CREATEPERSON` varchar(39) default NULL COMMENT '输入人员',
  `CREATE_TIME` datetime default NULL COMMENT '输入时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人员',
  `MODIFY_TIME` datetime default NULL COMMENT '修改时间',
  `DEPARTMENT_ID` varchar(39) default NULL,
  `PHONE` varchar(20) default NULL,
  PRIMARY KEY  (`KEY_PART_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bm_key_part
-- ----------------------------

-- ----------------------------
-- Table structure for `bm_key_part_person`
-- ----------------------------
DROP TABLE IF EXISTS `bm_key_part_person`;
CREATE TABLE `bm_key_part_person` (
  `PART_PERSON_ID` varchar(39) NOT NULL,
  `PERSON_ID` varchar(39) default NULL COMMENT '用户ID',
  `KEY_PART_ID` varchar(39) default NULL COMMENT '要害部位ID',
  PRIMARY KEY  (`PART_PERSON_ID`),
  KEY `FKC2153F4DFF575122` (`KEY_PART_ID`),
  CONSTRAINT `FKC2153F4DFF575122` FOREIGN KEY (`KEY_PART_ID`) REFERENCES `bm_key_part` (`KEY_PART_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bm_key_part_person
-- ----------------------------

-- ----------------------------
-- Table structure for `key_department_part`
-- ----------------------------
DROP TABLE IF EXISTS `key_department_part`;
CREATE TABLE `key_department_part` (
  `KEY_PART_ID` varchar(32) default NULL COMMENT '要害部位ID',
  `DEPARTMENT_ID` varchar(39) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部位部门中间表';

-- ----------------------------
-- Records of key_department_part
-- ----------------------------
