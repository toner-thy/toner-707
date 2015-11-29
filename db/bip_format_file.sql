/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : bip

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-11-29 11:59:18
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `bip_format_file`
-- ----------------------------
DROP TABLE IF EXISTS `bip_format_file`;
CREATE TABLE `bip_format_file` (
  `id` varchar(39) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `format_file_url` varchar(500) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '是否发布',
  `organ_id` varchar(39) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_person` varchar(39) DEFAULT NULL,
  `MODIFY_PERSON` varchar(39) DEFAULT NULL,
  `MODIFY_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bip_format_file
-- ----------------------------
INSERT INTO bip_format_file VALUES ('cab40c700853490fa1b69d93e556bdc0', '4', 'D:/bip/upload/2015/11/29/39c444ac428e4107b56eabbc3ebbc03f', '1', '1', '2015-11-29 11:47:25', null, null, '2015-11-29 11:51:18');

-- ----------------------------
-- Table structure for `bip_format_file_userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `bip_format_file_userinfo`;
CREATE TABLE `bip_format_file_userinfo` (
  `id` varchar(39) NOT NULL DEFAULT '',
  `formatFile_id` varchar(39) DEFAULT NULL,
  `formatFile_name` varchar(500) DEFAULT NULL,
  `userInfo_id` varchar(39) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '状态：1查看0未查看',
  `viewTime` datetime DEFAULT NULL COMMENT '查看时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bip_format_file_userinfo
-- ----------------------------
INSERT INTO bip_format_file_userinfo VALUES ('402847165151537d0151515a64f40001', 'cab40c700853490fa1b69d93e556bdc0', '4', '1', '1', '2015-11-29 11:50:38');
INSERT INTO bip_format_file_userinfo VALUES ('402847165151537d0151515dc53a0002', 'cab40c700853490fa1b69d93e556bdc0', '4', '5', '1', '2015-11-29 11:53:13');
