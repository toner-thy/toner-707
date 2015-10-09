/*
MySQL Data Transfer
Source Host: localhost
Source Database: bmp
Target Host: localhost
Target Database: bmp
Date: 2013-4-12 9:40:03
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for bm_equipment_check
-- ----------------------------
DROP TABLE IF EXISTS `bm_equipment_check`;
CREATE TABLE `bm_equipment_check` (
  `equipment_check_id` varchar(39) NOT NULL,
  `equipment_name` varchar(200) DEFAULT NULL COMMENT '设备名称',
  `equipment_organ` varchar(100) DEFAULT NULL COMMENT '设备所属单位',
  `equipment_type` varchar(150) DEFAULT NULL COMMENT '设备型号',
  `equipment_number` int(11) DEFAULT NULL COMMENT '设备数量',
  `equipment_producing_area` varchar(250) DEFAULT NULL COMMENT '设备产地',
  `equipment_purpose` varchar(200) DEFAULT NULL COMMENT '用途',
  `remark` longtext COMMENT '备注',
  `check_organ` varchar(39) DEFAULT NULL COMMENT '检查单位',
  `check_person` varchar(39) DEFAULT NULL COMMENT '检查人',
  `check_time` datetime DEFAULT NULL COMMENT '检查时间',
  `check_content` longtext COMMENT '检查内容',
  `modify_person` varchar(39) DEFAULT NULL COMMENT '修改人员',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `create_person` varchar(39) DEFAULT NULL COMMENT '输入人员',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '输入时间',
  PRIMARY KEY (`equipment_check_id`),
  KEY `FK86AAB723C44B8469` (`modify_person`),
  KEY `FK86AAB723F17D445B` (`check_person`),
  KEY `FK86AAB7235EE7C65` (`check_organ`),
  KEY `FK86AAB7237B01EDE7` (`create_person`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
