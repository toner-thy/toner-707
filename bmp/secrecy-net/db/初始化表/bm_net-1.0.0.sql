/*
MySQL Data Transfer
Source Host: localhost
Source Database: d
Target Host: localhost
Target Database: d
Date: 2013-5-18 20:43:39
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for bm_net_basic
-- ----------------------------
DROP TABLE IF EXISTS `bm_net_basic`;
CREATE TABLE `bm_net_basic` (
  `id` varchar(39) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `secrecy_level` int(11) DEFAULT NULL,
  `basic` int(11) DEFAULT NULL,
  `basic_explanation` varchar(100) DEFAULT NULL,
  `safe_secrecy_organ` varchar(100) DEFAULT NULL,
  `work_maintain_organ` varchar(100) DEFAULT NULL,
  `three_people` int(11) DEFAULT NULL,
  `main_system` varchar(100) DEFAULT NULL,
  `user_num` int(11) DEFAULT NULL,
  `net_range` varchar(1000) DEFAULT NULL,
  `net_terminal_gh_num` int(11) DEFAULT NULL,
  `net_terminal_jr_num` int(11) DEFAULT NULL,
  `cushi_idcard` int(11) DEFAULT NULL,
  `cushi_visit_control` int(11) DEFAULT NULL,
  `cushi_process_control` int(11) DEFAULT NULL,
  `cushi_safe` int(11) DEFAULT NULL,
  `cushi_bianjie` int(11) DEFAULT NULL,
  `cushi_password` int(11) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `linked_no` int(11) DEFAULT NULL,
  `linked_yes` int(11) DEFAULT NULL,
  `linked_yes_content` varchar(500) DEFAULT NULL,
  `safe_secrecy_ceping` int(11) DEFAULT NULL,
  `audit` int(11) DEFAULT NULL,
  `create_organ` varchar(39) NOT NULL,
  `create_department` varchar(39) NOT NULL,
  `create_person` varchar(39) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_person` varchar(39) NOT NULL,
  `modify_time` datetime DEFAULT NULL,
  `report_organ` varchar(39) NOT NULL,
  `report_user` varchar(39) NOT NULL,
  `report_date` datetime NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bm_net_secrecy_advice
-- ----------------------------
DROP TABLE IF EXISTS `bm_net_secrecy_advice`;
CREATE TABLE `bm_net_secrecy_advice` (
  `id` varchar(39) NOT NULL COMMENT 'id',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `organ_id` varchar(39) DEFAULT NULL COMMENT '单位',
  `report_user` varchar(39) DEFAULT NULL COMMENT '填表人',
  `report_time` date DEFAULT NULL COMMENT '填报日期',
  `question` varchar(2000) DEFAULT NULL COMMENT '存在的其他问题',
  `advise` varchar(2000) DEFAULT NULL COMMENT '对策和建议',
  `create_organ` varchar(39) NOT NULL,
  `create_person` varchar(39) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_person` varchar(39) NOT NULL,
  `modify_time` datetime DEFAULT NULL,
  `create_department` varchar(39) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bm_net_secrecy_found_train
-- ----------------------------
DROP TABLE IF EXISTS `bm_net_secrecy_found_train`;
CREATE TABLE `bm_net_secrecy_found_train` (
  `ID` varchar(39) NOT NULL,
  `year` int(11) NOT NULL,
  `REPORT_USER` varchar(39) NOT NULL,
  `REPORT_DATE` datetime NOT NULL,
  `REPORT_DEPARTMENT` varchar(39) DEFAULT NULL COMMENT '部门名称',
  `REPORT_ORGAN` varchar(39) NOT NULL,
  `SECRECY_CADRE_NUM` int(11) DEFAULT NULL,
  `DOCTOR_NUM` int(11) DEFAULT NULL,
  `MASTER_NUM` int(11) DEFAULT NULL,
  `UNDERGRADUATE_NUM` int(11) DEFAULT NULL,
  `JUNIOR_NUM` int(11) DEFAULT NULL,
  `SIGNAL_COMMU_NUM` int(11) DEFAULT NULL,
  `OTHER_NUM` int(11) DEFAULT NULL,
  `LESS_THAN_NUM` int(11) DEFAULT NULL,
  `GREATE_THAN_NUM` int(11) DEFAULT NULL,
  `PROBLEM_ADVICE` varchar(4000) DEFAULT NULL,
  `CREATE_PERSON` varchar(39) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `MODIFY_TIME` datetime DEFAULT NULL,
  `MODIFY_PERSON` varchar(39) DEFAULT NULL,
  `CREATE_DEPARTMENT` varchar(39) NOT NULL,
  `CREATE_ORGAN` varchar(39) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='省直机关保密队伍建设和保密培训情况统计表';

-- ----------------------------
-- Table structure for bm_net_secrecy_found_train_content
-- ----------------------------
DROP TABLE IF EXISTS `bm_net_secrecy_found_train_content`;
CREATE TABLE `bm_net_secrecy_found_train_content` (
  `ID` varchar(39) NOT NULL,
  `YEAR` int(11) DEFAULT NULL,
  `ORGAN` varchar(39) DEFAULT NULL,
  `SECRITY_TRAIN_NUM` int(11) DEFAULT NULL,
  `TRAIN_NUM` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='BM_NET_SECRITY_FOUND_TRAIN 培训情况内容表';

-- ----------------------------
-- Table structure for bm_net_secrecy_supervision
-- ----------------------------
DROP TABLE IF EXISTS `bm_net_secrecy_supervision`;
CREATE TABLE `bm_net_secrecy_supervision` (
  `ID` varchar(39) NOT NULL,
  `YEAR` int(11) DEFAULT NULL,
  `REPORT_ORGAN` varchar(39) NOT NULL,
  `REPORT_USER` varchar(39) NOT NULL,
  `REPORT_DATE` datetime NOT NULL,
  `REPORT_DEPARTMENT` varchar(39) DEFAULT NULL COMMENT '部门名称',
  `IN_PLATFORM_DUTY` int(11) DEFAULT NULL,
  `IN_CASE_INVESTIGATION` int(11) DEFAULT NULL,
  `IN_IMPORTANT_ORGAN_NET` int(11) DEFAULT NULL,
  `IN_SECRECY_COM_INTERNET` int(11) DEFAULT NULL,
  `IN_INTERNET_MSG_CHECK` int(11) DEFAULT NULL,
  `IN_SECRECY_CHECK` int(11) DEFAULT NULL,
  `IN_OTHER` varchar(500) DEFAULT NULL,
  `OUT_SOCIOLOGY_SUPERVISION` int(11) DEFAULT NULL,
  `OUT_IP_USER_MSG` int(11) DEFAULT NULL,
  `OUT_ORGAN_REPORT` int(11) DEFAULT NULL,
  `OUT_INTERNET_ACCESS` int(11) DEFAULT NULL,
  `OUT_OTHER` varchar(500) DEFAULT NULL,
  `NET_CHECK_TIMES` int(11) DEFAULT NULL,
  `NET_CHECK_NUM` int(11) DEFAULT NULL,
  `NET_EVALUATION_NUM` int(11) DEFAULT NULL,
  `NET_CHECK_ADVICE` varchar(4000) DEFAULT NULL,
  `NET_SECRECY_CHECK_TIMES` int(11) DEFAULT NULL,
  `NET_SECRECY_CHECK_NUM` int(11) DEFAULT NULL,
  `NET_NONE_SECRECY_NUM` int(11) DEFAULT NULL,
  `COMPUTER_SECRECY_NUM` int(11) DEFAULT NULL,
  `COMPUTER_NONE_NUM` int(11) DEFAULT NULL,
  `COMPUTER_INTERNET_NUM` int(11) DEFAULT NULL,
  `STORAGE_SECRECY_NUM` int(11) DEFAULT NULL,
  `STORAGE_NONE_NUM` int(11) DEFAULT NULL,
  `ERR_COMPUTER_INTERNET_NUM` int(11) DEFAULT NULL,
  `ERR_INTERNET_MSG_NUM` int(11) DEFAULT NULL,
  `ERR_STORAGE_EXCHANGE_USE_NUM` int(11) DEFAULT NULL,
  `ERR_PEOPLE_PUNISHMENT` int(11) DEFAULT NULL,
  `ERR_OTHER_ADVICE` varchar(4000) DEFAULT NULL,
  `ILLEGAL_DEAL_MSG` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `WARNING_OTHER_ADVICE` varchar(4000) DEFAULT NULL,
  `WARNING_MSG_NUM` int(11) DEFAULT NULL,
  `WEB_SECRECY_MSG_NUM` int(11) DEFAULT NULL,
  `ERR_COMPUTER_TROJAN_NUM` int(11) DEFAULT NULL,
  `CREATE_ORGAN` varchar(39) NOT NULL,
  `CREATE_PERSON` varchar(39) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `MODIFY_PERSON` varchar(39) NOT NULL,
  `MODIFY_TIME` datetime DEFAULT NULL,
  `CREATE_DEPARTMENT` varchar(39) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区、县（市）网络保密监督管理工作机制及制度建设情况统计表';

-- ----------------------------
-- Table structure for bm_net_secrecy_supervision_content
-- ----------------------------
DROP TABLE IF EXISTS `bm_net_secrecy_supervision_content`;
CREATE TABLE `bm_net_secrecy_supervision_content` (
  `ID` varchar(39) NOT NULL,
  `YEAR` int(11) DEFAULT NULL,
  `ORGAN` varchar(39) DEFAULT NULL,
  `BUDGET` double(20,2) DEFAULT NULL,
  `ACTUAL_USE` double(20,2) DEFAULT NULL,
  `BUY_CHECK_TOOLS` int(11) DEFAULT NULL,
  `PLATFORM_CONSTRUCTION` int(11) DEFAULT NULL,
  `BUSINESS_TRAIN` int(11) DEFAULT NULL,
  `NETWORK_EVALUATION` int(11) DEFAULT NULL,
  `OTHER` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区、县（市）网络保密监督管理工作机制及制度建设情况统计表（子表）';

-- ----------------------------
-- Table structure for bm_net_secrecy_technology_train
-- ----------------------------
DROP TABLE IF EXISTS `bm_net_secrecy_technology_train`;
CREATE TABLE `bm_net_secrecy_technology_train` (
  `ID` varchar(39) NOT NULL,
  `YEAR` int(4) DEFAULT NULL,
  `REPORT_ORGAN` varchar(39) NOT NULL,
  `REPORT_DEPARTMENT` char(39) DEFAULT NULL COMMENT '部门名称',
  `REPORT_USER` varchar(39) NOT NULL,
  `CREATE_PERSON` varchar(39) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `MODIFY_TIME` datetime DEFAULT NULL,
  `MODIFY_PERSON` varchar(39) NOT NULL,
  `CREATE_DEPARTMENT` varchar(39) DEFAULT NULL,
  `CREATE_ORGAN` varchar(39) NOT NULL,
  `REPORT_DATE` datetime NOT NULL,
  `PROBLEM_ADVICE` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区、县（市）保密技术培训情况统计表';

-- ----------------------------
-- Table structure for bm_net_secrecy_technology_train_content
-- ----------------------------
DROP TABLE IF EXISTS `bm_net_secrecy_technology_train_content`;
CREATE TABLE `bm_net_secrecy_technology_train_content` (
  `ID` varchar(39) NOT NULL,
  `YEAR` int(11) DEFAULT NULL,
  `ORGAN` varchar(39) DEFAULT NULL,
  `SECRITY_TECH_TRAIN_NUM` int(11) DEFAULT NULL,
  `TRAIN_NUM` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区、县（市）保密技术培训情况统计表';

-- ----------------------------
-- Table structure for bm_net_troops
-- ----------------------------
DROP TABLE IF EXISTS `bm_net_troops`;
CREATE TABLE `bm_net_troops` (
  `id` varchar(39) NOT NULL,
  `small_organ_num` int(11) DEFAULT NULL,
  `ju_plait_num` int(11) DEFAULT NULL,
  `ju_exist_num` int(11) DEFAULT NULL,
  `zx_plait_num` int(11) DEFAULT NULL,
  `zx_exist_num` int(11) DEFAULT NULL,
  `degree_bo_num` int(11) DEFAULT NULL,
  `degree_suo_num` int(11) DEFAULT NULL,
  `degree_ben_num` int(11) DEFAULT NULL,
  `degree_daz_num` int(11) DEFAULT NULL,
  `specialy_computer_num` int(11) DEFAULT NULL,
  `specialy_other_num` int(11) DEFAULT NULL,
  `age_fortyfive_down` int(11) DEFAULT NULL,
  `age_fortyfive_up` int(11) DEFAULT NULL,
  `create_organ` varchar(39) NOT NULL,
  `create_department` varchar(39) DEFAULT NULL,
  `create_person` varchar(39) NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_person` varchar(39) NOT NULL,
  `modify_time` datetime DEFAULT NULL,
  `report_organ` varchar(39) NOT NULL,
  `report_user` varchar(39) NOT NULL,
  `report_date` datetime NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bm_no_sec_net
-- ----------------------------
DROP TABLE IF EXISTS `bm_no_sec_net`;
CREATE TABLE `bm_no_sec_net` (
  `NO_SEC_NET_ID` varchar(32) NOT NULL COMMENT 'no_sec_net_id',
  `REPORT_ORGAN` varchar(39) NOT NULL COMMENT '单位',
  `REPORT_USER` varchar(39) NOT NULL COMMENT '填报人',
  `REPORT_DATE` datetime NOT NULL COMMENT '填报日期',
  `INTERNET_NUM` int(11) DEFAULT NULL COMMENT '与互联网连接的网络数量',
  `INTERNET_OA` int(11) DEFAULT NULL COMMENT '机关OA网项',
  `INTERNET_PARTY` int(11) DEFAULT NULL COMMENT '党政外网项',
  `INTERNET_OTHER` int(11) DEFAULT NULL COMMENT '其他项',
  `INTERNET_OTHER_DESC` varchar(100) DEFAULT NULL COMMENT '其他描述',
  `INTRANET_NUM` int(11) DEFAULT NULL COMMENT '与互联网物理隔离的网络数量',
  `INTERNET_COMPUTER_NUM` int(11) DEFAULT NULL COMMENT '连接互联网计算机数量',
  `INTERNET_WEBSITE_NUM` int(11) DEFAULT NULL COMMENT '互联网门户网站数量',
  `INTERNET_WEBSITE_EXIST` int(11) DEFAULT NULL COMMENT '是否存在互联网门户网站',
  `INTERNET_WEBSITE_DN` varchar(2000) DEFAULT NULL COMMENT '门户网址',
  `INTERNET_WEBSITE_IP` varchar(2000) DEFAULT NULL COMMENT '门户IP',
  `INTERNET_PORT_NUM` int(11) DEFAULT NULL COMMENT '互联网接入口数量',
  `INTERNET_PORT_IP` varchar(1000) DEFAULT NULL COMMENT '接入IP地址',
  `SERVICE_MF_DX` int(11) DEFAULT NULL COMMENT '接入服务供应商-电信',
  `SERVICE_MF_WT` int(11) DEFAULT NULL COMMENT '接入服务供应商-网通',
  `SERVICE_MF_HS` int(11) DEFAULT NULL COMMENT '接入服务供应商-华数',
  `SERVICE_MF_OTHER` int(11) DEFAULT NULL COMMENT '接入服务供应商-其他',
  `SERVICE_MF_OTHER_DESC` varchar(100) DEFAULT NULL COMMENT '接入服务供应商-其他描述',
  `YEAR` int(11) DEFAULT NULL COMMENT '年份',
  `CREATE_ORGAN` varchar(39) NOT NULL COMMENT 'create_organ',
  `CREATE_PERSON` varchar(39) NOT NULL COMMENT 'create_person',
  `CREATE_TIME` datetime NOT NULL COMMENT 'create_time',
  `MODIFY_PERSON` varchar(39) NOT NULL COMMENT 'modify_person',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT 'modify_time',
  `CREATE_DEPARTMENT` varchar(39) DEFAULT NULL COMMENT 'create_department',
  PRIMARY KEY (`NO_SEC_NET_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='市直机关非涉密网络基本情况统计表';

-- ----------------------------
-- Table structure for bm_no_sec_net_intranet
-- ----------------------------
DROP TABLE IF EXISTS `bm_no_sec_net_intranet`;
CREATE TABLE `bm_no_sec_net_intranet` (
  `INTRANET_ID` varchar(32) NOT NULL COMMENT 'intranet_id',
  `NO_SEC_NET_ID` varchar(32) DEFAULT NULL COMMENT 'no_sec_net_id',
  `NAME` varchar(200) DEFAULT NULL COMMENT '网络名称',
  `USER_NUM` int(11) DEFAULT NULL COMMENT '用户数量',
  `USE_RANGE` varchar(200) DEFAULT NULL COMMENT '使用范围',
  `PURPOSE` varchar(200) DEFAULT NULL COMMENT '用途',
  `SORT` int(11) DEFAULT NULL COMMENT 'sort',
  PRIMARY KEY (`INTRANET_ID`),
  KEY `FK_NO_SEC_NET_INTRANET_FK1` (`NO_SEC_NET_ID`),
  CONSTRAINT `FK_NO_SEC_NET_INTRANET_FK1` FOREIGN KEY (`NO_SEC_NET_ID`) REFERENCES `bm_no_sec_net` (`NO_SEC_NET_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='与互联网物理隔离的网络数量';

-- ----------------------------
-- Table structure for bm_sec_computer_3i1
-- ----------------------------
DROP TABLE IF EXISTS `bm_sec_computer_3i1`;
CREATE TABLE `bm_sec_computer_3i1` (
  `ID` varchar(32) NOT NULL COMMENT 'id',
  `REPORT_ORGAN` varchar(39) NOT NULL COMMENT '单位',
  `REPORT_USER` varchar(39) NOT NULL COMMENT '填报人',
  `REPORT_DATE` datetime NOT NULL COMMENT '填报日期',
  `DEPARTMENT_NAME` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `SECRECY_LEVEL` int(11) DEFAULT NULL COMMENT '密级',
  `USE_DUTY_PERSON` varchar(100) DEFAULT NULL COMMENT '使用管理责任人',
  `DISK_SN` varchar(100) DEFAULT NULL COMMENT '硬盘序列号',
  `INSTALL_3I1` int(11) DEFAULT NULL COMMENT '是否安装三合一',
  `YEAR` int(11) DEFAULT NULL COMMENT '年份',
  `SORT` int(11) DEFAULT NULL COMMENT 'sort',
  `CREATE_ORGAN` varchar(39) NOT NULL COMMENT 'create_organ',
  `CREATE_PERSON` varchar(39) NOT NULL COMMENT 'create_person',
  `CREATE_TIME` datetime NOT NULL COMMENT 'create_time',
  `MODIFY_PERSON` varchar(39) NOT NULL COMMENT 'modify_person',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT 'modify_time',
  `CREATE_DEPARTMENT` varchar(39) DEFAULT NULL COMMENT 'create_department',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='市直机关涉密计算机及“三合一”安装统计表';

-- ----------------------------
-- Table structure for bm_tec_tool_info
-- ----------------------------
DROP TABLE IF EXISTS `bm_tec_tool_info`;
CREATE TABLE `bm_tec_tool_info` (
  `TEC_TOOL_INFO_ID` varchar(32) NOT NULL COMMENT 'id',
  `REPORT_ORGAN` varchar(39) NOT NULL COMMENT '单位',
  `REPORT_USER` varchar(39) NOT NULL COMMENT '填报人',
  `REPORT_DATE` datetime NOT NULL COMMENT '填报日期',
  `IMP_ORG_CHECK_NUM` int(11) DEFAULT NULL COMMENT '重要单位接入口保密检查平台纳入监管单位数量',
  `TOOL_OTHER_DESC` varchar(1000) DEFAULT NULL COMMENT '检查工具-其他工具描述',
  `ORG_WEBSITE_CHECK_NUM` int(11) DEFAULT NULL COMMENT '机关单位网站保密检查平台纳入监管门户网站数量',
  `TOOL_COMPUTER` int(11) DEFAULT NULL COMMENT '检查工具-计算机检查工具',
  `INTERNET_CONNECT_PLAT_NUM` int(11) DEFAULT NULL COMMENT '涉密计算机违规外链监控平台',
  `YEAR` int(11) DEFAULT NULL COMMENT '年份',
  `CREATE_ORGAN` varchar(39) NOT NULL COMMENT 'create_organ',
  `CREATE_PERSON` varchar(39) NOT NULL COMMENT 'create_person',
  `CREATE_TIME` datetime NOT NULL COMMENT 'create_time',
  `MODIFY_PERSON` varchar(39) NOT NULL COMMENT 'modify_person',
  `CREATE_DEPARTMENT` varchar(39) DEFAULT NULL COMMENT 'create_department',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT 'modify_time',
  `TOOL_OTHER` int(11) DEFAULT NULL COMMENT '检查工具-其他工具',
  `TOOL_SIGNAL` int(11) DEFAULT NULL COMMENT '检查工具-电磁信号检查工具',
  `TOOL_NET_TEST` int(11) DEFAULT NULL COMMENT '检查工具-网络评测工具',
  `TOOL_COCKHORSE` int(11) DEFAULT NULL COMMENT '检查工具-木马检查工具',
  `TOOL_NET` int(11) DEFAULT NULL COMMENT '检查工具-网络检查工具',
  `TOOL_COMPUTER_NUM` int(11) DEFAULT NULL COMMENT '检查工具-计算机检查工具数量',
  `TOOL_NET_NUM` int(11) DEFAULT NULL COMMENT '检查工具-网络检查工具数量',
  `TOOL_COCKHORSE_NUM` int(11) DEFAULT NULL COMMENT '检查工具-木马检查工具数量',
  `TOOL_NET_TEST_NUM` int(11) DEFAULT NULL COMMENT '检查工具-网络评测工具数量',
  `TOOL_SIGNAL_NUM` int(11) DEFAULT NULL COMMENT '检查工具-电磁信号检查工具数量',
  PRIMARY KEY (`TEC_TOOL_INFO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='技术手段情况统计表';

-- ----------------------------
-- Records 
-- ----------------------------
