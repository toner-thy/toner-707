-- ******************************* 添加菜单[ 单位设备配备 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743939c7e014393c4ffb30000', 'a', '单位设备配备', '0', '', '', '1', '', '1', '402881cd36a4941c0136a4961cb90002');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743939c7e014393c4ffc20001', '1', '', '', '402881a743939c7e014393c4ffb30000');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743939c7e014393c4ffb30000', '1');

-- ******************************* 添加菜单[ 单位设备配备 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743939c7e014393ca383f0004', 'a', '单位设备配备', '-1', '', '', '1', '', '0', '402881a743939c7e014393c4ffb30000');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743ad87880143ad8a76130000', '1', '', '/bmp/devicemgr/deviceMgrAction_main.action', '402881a743939c7e014393ca383f0004');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743939c7e014393ca383f0004', '1');

-- ******************************* 添加菜单[ 设备分类管理 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743939c7e014393c81bb80002', 'a', '设备分类管理', '0', '', '', '1', '', '1', '402881a743939c7e014393c4ffb30000');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743ad928d0143adb4753c0000', '1', '', '/bmp/secrecyEquipmentCategory/secrecyEquipmentCategory_main.action', '402881a743939c7e014393c81bb80002');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743939c7e014393c81bb80002', '1');

-- ******************************* 添加菜单[ 会议资料 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743aed4670143aedc8e810000', 'a', '会议资料', '-1', '', '', '1', '', '1', '402881cd36a4941c0136a4961cb90002');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743aed4670143aedc8ea10001', '1', '', '', '402881a743aed4670143aedc8e810000');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743aed4670143aedc8e810000', '1');

-- ******************************* 添加菜单[ 会议分类 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743aed4670143aeddbca10002', 'a', '会议分类', '0', '', '', '1', '', '1', '402881a743aed4670143aedc8e810000');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743aed4670143aeddbcb10003', '1', '', '/bmp/meetingcategory/meetingCategory_list.action', '402881a743aed4670143aeddbca10002');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743aed4670143aeddbca10002', '1');

-- ******************************* 添加菜单[ 新增 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743b278330143b28b94ff0000', 'a', '新增', '1', '', '', '1', '', '1', '402881a743aed4670143aeddbca10002');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743b278330143b28b950e0001', '1', 'doAdd', '/bmp/meetingcategory/meetingCategory_addBefore.action', '402881a743b278330143b28b94ff0000');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743b278330143b28b94ff0000', '1');

-- ******************************* 添加菜单[ 编辑 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743b278330143b28d1cf60002', 'a', '编辑', '1', '', '', '1', '', '2', '402881a743aed4670143aeddbca10002');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743b278330143b28d1cf60003', '1', 'doEdit', '/bmp/meetingcategory/meetingCategory_editBefore.action', '402881a743b278330143b28d1cf60002');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743b278330143b28d1cf60002', '1');

-- ******************************* 添加菜单[ 删除 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743b278330143b28fb3220004', 'a', '删除', '1', '', '', '1', '', '3', '402881a743aed4670143aeddbca10002');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743b278330143b294643e000c', '1', 'doDel', '/bmp/meetingcategory/meetingCategory_delete.action', '402881a743b278330143b28fb3220004');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743b278330143b28fb3220004', '1');

-- ******************************* 添加菜单[ 所有会议记录 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743aed4670143aedeafcb0004', 'a', '所有会议记录', '0', '', '', '1', '', '1', '402881a743aed4670143aedc8e810000');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743aed4670143aedeafcb0005', '1', '', '/bmp/meeting/meeting_list.action', '402881a743aed4670143aedeafcb0004');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743aed4670143aedeafcb0004', '1');

-- ******************************* 添加菜单[ 新增 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743b278330143b291a2300006', 'a', '新增', '1', '', '', '1', '', '1', '402881a743aed4670143aedeafcb0004');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743b278330143b291a2300007', '1', 'doAdd', '/bmp/meeting/meeting_add.action', '402881a743b278330143b291a2300006');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743b278330143b291a2300006', '1');

-- ******************************* 添加菜单[ 编辑 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743b278330143b2929dc70008', 'a', '编辑', '1', '', '', '1', '', '2', '402881a743aed4670143aedeafcb0004');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743b278330143b2929dc70009', '1', 'doEdit', '/bmp/meeting/meeting_edit.action', '402881a743b278330143b2929dc70008');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743b278330143b2929dc70008', '1');

