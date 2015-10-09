/*
MySQL Data Transfer
Source Host: 192.168.1.90
Source Database: bmp-test-platform-171
Target Host: 192.168.1.90
Target Database: bmp-test-platform-171
Date: 2012-12-26 9:21:33
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for bm_secrecy_office
-- ----------------------------
DROP TABLE IF EXISTS `bm_secrecy_office`;
CREATE TABLE `bm_secrecy_office` (
  `SECRECY_OFFICE_ID` varchar(39) NOT NULL,
  `SECRECY_COMMITTEE_ID` varchar(39) default NULL,
  `NAME` varchar(50) default NULL,
  `DIRECTOR` varchar(39) default NULL,
  `DEPT` varchar(39) default NULL,
  `PERSON` varchar(39) default NULL,
  `TELEPHONE` varchar(20) default NULL,
  `FAX` varchar(30) default NULL,
  `POSTCODE` varchar(6) default NULL,
  `ADDRESS` varchar(100) default NULL,
  `DUTY_MEMBER_WORK` varchar(1000) default NULL,
  `CREATE_TIME` datetime default NULL COMMENT '创建时间',
  `CREATE_USER` varchar(39) default NULL COMMENT '创建用户',
  `CREATE_ORGAN` varchar(39) default NULL COMMENT '创建单位',
  `MODIFY_TIME` datetime default NULL COMMENT '修改时间',
  `MODIFY_USER` varchar(39) default NULL COMMENT '修改用户',
  `MODIFY_ORGAN` varchar(39) default NULL COMMENT '修改单位',
  `ENABLE` int(11) NOT NULL COMMENT '0:未启用;1:启用',
  PRIMARY KEY  (`SECRECY_OFFICE_ID`),
  KEY `FK_BM_SECRECY_OFFICE_FK1` (`SECRECY_COMMITTEE_ID`),
  CONSTRAINT `FK_BM_SECRECY_OFFICE_FK1` FOREIGN KEY (`SECRECY_COMMITTEE_ID`) REFERENCES `bm_secrecy_committee` (`SECRECY_COMMITTEE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='各级保密办(保密局)';

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for bm_secrecy_office_members
-- ----------------------------
DROP TABLE IF EXISTS `bm_secrecy_office_members`;
CREATE TABLE `bm_secrecy_office_members` (
  `SECRECY_OFFICE_MEMBERS_ID` varchar(39) NOT NULL,
  `SECRECY_OFFICE_ID` varchar(39) DEFAULT NULL,
  `PERSON_ID` varchar(39) DEFAULT NULL,
  `POLITICAL_STATUS` varchar(50) DEFAULT NULL,
  `FIRST_WORK_DATE` datetime DEFAULT NULL COMMENT '参加工作日期',
  `SECRECY_PERSON_LEVEL` int(11) DEFAULT NULL COMMENT '涉密程度',
  `OFFICE_PHONE` varchar(50) DEFAULT NULL,
  `POST` varchar(40) DEFAULT NULL COMMENT '岗位',
  `RESUME` varchar(2000) DEFAULT NULL COMMENT '个人简介',
  `REMARK` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`SECRECY_OFFICE_MEMBERS_ID`),
  KEY `FK_BM_SECRECY_OFFICE_MEMBERS_FK2` (`SECRECY_OFFICE_ID`),
  CONSTRAINT `FK_BM_SECRECY_OFFICE_MEMBERS_FK2` FOREIGN KEY (`SECRECY_OFFICE_ID`) REFERENCES `bm_secrecy_office` (`SECRECY_OFFICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='办公室(保密局)人员列表';
