/*
MySQL Data Transfer
Source Host: localhost
Source Database: bmp
Target Host: localhost
Target Database: bmp
Date: 2013-4-12 9:02:11
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for bm_technic_equipment_number
-- ----------------------------
DROP TABLE IF EXISTS `bm_technic_equipment_number`;
CREATE TABLE `bm_technic_equipment_number` (
  `TECHNIC_EQUIPMENT_NUMBER_ID` varchar(32) NOT NULL,
  `TECHNIC_EQUIPMENT_TYPE_ID` varchar(32) DEFAULT NULL COMMENT '技术设备类型',
  `TECHNIC_EQUIPMENT_NUMBER` int(11) DEFAULT NULL COMMENT '数量',
  `organ_id` varchar(39) DEFAULT NULL COMMENT '单位ID',
  `department_id` varchar(39) DEFAULT NULL COMMENT '部门ID',
  `modify_person` varchar(39) DEFAULT NULL COMMENT '修改人员',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `create_person` varchar(39) DEFAULT NULL COMMENT '输入人员',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '输入时间',
  `REPORT_TIME` datetime DEFAULT NULL COMMENT '上报时间',
  `INCEPT_TIME` datetime DEFAULT NULL COMMENT '接收时间',
  `REPORT_STATE` int(11) DEFAULT NULL COMMENT '1:未上报；2:上报;3:修改',
  `REPORT_ORGAN` varchar(255) DEFAULT NULL COMMENT '上报单位',
  `REPORT_ORGAN_TIME` datetime DEFAULT NULL COMMENT '接收单位上报时间',
  `INCEPT_ORGAN_TIME` datetime DEFAULT NULL COMMENT '接收单位接收时间',
  `REPORT_ORGAN_STATE` int(11) DEFAULT NULL COMMENT '接收单位状态1:未上报；2:上报;3:修改',
  `TRANSMIT_STATE` int(11) DEFAULT NULL,
  PRIMARY KEY (`TECHNIC_EQUIPMENT_NUMBER_ID`),
  KEY `FK3E4ABD7BE402B565` (`department_id`),
  KEY `FK3E4ABD7B1260E254` (`organ_id`),
  KEY `FK3E4ABD7BC44B8469` (`modify_person`),
  KEY `FK3E4ABD7B7B01EDE7` (`create_person`),
  KEY `FK3E4ABD7BA721236D` (`TECHNIC_EQUIPMENT_TYPE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

