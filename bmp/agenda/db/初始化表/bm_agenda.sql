/*
MySQL Data Transfer
Source Host: localhost
Source Database: bmp_cd2
Target Host: localhost
Target Database: bmp_cd2
Date: 2013-1-13 15:04:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for bm_agenda
-- ----------------------------
DROP TABLE IF EXISTS `bm_agenda`;
CREATE TABLE `bm_agenda` (
  `agenda_id` varchar(39) NOT NULL,
  `agenda_title` varchar(100) DEFAULT NULL COMMENT '标题',
  `agenda_content` longtext COMMENT '内容',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `agenda_assigner` varchar(39) DEFAULT NULL COMMENT '指派人',
  `agenda_executor` varchar(39) DEFAULT NULL COMMENT '执行者',
  `create_person` varchar(39) DEFAULT NULL COMMENT '输入人员',
  `create_time` datetime DEFAULT NULL COMMENT '输入时间',
  `modify_person` varchar(39) DEFAULT NULL COMMENT '修改人员',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`agenda_id`),
  KEY `FK49E3B280C44B8469` (`modify_person`),
  KEY `FK49E3B280EEE8BA9E` (`agenda_assigner`),
  KEY `FK49E3B2807B01EDE7` (`create_person`),
  KEY `FK49E3B2807EBAA595` (`agenda_executor`),
  CONSTRAINT `FK49E3B2807B01EDE7` FOREIGN KEY (`create_person`) REFERENCES `sys_user` (`USER_ID`),
  CONSTRAINT `FK49E3B2807EBAA595` FOREIGN KEY (`agenda_executor`) REFERENCES `sys_user` (`USER_ID`),
  CONSTRAINT `FK49E3B280C44B8469` FOREIGN KEY (`modify_person`) REFERENCES `sys_user` (`USER_ID`),
  CONSTRAINT `FK49E3B280EEE8BA9E` FOREIGN KEY (`agenda_assigner`) REFERENCES `sys_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
