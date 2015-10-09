/*
MySQL Data Transfer
Source Host: localhost
Source Database: bmp-320
Target Host: localhost
Target Database: bmp-320
Date: 2013-2-22 14:50:58
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for m_draft_mail
-- ----------------------------
DROP TABLE IF EXISTS `m_draft_mail`;
CREATE TABLE `m_draft_mail` (
  `mail_id` varchar(39) NOT NULL DEFAULT '',
  `mail_message_id` varchar(100) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `content_url` varchar(100) DEFAULT NULL,
  `attach_url` varchar(1000) DEFAULT NULL,
  `send_person_account` varchar(100) DEFAULT NULL,
  `send_person_name` varchar(100) DEFAULT NULL,
  `send_person_organ` varchar(100) DEFAULT NULL,
  `send_person_dept` varchar(100) DEFAULT NULL,
  `receive_person_account` varchar(1000) DEFAULT NULL,
  `receive_person_name` varchar(1000) DEFAULT NULL,
  `copy_person_account` varchar(1000) DEFAULT NULL,
  `copy_person_name` varchar(1000) DEFAULT NULL,
  `send_mail_date` datetime DEFAULT NULL,
  `create_person` varchar(39) DEFAULT NULL,
  PRIMARY KEY (`mail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for m_inbox_mail
-- ----------------------------
DROP TABLE IF EXISTS `m_inbox_mail`;
CREATE TABLE `m_inbox_mail` (
  `mail_id` varchar(39) NOT NULL DEFAULT '',
  `mail_message_id` varchar(100) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `content_url` varchar(100) DEFAULT NULL,
  `attach_url` varchar(1000) DEFAULT NULL,
  `send_person_account` varchar(100) DEFAULT NULL,
  `send_person_name` varchar(100) DEFAULT NULL,
  `send_person_organ` varchar(100) DEFAULT NULL,
  `send_person_dept` varchar(100) DEFAULT NULL,
  `receive_person_account` varchar(1000) DEFAULT NULL,
  `receive_person_name` varchar(1000) DEFAULT NULL,
  `copy_person_account` varchar(1000) DEFAULT NULL,
  `copy_person_name` varchar(1000) DEFAULT NULL,
  `send_mail_date` datetime DEFAULT NULL,
  `mail_status` varchar(10) DEFAULT NULL COMMENT '邮件状态（0：未读，1：已读）',
  `create_person` varchar(39) DEFAULT NULL,
  PRIMARY KEY (`mail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for m_mail_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `m_mail_userinfo`;
CREATE TABLE `m_mail_userinfo` (
  `PERSON_ID` varchar(39) NOT NULL DEFAULT '' COMMENT '用户id',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`PERSON_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for m_send_mail
-- ----------------------------
DROP TABLE IF EXISTS `m_send_mail`;
CREATE TABLE `m_send_mail` (
  `mail_id` varchar(39) NOT NULL DEFAULT '',
  `mail_message_id` varchar(100) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `content_url` varchar(100) DEFAULT NULL,
  `attach_url` varchar(1000) DEFAULT NULL,
  `send_person_account` varchar(100) DEFAULT NULL,
  `send_person_name` varchar(100) DEFAULT NULL,
  `send_person_organ` varchar(100) DEFAULT NULL,
  `send_person_dept` varchar(100) DEFAULT NULL,
  `receive_person_account` varchar(1000) DEFAULT NULL,
  `receive_person_name` varchar(1000) DEFAULT NULL,
  `copy_person_account` varchar(1000) DEFAULT NULL,
  `copy_person_name` varchar(1000) DEFAULT NULL,
  `send_mail_date` datetime DEFAULT NULL,
  `create_person` varchar(39) DEFAULT NULL,
  PRIMARY KEY (`mail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for m_trash_mail
-- ----------------------------
DROP TABLE IF EXISTS `m_trash_mail`;
CREATE TABLE `m_trash_mail` (
  `mail_id` varchar(39) NOT NULL DEFAULT '',
  `mail_message_id` varchar(100) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `content_url` varchar(100) DEFAULT NULL,
  `attach_url` varchar(1000) DEFAULT NULL,
  `send_person_account` varchar(100) DEFAULT NULL,
  `send_person_name` varchar(100) DEFAULT NULL,
  `send_person_organ` varchar(100) DEFAULT NULL,
  `send_person_dept` varchar(100) DEFAULT NULL,
  `receive_person_account` varchar(1000) DEFAULT NULL,
  `receive_person_name` varchar(1000) DEFAULT NULL,
  `copy_person_account` varchar(1000) DEFAULT NULL,
  `copy_person_name` varchar(1000) DEFAULT NULL,
  `send_mail_date` datetime DEFAULT NULL,
  `create_person` varchar(39) DEFAULT NULL,
  PRIMARY KEY (`mail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
