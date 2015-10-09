/*
MySQL Data Transfer
Source Host: localhost
Source Database: 2
Target Host: localhost
Target Database: 2
Date: 2013-7-30 11:19:51
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for bm_case_disclosesecrecy
-- ----------------------------
DROP TABLE IF EXISTS `bm_case_disclosesecrecy`;
CREATE TABLE `bm_case_disclosesecrecy` (
  `disclosesecrecycase_id` varchar(39) character set latin1 NOT NULL COMMENT '泄密案件id',
  `disclosesecrecycase_name` varchar(300) default NULL COMMENT '泄密案件名称',
  `deal_result` int(11) default NULL COMMENT '查处结果(1已查处，0未查处)',
  `secrecy_level` int(11) default NULL COMMENT '密级(3秘密，2机密，1绝密)',
  `case_kind` int(11) NOT NULL default '0' COMMENT '案件性质(1故意泄密，2过失泄密)',
  `case_type` int(11) default NULL COMMENT '发案形式',
  `duty_organ_kind` int(11) default NULL COMMENT '责任单位性质',
  `depart_id` varchar(39) character set latin1 default NULL COMMENT '部门名称',
  `create_person` varchar(39) character set latin1 default NULL COMMENT '创建人员',
  `create_time` datetime default NULL COMMENT '创建时间',
  `modify_person` varchar(39) character set latin1 default NULL COMMENT '修改人员',
  `modify_time` datetime default NULL COMMENT '修改时间',
  `state` int(11) default '0' COMMENT '状态',
  `secrecy_status` int(11) unsigned default '0' COMMENT '涉密状态',
  `create_organ_id` varchar(39) character set latin1 default NULL,
  PRIMARY KEY  (`disclosesecrecycase_id`,`case_kind`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='泄密案件';