-- ******************************* 添加菜单[ 删除 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743b278330143b293cf03000a', 'a', '删除', '1', '', '', '1', '', '3', '402881a743aed4670143aedeafcb0004');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743b278330143b293cf03000b', '1', 'doDel', '/bmp/meeting/meeting_delete.action', '402881a743b278330143b293cf03000a');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743b278330143b293cf03000a', '1');

-- ******************************* 添加菜单[ 离退休档案 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743b784530143b7945ff70001', 'a', '离退休档案', '-1', '', '', '1', '', '1', '402881cd36a4941c0136a4961cb90002');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743b784530143b7945ff70002', '1', '', '', '402881a743b784530143b7945ff70001');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743b784530143b7945ff70001', '1');

-- ******************************* 添加菜单[ 档案列表 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743b784530143b79535870003', 'a', '档案列表', '0', '', '', '1', '', '1', '402881a743b784530143b7945ff70001');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743b784530143b79535870004', '1', '', '/bmp/retireofficial/retireofficial_list.action', '402881a743b784530143b79535870003');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743b784530143b79535870003', '1');

-- ******************************* 添加菜单[ 新增 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743b784530143b79636f90005', 'a', '新增', '1', '', '', '1', '', '1', '402881a743b784530143b79535870003');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743b784530143b79676250007', '1', 'doAdd', '/bmp/retireofficial/retireofficial_add.action', '402881a743b784530143b79636f90005');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743b784530143b79636f90005', '1');

-- ******************************* 添加菜单[ 编辑 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743b784530143b797b1740008', 'a', '编辑', '1', '', '', '1', '', '2', '402881a743b784530143b79535870003');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743b784530143b797b1830009', '1', 'doEdit', '/bmp/retireofficial/retireofficial_edit.action', '402881a743b784530143b797b1740008');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743b784530143b797b1740008', '1');

-- ******************************* 添加菜单[ 删除 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a743b784530143b7985758000a', 'a', '删除', '1', '', '', '1', '', '3', '402881a743b784530143b79535870003');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a743b784530143b7985758000b', '1', 'doDel', '/bmp/retireofficial/retireofficial_del.action', '402881a743b784530143b7985758000a');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a743b784530143b7985758000a', '1');



