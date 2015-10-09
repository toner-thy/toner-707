SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bm_info
-- ----------------------------
DROP TABLE IF EXISTS `bm_info`;
CREATE TABLE `bm_info` (
  `info_id` varchar(39) NOT NULL,
  `info_type_id` varchar(39) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `source` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '草稿、待审、审核通过、审核驳回',
  `info_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `leader` varchar(39) DEFAULT NULL,
  `content` text,
  `report_phone` varchar(12) DEFAULT NULL,
  `report_organ` varchar(39) DEFAULT NULL,
  `report_time` datetime DEFAULT NULL,
  `report_type` int(11) DEFAULT NULL COMMENT '已上报、未上报',
  `create_person` varchar(39) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bm_info_log
-- ----------------------------
DROP TABLE IF EXISTS `bm_info_log`;
CREATE TABLE `bm_info_log` (
  `info_log_id` varchar(39) NOT NULL,
  `info_id` varchar(39) DEFAULT NULL,
  `operate_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `operate_organ` varchar(39) DEFAULT NULL,
  `operate_user` varchar(39) DEFAULT NULL,
  `operate_status` int(11) DEFAULT NULL COMMENT '录入、修改、审核通过、审核驳回、已上报',
  `operate_des` varchar(1000) DEFAULT NULL COMMENT '操作备注',
  PRIMARY KEY (`info_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bm_info_type
-- ----------------------------
DROP TABLE IF EXISTS `bm_info_type`;
CREATE TABLE `bm_info_type` (
  `info_type_id` varchar(39) NOT NULL,
  `name` varchar(39) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `organ_id` varchar(39) DEFAULT NULL,
  PRIMARY KEY (`info_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bm_info_type
-- ----------------------------
INSERT INTO `bm_info_type` VALUES ('1', '即时要闻', null, '1');
INSERT INTO `bm_info_type` VALUES ('10', '魅力城镇建设', null, '1');
INSERT INTO `bm_info_type` VALUES ('2', '动态新闻', null, '1');
INSERT INTO `bm_info_type` VALUES ('3', '民生保障', null, '1');
INSERT INTO `bm_info_type` VALUES ('4', '公示公告', null, '1');
INSERT INTO `bm_info_type` VALUES ('5', '领导讲话', null, '1');
INSERT INTO `bm_info_type` VALUES ('6', '重要文件', null, '1');
INSERT INTO `bm_info_type` VALUES ('7', '调查研究', null, '1');
INSERT INTO `bm_info_type` VALUES ('8', '文学写作', null, '1');
INSERT INTO `bm_info_type` VALUES ('9', '先进事迹及大项目建设', null, '1');

-- ----------------------------
-- Table structure for bm_info_warn
-- ----------------------------
DROP TABLE IF EXISTS `bm_info_warn`;
CREATE TABLE `bm_info_warn` (
  `warn_id` varchar(39) NOT NULL,
  `info_id` varchar(39) DEFAULT NULL,
  `organ_id` varchar(39) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '查看、未查看',
  `view_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `view_user` varchar(39) DEFAULT NULL,
  `report_organ` varchar(100) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `report_time` datetime DEFAULT NULL,
  PRIMARY KEY (`warn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据字典
INSERT INTO `sys_dictionary_table` VALUES ('402880964b0111eb014b012b10b90000', 0, 10, 1, 'info_report', '信息报送');

INSERT INTO `sys_dictionary_field` VALUES ('402880964b0111eb014b012b9d580001', 'info_status', '信息状态', 0, 1, '402880964b0111eb014b012b10b90000');
INSERT INTO `sys_dictionary_field` VALUES ('402880964b0111eb014b012bd7eb0002', 'info_report_status', '信息上报状态', 0, 2, '402880964b0111eb014b012b10b90000');
INSERT INTO `sys_dictionary_field` VALUES ('402880964b0111eb014b012c36280003', 'info_warn_status', '信息提醒状态', 0, 3, '402880964b0111eb014b012b10b90000');
INSERT INTO `sys_dictionary_field` VALUES ('402880964b0111eb014b012ecfed000c', 'info_operate_status', '信息操作状态', 0, 4, '402880964b0111eb014b012b10b90000');

INSERT INTO sys_dictionary_option VALUES ('402880964b0111eb014b012cc3d20004', '草稿', 1, 1, 1, '402880964b0111eb014b012b9d580001', NULL);
INSERT INTO sys_dictionary_option VALUES ('402880964b0111eb014b012ceefa0005', '待审', 2, 2, 1, '402880964b0111eb014b012b9d580001', NULL);
INSERT INTO sys_dictionary_option VALUES ('402880964b0111eb014b012d152c0006', '审核通过', 3, 3, 1, '402880964b0111eb014b012b9d580001', NULL);
INSERT INTO sys_dictionary_option VALUES ('402880964b0111eb014b012d3ad40007', '审核驳回', 4, 4, 1, '402880964b0111eb014b012b9d580001', NULL);
INSERT INTO sys_dictionary_option VALUES ('402880964b0111eb014b012d83540008', '已上报', 1, 2, 1, '402880964b0111eb014b012bd7eb0002', NULL);
INSERT INTO sys_dictionary_option VALUES ('402880964b0111eb014b012dab680009', '未上报', 0, 1, 1, '402880964b0111eb014b012bd7eb0002', NULL);
INSERT INTO sys_dictionary_option VALUES ('402880964b0111eb014b012e2ef4000a', '查看', 2, 3, 1, '402880964b0111eb014b012c36280003', NULL);
INSERT INTO sys_dictionary_option VALUES ('402880964b0111eb014b012e514f000b', '未查看', 1, 2, 1, '402880964b0111eb014b012c36280003', NULL);
INSERT INTO sys_dictionary_option VALUES ('4028c8814b064e6d014b06523e640000', '草稿', 0, 1, 1, '402880964b0111eb014b012c36280003', NULL);
INSERT INTO sys_dictionary_option VALUES ('402880964b0111eb014b012ef478000d', '录入', 1, 1, 1, '402880964b0111eb014b012ecfed000c', NULL);
INSERT INTO sys_dictionary_option VALUES ('402880964b0111eb014b012f1573000e', '修改', 3, 3, 1, '402880964b0111eb014b012ecfed000c', NULL);
INSERT INTO sys_dictionary_option VALUES ('402880964b0111eb014b012f3935000f', '审核通过', 4, 4, 1, '402880964b0111eb014b012ecfed000c', NULL);
INSERT INTO sys_dictionary_option VALUES ('402880964b0111eb014b012f57ae0010', '审核驳回', 5, 5, 1, '402880964b0111eb014b012ecfed000c', NULL);
INSERT INTO sys_dictionary_option VALUES ('402880964b0111eb014b012f7fc80011', '上报', 6, 6, 1, '402880964b0111eb014b012ecfed000c', NULL);
INSERT INTO sys_dictionary_option VALUES ('4028c8814b0b01d4014b0b057d910000', '报审', 2, 2, 1, '402880964b0111eb014b012ecfed000c', NULL);
