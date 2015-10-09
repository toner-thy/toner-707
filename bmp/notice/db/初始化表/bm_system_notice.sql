-- 宋亚非 2014.04.25
-- 登录页公告管理
-- ----------------------------
-- Table structure for bm_system_notice
-- ----------------------------
DROP TABLE IF EXISTS `bm_system_notice`;
CREATE TABLE `bm_system_notice` (
  `NOTICE_ID` varchar(39) NOT NULL,
  `NOTICE_NAME` varchar(300) default NULL,
  `NOTICE_CONTENT` varchar(4000) default NULL,
  `NOTICE_PUBLISHER` varchar(200) default NULL,
  `NOTICE_PUBLISH_DATE` datetime default NULL,
  `NOTICE_STATUS` int(11) default NULL,
  `CREATE_PERSON` varchar(39) default NULL,
  `CREATE_TIME` datetime default NULL,
  `MODIFY_PERSON` varchar(39) default NULL,
  `MODIFY_TIME` datetime default NULL,
  PRIMARY KEY  (`NOTICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;