DROP TABLE IF EXISTS `boa_secrecy_equipment_category`;
CREATE TABLE `boa_secrecy_equipment_category` (
  `SECRECY_EQUIPMENT_CATEGORY_ID` varchar(32) NOT NULL COMMENT '主键ID',
  `PARENT_ID` varchar(32) default NULL COMMENT '父主键ID',
  `NAME` varchar(20) default NULL COMMENT '分类名称',
  `DESCRIPTION` varchar(512) default NULL COMMENT '描述',
  `ORDER_NO` int(11) default NULL COMMENT '排序值',
  `ORGAN_ID` varchar(32) default NULL COMMENT '单位ID',
  `DEPARTMENT_ID` varchar(32) default NULL COMMENT '部门ID',
  `CREATE_PERSON` varchar(32) default NULL COMMENT '输入人员',
  `CREATE_TIME` datetime default NULL COMMENT '输入时间',
  `MODIFY_PERSON` varchar(32) default NULL COMMENT '修改人员',
  `MODIFY_TIME` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`SECRECY_EQUIPMENT_CATEGORY_ID`),
  KEY `FK_PARENT_SECRECY_EQUIPMENT_CATEGORY` (`PARENT_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='分类; InnoDB free: 8192 kB; (`PARENT_ID`) REFER `bmp21/boa';
INSERT INTO boa_secrecy_equipment_category VALUES ('1', null, '分类', '分类', null, null, null, null, null, null, null);
DROP TABLE IF EXISTS `boa_equipment_trash`;
CREATE TABLE `boa_equipment_trash` (
  `EQUIPMENT_TRASH_ID` varchar(39) NOT NULL COMMENT '主键ID',
  `SECRECY_EQUIPMENT_ID` varchar(39) default NULL COMMENT '保密技术设备ID',
  `APPLY_PERSON` varchar(39) default NULL COMMENT '申请人',
  `REMARK` varchar(500) default NULL COMMENT '备注',
  `APPLY_REASON` varchar(500) default NULL COMMENT '原因',
  `TRASH_DATE` datetime default NULL COMMENT '毁销时间',
  `STATUS` int(11) default NULL,
  `ORGAN_ID` varchar(39) default NULL COMMENT '单位ID',
  `DEPARTMENT_ID` varchar(39) default NULL COMMENT '部门ID',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '输入人员',
  `CREATE_TIME` datetime default NULL COMMENT '输入时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人员',
  `MODIFY_TIME` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`EQUIPMENT_TRASH_ID`),
  KEY `FK764A44BCE402B565` (`DEPARTMENT_ID`),
  KEY `FK764A44BC1260E254` (`ORGAN_ID`),
  KEY `FK764A44BCF7D3A6E5` (`MODIFY_PERSON`),
  KEY `FK764A44BCAE8A1063` (`CREATE_PERSON`),
  KEY `FK764A44BC20A7E9B1` (`APPLY_PERSON`),
  KEY `FK764A44BC9C3E1FDC` (`SECRECY_EQUIPMENT_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='设备报废申请';
DROP TABLE IF EXISTS `boa_equipment_loan`;
CREATE TABLE `boa_equipment_loan` (
  `EQUIPMENT_LOAN_ID` varchar(39) NOT NULL COMMENT '主键ID',
  `SECRECY_EQUIPMENT_ID` varchar(39) default NULL COMMENT '保密技术设备ID',
  `LOAN_PERSON` varchar(39) default NULL COMMENT '申请人',
  `REMARK` varchar(500) default NULL COMMENT '备注',
  `LOAN_REASON` varchar(500) default NULL COMMENT '借用是由',
  `LOAN_START_TIME` datetime default NULL COMMENT '借用起始日期',
  `LOAN_END_TIME` datetime default NULL COMMENT '借用截止时间',
  `STATUS` int(11) default NULL COMMENT '0:正常，1已经归还',
  `ORGAN_ID` varchar(39) default NULL COMMENT '单位ID',
  `DEPARTMENT_ID` varchar(39) default NULL COMMENT '部门ID',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '输入人员',
  `CREATE_TIME` datetime default NULL COMMENT '输入时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人员',
  `MODIFY_TIME` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`EQUIPMENT_LOAN_ID`),
  KEY `FK87EE32ECE402B565` (`DEPARTMENT_ID`),
  KEY `FK87EE32EC1260E254` (`ORGAN_ID`),
  KEY `FK87EE32ECC44B8469` (`MODIFY_PERSON`),
  KEY `FK87EE32EC18DE51EF` (`LOAN_PERSON`),
  KEY `FK87EE32EC7B01EDE7` (`CREATE_PERSON`),
  KEY `FK87EE32EC9C3E1FDC` (`SECRECY_EQUIPMENT_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='设备借用申请';

DROP TABLE IF EXISTS `boa_secrecy_equipment`;
CREATE TABLE `boa_secrecy_equipment` (
  `SECRECY_EQUIPMENT_ID` varchar(39) NOT NULL,
  `ASSET_ID` varchar(39) default NULL,
  `SECRECY_EQUIPMENT_CATEGORY_ID` varchar(32) default NULL COMMENT '分类主键ID',
  `NAME` varchar(100) default NULL,
  `TYPE_CODE` varchar(100) default NULL,
  `CATEGORY_CODE` varchar(100) default NULL,
  `PRICE` double default NULL,
  `BUY_TIME` datetime default NULL,
  `AT_PLACE` varchar(100) default NULL,
  `USE_PERSON` varchar(39) default NULL,
  `DUTY_PERSON` varchar(39) default NULL,
  `DUTY_DEPARTMENT` varchar(39) default NULL,
  `MODIFY_TIME` datetime default NULL,
  `MODIFY_PERSON` varchar(39) default NULL,
  `CREATE_TIME` datetime default NULL,
  `CREATE_PERSON` varchar(39) default NULL,
  `DEPARTMENT_ID` varchar(39) default NULL,
  `STATUS` int(11) default NULL COMMENT '状态（正常，借出，报废等等）',
  `ORGAN_ID` varchar(39) default NULL,
  `number` int(11) default NULL,
  `unitPrice` double default NULL,
  PRIMARY KEY  (`SECRECY_EQUIPMENT_ID`),
  KEY `FKBCAE77EE402B565` (`DEPARTMENT_ID`),
  KEY `FKBCAE77E288A7C98` (`DUTY_DEPARTMENT`),
  KEY `FKBCAE77E6FAA6ACD` (`DUTY_PERSON`),
  KEY `FKBCAE77E7B01EDE7` (`CREATE_PERSON`),
  KEY `FKBCAE77EC44B8469` (`MODIFY_PERSON`),
  KEY `FKBCAE77E9478A6FC` (`USE_PERSON`),
  KEY `FKBCAE77E1260E254` (`ORGAN_ID`),
  KEY `secrecy_equipment_office_asset` (`ASSET_ID`),
  KEY `FK_SECRECY_EQUIPMENT_CATEGORY` (`SECRECY_EQUIPMENT_CATEGORY_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `bm_attend_meeting`;
CREATE TABLE `bm_attend_meeting` (
  `attend_meeting_id` varchar(255) NOT NULL,
  `ATTEND_USERINFO` varchar(255) default NULL,
  `ATTEND_ORGAN` varchar(255) default NULL,
  `ATTEND_DEPARTMENT` varchar(255) default NULL,
  `commit_Info_Time` datetime default NULL,
  `meeting_Id` varchar(39) default NULL,
  `attend_Meeting_Name` varchar(4096) default NULL,
  `attend_Meeting_Content` varchar(4096) default NULL,
  `attendMeetingId` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `organName` varchar(255) default NULL,
  `departmentName` varchar(255) default NULL,
  `commitInfoTime` datetime default NULL,
  `meetingId` varchar(39) default NULL,
  `attendMeetingName` varchar(4096) default NULL,
  `attendMeetingContent` varchar(4096) default NULL,
  PRIMARY KEY  (`attend_meeting_id`),
  KEY `FKCEB0980A1D420281` (`meeting_Id`),
  KEY `FKCEB0980A4B40A077` (`ATTEND_ORGAN`),
  KEY `FKCEB0980A855F078A` (`meeting_Id`),
  KEY `FKCEB0980A3215F69` (`ATTEND_USERINFO`),
  KEY `FKCEB0980A36357654` (`ATTEND_DEPARTMENT`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `bm_meeting`;
CREATE TABLE `bm_meeting` (
  `MEETING_ID` varchar(39) NOT NULL,
  `MEETING_NAME` varchar(128) default NULL,
  `MEETING_TIME` datetime default NULL,
  `HOLD_ORGAN` varchar(39) default NULL,
  `HOLD_DEPARTMENT` varchar(39) default NULL,
  `MEETING_DESCRIPTION` varchar(2000) default NULL,
  `DEPARTMENT_ID` varchar(39) default NULL COMMENT '部门ID',
  `ORGAN_ID` varchar(39) default NULL COMMENT '单位ID',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '输入人员',
  `CREATE_TIME` datetime default NULL COMMENT '输入时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人员',
  `MODIFY_TIME` datetime default NULL COMMENT '修改时间',
  `meetingId` varchar(39) default NULL,
  `holdDepartment` varchar(255) default NULL,
  `meetingTime` datetime default NULL,
  `meetingName` varchar(255) default NULL,
  `holdOrgan` varchar(255) default NULL,
  `meetingDescription` varchar(4096) default NULL,
  `STATUS` int(11) default NULL,
  `MEETING_CATEGORY_ID` varchar(32) default NULL,
  PRIMARY KEY  (`MEETING_ID`),
  KEY `FK69F682471260E254` (`ORGAN_ID`),
  KEY `FK69F68247C44B8469` (`MODIFY_PERSON`),
  KEY `FK69F682472F1AA70F` (`HOLD_DEPARTMENT`),
  KEY `FK69F6824770C8A6DC` (`HOLD_ORGAN`),
  KEY `FK69F682477B01EDE7` (`CREATE_PERSON`),
  KEY `FK69F6f2d7C44B8469` (`MEETING_CATEGORY_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `bm_meeting_category`;
CREATE TABLE `bm_meeting_category` (
  `MEETING_CATEGORY_ID` varchar(32) NOT NULL COMMENT '主键ID',
  `CATEGORY_NAME` varchar(50) default NULL COMMENT '分类名称',
  `CATEGORY_DESC` varchar(200) default NULL COMMENT '分类说明',
  `ORDER_NO` int(11) default NULL COMMENT '排序值',
  `DEPARTMENT_ID` varchar(39) default NULL COMMENT '部门ID',
  `ORGAN_ID` varchar(39) default NULL COMMENT '单位ID',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '输入人员',
  `CREATE_TIME` datetime default NULL COMMENT '输入时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人员',
  `MODIFY_TIME` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`MEETING_CATEGORY_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='会议分类';
DROP TABLE IF EXISTS `boa_retire_official`;
CREATE TABLE `boa_retire_official` (
  `retire_official_id` varchar(39) NOT NULL,
  `name` varchar(20) default NULL COMMENT '姓名',
  `sex` int(11) default NULL COMMENT '性别（1：男，2：女）',
  `birth_date` datetime default NULL COMMENT '出生年月',
  `nation` int(11) default NULL COMMENT '民族',
  `native_place` varchar(39) default NULL COMMENT '籍贯',
  `join_party_time` datetime default NULL COMMENT '入党时间',
  `start_work_time` datetime default NULL COMMENT '参加工作时间',
  `retire_time` datetime default NULL COMMENT '离退休时间',
  `retire_code` varchar(20) default NULL COMMENT '离退休文号',
  `retire_level` int(11) default NULL COMMENT '享受待遇/级别（0：退休，1：离休）',
  `retire_organ_duty` varchar(50) default NULL COMMENT '离退休时单位及职务',
  `month_earning` float default NULL COMMENT '月收入总额',
  `basic_retire_money` float default NULL COMMENT '基本离退休金',
  `life_subsidy` float default NULL COMMENT '生活补贴',
  `subsidy2` float default NULL COMMENT '补贴2',
  `food_subsidy` float default NULL COMMENT '粮贴',
  `health` int(11) default NULL COMMENT '健康情况',
  `id_card` varchar(30) default NULL COMMENT '身份证号码',
  `address` varchar(50) default NULL COMMENT '家庭住址',
  `address_size` int(11) default NULL COMMENT '住房面积',
  `phone` varchar(15) default NULL COMMENT '固定电话',
  `mobile` varchar(15) default NULL COMMENT '移动电话',
  `spouse_name` varchar(20) default NULL COMMENT '配偶姓名',
  `spouse_birth_date` datetime default NULL COMMENT '配偶出生年月',
  `spouse_health` int(11) default NULL COMMENT '配偶身体状况',
  `spouse_organ_duty` varchar(50) default NULL COMMENT '配偶原单位及职务',
  `spouse_info` varchar(50) default NULL COMMENT '配偶目前状况',
  `personal_record` longtext COMMENT '个人履历',
  `department_id` varchar(39) default NULL COMMENT '部门ID',
  `organ_id` varchar(39) default NULL COMMENT '单位ID',
  `create_person` varchar(39) default NULL COMMENT '输入人员',
  `create_time` datetime default NULL COMMENT '输入时间',
  `modify_person` varchar(39) default NULL COMMENT '修改人员',
  `modify_time` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`retire_official_id`),
  KEY `FK1E039604E402B565` (`department_id`),
  KEY `FK1E0396041260E254` (`organ_id`),
  KEY `FK1E039604F7D3A6E5` (`modify_person`),
  KEY `FK1E039604AE8A1063` (`create_person`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
INSERT INTO `sys_dictionary_table` VALUES ('402881e2277911060127791de4070004', 0, 1, 1, 'devicemgr', '保密技术设备');
INSERT INTO `sys_dictionary_field` VALUES ('402881e2277911060127791e658b0005', 'secrecyEquipment_status', '保密设备状态', 0, 1, '402881e2277911060127791de4070004');
INSERT INTO `sys_dictionary_option` VALUES ('402881e2277911060127791ecc150006', '正常', 1, 1, 1, '402881e2277911060127791e658b0005',null);
INSERT INTO `sys_dictionary_option` VALUES ('402881e2277911060127791eef7b0007', '借出', 2, 2, 1, '402881e2277911060127791e658b0005',null);
INSERT INTO `sys_dictionary_option` VALUES ('402881e2277911060127791f43770008', '报废', 100, 3, 1, '402881e2277911060127791e658b0005',null);


