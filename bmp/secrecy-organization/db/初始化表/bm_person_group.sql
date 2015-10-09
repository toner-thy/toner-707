/*
MySQL Data Transfer
Source Host: localhost
Source Database: bmp-new
Target Host: localhost
Target Database: bmp-new
Date: 2012-12-24 16:31:34
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for bm_person_group
-- ----------------------------
DROP TABLE IF EXISTS `bm_person_group`;
CREATE TABLE `bm_person_group` (
  `PERSON_GROUP_ID` varchar(32) NOT NULL,
  `CODE` varchar(20) default NULL COMMENT '编号',
  `NAME` varchar(70) default NULL COMMENT '名称',
  `SETUP_DATE` date default NULL COMMENT '成立日期',
  `ADJUST_DATE` date default NULL COMMENT '调整日期',
  `ORGAN_DESC` varchar(1000) default NULL COMMENT '组织描述 组成机构的主任副主任成员所在部门及职务',
  `TELEPHONE` varchar(100) default NULL COMMENT '手机号',
  `ORGAN_ID` varchar(39) default NULL COMMENT '所在单位（单位表外键）',
  `DEPARTMENT_ID` varchar(39) default NULL COMMENT '所在部门（部门表外键）',
  `CREATE_TIME` datetime default NULL COMMENT '创建时间（添加数据的时间，自动记录）',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '创建人员',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人员',
  `MODIFY_TIME` datetime default NULL COMMENT '修改时间（修改数据的时间，自动记录）',
  `SECRECY_ORGAN` varchar(32) default NULL COMMENT '保密工作机构',
  `ORGAN_PRINCIPAL` varchar(32) default NULL COMMENT '保密工作机构负责人',
  `PRINCIPAL_PHONE` varchar(20) default NULL COMMENT '负责人电话',
  `PRINCIPAL_DUTY` varchar(32) default NULL COMMENT '负责人职务',
  `GROUP_TYPE` int(11) default NULL COMMENT '分组类型1为保密组织机构2为保密委员会',
  `ADDRESS` longtext COMMENT '地址',
  `FAX` varchar(50) default NULL COMMENT '传真',
  `WEBSITE` longtext COMMENT '网址',
  `ZIPCODE` varchar(50) default NULL COMMENT '邮编',
  `REPORT_TIME` datetime default NULL COMMENT '上报时间',
  `INCEPT_TIME` datetime default NULL COMMENT '接收时间',
  `REPORT_STATE` int(11) default NULL COMMENT '1:未上报；2:已上报;3:修改',
  `REPORT_ORGAN` varchar(39) default NULL COMMENT '上报单位',
  `REPORT_ORGAN_TIME` datetime default NULL COMMENT '上报单位时间',
  `INCEPT_ORGAN_TIME` datetime default NULL COMMENT '上报单位接收时间',
  `REPORT_ORGAN_STATE` int(11) default NULL COMMENT '1:未上报；2:已上报;3:修改',
  `TRANSMIT_STATE` int(11) default NULL,
  `DOC_NO` varchar(100) DEFAULT NULL,
  PRIMARY KEY  (`PERSON_GROUP_ID`),
  KEY `FKFF5089E402B565` (`DEPARTMENT_ID`),
  KEY `FKFF50891260E254` (`ORGAN_ID`),
  KEY `FKFF5089C44B8469` (`MODIFY_PERSON`),
  KEY `FKFF50897B01EDE7` (`CREATE_PERSON`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bm_person_group_position
-- ----------------------------
DROP TABLE IF EXISTS `bm_person_group_position`;
CREATE TABLE `bm_person_group_position` (
  `PERSON_GROUP_POSITION` varchar(32) NOT NULL,
  `POSITION_NAME` varchar(30) default NULL COMMENT '职位名称',
  `ORDER_NO` int(11) default NULL COMMENT '排序',
  `GROUP_TYPE` int(11) default NULL COMMENT '分组类型1为保密组织机构2为保密委员会',
  PRIMARY KEY  (`PERSON_GROUP_POSITION`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bm_person_group_receive
-- ----------------------------
DROP TABLE IF EXISTS `bm_person_group_receive`;
CREATE TABLE `bm_person_group_receive` (
  `PERSON_GROUP_ID` varchar(39) NOT NULL COMMENT '单位保密组织机构ID',
  `RECEIVE_ORGAN_ID` varchar(39) NOT NULL COMMENT '接收单位主键ID',
  PRIMARY KEY  (`PERSON_GROUP_ID`,`RECEIVE_ORGAN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bm_person_group_relation
-- ----------------------------
DROP TABLE IF EXISTS `bm_person_group_relation`;
CREATE TABLE `bm_person_group_relation` (
  `BM_PERSON_GROUP_RELATION_ID` varchar(32) NOT NULL,
  `PERSON_ID` varchar(39) default NULL COMMENT '用户ID',
  `USER_GROUP_POSITION_ID` varchar(32) default NULL COMMENT '人员组职位ID',
  `PERSON_GROUP_RELATION_ID` varchar(32) default NULL COMMENT '保密组织ID',
  `REMARK` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY  (`BM_PERSON_GROUP_RELATION_ID`),
  KEY `FK40E164F2586286B0` (`PERSON_ID`),
  KEY `FK40E164F22AFA4060` (`USER_GROUP_POSITION_ID`),
  KEY `FK40E164F2FFB2686E` (`PERSON_GROUP_RELATION_ID`),
  KEY `FK40E164F25B87DA14` (`USER_GROUP_POSITION_ID`),
  KEY `FK40E164F24AFEF622` (`PERSON_GROUP_RELATION_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bm_secrecy_committee
-- ----------------------------
DROP TABLE IF EXISTS `bm_secrecy_committee`;
CREATE TABLE `bm_secrecy_committee` (
  `SECRECY_COMMITTEE_ID` varchar(39) NOT NULL,
  `NAME` varchar(100) default NULL,
  `DOC_NO` varchar(100) default NULL,
  `SETUP_DATE` datetime default NULL,
  `DUTY_MEMBER_WORK` varchar(1000) default NULL,
  `DISTRICT` varchar(39) NOT NULL,
  `CREATE_TIME` datetime default NULL COMMENT '创建时间',
  `CREATE_USER` varchar(39) default NULL COMMENT '创建用户',
  `CREATE_ORGAN` varchar(39) default NULL COMMENT '创建单位',
  `MODIFY_TIME` datetime default NULL COMMENT '修改时间',
  `MODIFY_USER` varchar(39) default NULL COMMENT '修改用户',
  `MODIFY_ORGAN` varchar(39) default NULL COMMENT '修改单位',
  `ENABLE` int(11) NOT NULL COMMENT '0:未启用;1:启用',
  PRIMARY KEY  (`SECRECY_COMMITTEE_ID`),
  KEY `FK80F8B0447E95F583` (`DISTRICT`),
  CONSTRAINT `FK80F8B0447E95F583` FOREIGN KEY (`DISTRICT`) REFERENCES `sys_district` (`district_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='各级保密委';

-- ----------------------------
-- Table structure for bm_secrecy_committee_members
-- ----------------------------
DROP TABLE IF EXISTS `bm_secrecy_committee_members`;
CREATE TABLE `bm_secrecy_committee_members` (
  `SECRECY_COMMITTEE_MEMBER_ID` varchar(39) NOT NULL,
  `SECRECY_COMMITTEE` varchar(39) default NULL,
  `PERSON` varchar(39) default NULL,
  `PERSON_GROUP_POSITION` varchar(32) default NULL COMMENT '人员组职位ID',
  `REMARK` varchar(1000) default NULL,
  `CREATE_TIME` datetime default NULL COMMENT '创建时间',
  `CREATE_USER` varchar(39) default NULL COMMENT '创建用户',
  `CREATE_ORGAN` varchar(39) default NULL COMMENT '创建单位',
  `MODIFY_TIME` datetime default NULL COMMENT '修改时间',
  `MODIFY_USER` varchar(39) default NULL COMMENT '修改用户',
  `MODIFY_ORGAN` varchar(39) default NULL COMMENT '修改单位',
  `ENABLE` int(11) NOT NULL COMMENT '0:未启用;1:启用',
  `SECRECY_COMMITTEE_ID` varchar(39) default NULL COMMENT '保密委成员',
  PRIMARY KEY  (`SECRECY_COMMITTEE_MEMBER_ID`),
  KEY `FK_SECRECY_COMMITTEE_MEMBERS_FK2` (`SECRECY_COMMITTEE`),
  KEY `FK3C949C1E62669101` (`SECRECY_COMMITTEE_ID`),
  CONSTRAINT `bm_secrecy_committee_members_ibfk_1` FOREIGN KEY (`SECRECY_COMMITTEE`) REFERENCES `bm_secrecy_committee` (`SECRECY_COMMITTEE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK3C949C1E62669101` FOREIGN KEY (`SECRECY_COMMITTEE_ID`) REFERENCES `bm_secrecy_committee` (`SECRECY_COMMITTEE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保密委成员';

-- ----------------------------
-- Records
-- ----------------------------
INSERT INTO `bm_person_group` VALUES ('5f6b39fad20f47b288fb44a54a20c981', 'GK12', '成都市保密委员会', '1997-11-05', '2008-11-04', '', null, '402881ee264ad3d401264ad612270003', '402881ee264ad3d401264ad612460004', '2012-11-01 11:45:29', 'SYSTEM_402881ee264ad3d401264ad612270003', 'SYSTEM_402881ee264ad3d401264ad612270003', '2012-11-01 11:45:29', '保密办', '周海灵', '152028824574', '主任', '2', '', '', '', '', null, null, '1', null, null, null, null, null);
INSERT INTO `bm_person_group` VALUES ('26b514e8a5fc4b9fb863162361ea512e', 'BMWH-5010', '成都市***保密小组', '2007-09-03', '2011-11-01', '暂无', null, '1', '1', '2012-11-05 15:08:27', '1', '1', '2012-11-05 15:08:27', '保密办', '李成语', '15346814658', '处长', '1', '成都市', '028-85465841', 'www.cdXX.gov.cn', '610041', null, null, '1', null, null, null, null, null);
INSERT INTO `bm_person_group_position` VALUES ('1', '主任', '1', '2');
INSERT INTO `bm_person_group_position` VALUES ('2', '副主任', '2', '2');
INSERT INTO `bm_person_group_position` VALUES ('3', '委员', '3', '2');
INSERT INTO `bm_person_group_position` VALUES ('4', '组长', '1', '1');
INSERT INTO `bm_person_group_position` VALUES ('5', '副组长', '2', '1');
INSERT INTO `bm_person_group_position` VALUES ('6', '组员', '3', '1');
INSERT INTO `bm_person_group_relation` VALUES ('4df7e255d69b4f2f8862334a13a55cd2', '402881d4278a21a801278a5a7064003a', '1', '5f6b39fad20f47b288fb44a54a20c981');
INSERT INTO `bm_person_group_relation` VALUES ('be56a3777dda474f89ec992c5854c05b', 'ff8080813361fdbc01339fad40220049', '3', '5f6b39fad20f47b288fb44a54a20c981');
INSERT INTO `bm_person_group_relation` VALUES ('58eb89033c5e491cb51f8bbc784ee005', '1', '4', '26b514e8a5fc4b9fb863162361ea512e');
INSERT INTO `bm_secrecy_committee` VALUES ('402881e73bb75071013bb75626bd0002', '中共全国委保密委员会', '川XXX号', '2012-12-20 00:00:00', '暂无', '100000', '2012-12-20 16:02:54', '1', '1', '2012-12-23 14:19:59', '1', '1', '1');
INSERT INTO `bm_secrecy_committee_members` VALUES ('402881e73bc7082f013bc7392f810006', '402881e73bb75071013bb75626bd0002', '402881e73bc7082f013bc7392f430005', '3', '暂无撒旦飞洒地方阿斯蒂芬阿斯蒂芬啥地方士大夫22', '2012-12-23 18:05:11', '1', '1', '2012-12-23 18:33:08', '1', '1', '1', null);
