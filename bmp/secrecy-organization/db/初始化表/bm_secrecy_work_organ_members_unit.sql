/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : bmp-test-platform-171

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2013-01-07 14:57:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bm_secrecy_work_organ_members_unit`
-- ----------------------------
DROP TABLE IF EXISTS `bm_secrecy_work_organ_members_unit`;
CREATE TABLE `bm_secrecy_work_organ_members_unit` (
  `SECRECY_WORK_ORGAN_MEMBERS_ID` varchar(39) NOT NULL,
  `PERSON_ID` varchar(39) DEFAULT NULL,
  `REMARK` varchar(1000) DEFAULT NULL,
  `PERSON_GROUP_ID` varchar(39) DEFAULT NULL,
  PRIMARY KEY (`SECRECY_WORK_ORGAN_MEMBERS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='办公室(保密局)人员列表; InnoDB free: 5120 kB; (`SE';

-- ----------------------------
-- Records of bm_secrecy_work_organ_members_unit
-- ----------------------------