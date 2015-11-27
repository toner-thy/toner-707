/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : bip

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-11-25 17:21:43
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
INSERT INTO bip_format_file VALUES ('145ee7c0362a4038a94c17982d01e912', 'AFFF', 'D:/bip/upload/2015/11/25/ddf2d1fc12304d9484ec93b2de060098', null, '1', '2015-11-25 11:24:58', null, null, '2015-11-25 11:24:58');
INSERT INTO bip_format_file VALUES ('273be44b650045848468472fef51f0ce', 'ADDDDFFFFF', 'D:/bip/upload/2015/11/25/6593504203434590bba28feef3a00ca8', null, '1', '2015-11-25 14:21:24', null, null, '2015-11-25 14:21:24');
INSERT INTO bip_format_file VALUES ('4231a2ad35b14631a14a9a51c528bc62', 'AAAAAAAAAA', 'D:/bip/upload/2015/11/25/2ab537b6f1e441f08d6c84f0e025b84f', null, '1', '2015-11-25 14:45:54', null, null, '2015-11-25 14:45:54');
INSERT INTO bip_format_file VALUES ('7668c50dbae349d5847e6137092eb023', '1111', 'D:/bip/upload/2015/11/25/befb28e76ab1479b97b8bae15fbd6dfc', null, '1', '2015-11-25 14:40:08', null, null, '2015-11-25 14:40:08');
INSERT INTO bip_format_file VALUES ('8f351c0b42bb4da3b1cbf8a71eb88763', '中国馆', 'D:/bip/upload/2015/11/25/75b60967083f4b88af2417a3a3a48dc9', null, '1', '2015-11-25 11:50:10', null, null, '2015-11-25 11:50:10');
INSERT INTO bip_format_file VALUES ('a8b598c176a343f88a5e5b5b0c950ad5', 'DDX', 'D:/bip/upload/2015/11/25/de79c6c351934dacbc0abf69b7a4a294', null, '1', '2015-11-25 14:43:19', null, null, '2015-11-25 14:43:19');
INSERT INTO bip_format_file VALUES ('bd9dd48634d04d94bd5bc5b08d6c9c94', '三大平台', 'D:/bip/upload/2015/11/25/3d76cda6dfc049d092af774fdcc5718d', null, '1', '2015-11-25 14:38:36', null, null, '2015-11-25 14:38:36');
INSERT INTO bip_format_file VALUES ('d6d9a91a9950465d832ad2a51d229b52', 'AAA', 'D:/bip/upload/2015/11/25/508281243a7945889a583ee1231f37f9', null, '1', '2015-11-25 11:11:27', null, null, '2015-11-25 11:11:27');

-- ----------------------------
-- Table structure for `bip_format_file_userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `bip_format_file_userinfo`;
CREATE TABLE `bip_format_file_userinfo` (
  `id` varchar(39) NOT NULL DEFAULT '',
  `formatFile_id` varchar(39) DEFAULT NULL,
  `userInfo_id` varchar(39) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '状态：1查看0未查看',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bip_format_file_userinfo
-- ----------------------------
INSERT INTO bip_format_file_userinfo VALUES ('1', '402881cd4dd1e9fb014dd1ef51430001', '5', '1');
