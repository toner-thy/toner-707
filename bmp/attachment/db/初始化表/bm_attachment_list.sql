-- 宋亚非 2014.04.25
-- 登录页附件下载管理表
-- ----------------------------
-- Table structure for bm_attachment_list
-- ----------------------------
DROP TABLE IF EXISTS `bm_attachment_list`;
CREATE TABLE `bm_attachment_list` (
  `ATTACHMENT_LIST_ID` varchar(39) NOT NULL,
  `ATTACHMENT_LIST_NAME` varchar(200) default NULL,
  `ATTACHMENT_LIST_STATUS` int(11) default NULL,
  `CREATE_PERSON` varchar(39) default NULL,
  `CREATE_TIME` datetime default NULL,
  `MODIFY_PERSON` varchar(39) default NULL,
  `MODIFY_TIME` datetime default NULL,
  PRIMARY KEY  (`ATTACHMENT_LIST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;