DROP TABLE IF EXISTS `bm_meeting`;
CREATE TABLE `bm_meeting` (
  `MEETING_ID` varchar(39) NOT NULL,
  `MEETING_NAME` varchar(128) default NULL,
  `MEETING_TIME` datetime default NULL,
  `ORGAN_ID` varchar(39) default NULL COMMENT '单位ID',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '输入人员',
  `CREATE_TIME` datetime default NULL COMMENT '输入时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人员',
  `MODIFY_TIME` datetime default NULL COMMENT '修改时间',
  `holdDepartment` varchar(255) default NULL,
  `holdOrgans` varchar(255) default NULL,
  `attendUserInfos` varchar(4096) default NULL,
  `STATUS` int(11) default NULL,
  `MEETING_CATEGORY_ID` varchar(32) default NULL,
  `place` varchar(128) default NULL,
  `content` varchar(4096) default NULL,
  `scope` varchar(4096) default NULL,
  `situation` varchar(4096) default NULL,
  `presenter_ID` varchar(39) default NULL COMMENT '单位ID',
  `recorder_ID` varchar(39) default NULL COMMENT '单位ID',
  `secrecyLevel` int(11) default NULL,
  `measure` varchar(4096) default NULL,
  PRIMARY KEY  (`MEETING_ID`),
  KEY `FK69F682471260E254` (`ORGAN_ID`),
  KEY `FK69F68247C44B8469` (`MODIFY_PERSON`),
  KEY `FK69F682477B01EDE7` (`CREATE_PERSON`),
  KEY `FK69F6f2d7C44B8469` (`MEETING_CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for `bm_meeting_category`
-- ----------------------------
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会议分类';

-- ----------------------------
-- Records of bm_meeting_category
-- ----------------------------
INSERT INTO bm_meeting_category VALUES ('ca82caeb45d9c78f0145d9cd1cd00000', '涉密会议（活动）', '涉密会议（活动）', null, '1', '1', '1', '2014-05-08 11:07:49', null, null);
INSERT INTO bm_meeting_category VALUES ('ca82caeb45d9c78f0145d9cdc2280001', '保密会议（活动）', '保密会议（活动）', null, '1', '1', '1', '2014-05-08 11:08:31', null, null);
INSERT INTO bm_meeting_category VALUES ('ca82caeb45d9c78f0145d9ce75990002', '会议（活动）记录', '会议（活动）记录', null, '1', '1', '1', '2014-05-08 11:09:17', null, null);
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

	-- ******************************* 添加菜单[ 会议管理 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881a743aed4670143aedeafcb0004', 'a', '会议管理', '0', '', '', '1', '', '2', '402881a743aed4670143aedc8e810000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82caeb45edfb3e0145ee77c4960002', '1', '', '/bmp/meeting/meeting_list.action', '402881a743aed4670143aedeafcb0004');
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

		-- ******************************* 添加菜单[ 导出 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb45edfb3e0145edfec2030000', 'a', '导出', '1', '', '', '1', '', '4', '402881a743aed4670143aedeafcb0004');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45edfb3e0145edfec2030001', '1', 'doExport', '/bmp/meeting/meeting_export.action', 'ca82caeb45edfb3e0145edfec2030000');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb45edfb3e0145edfec2030000', '1');

	-- ******************************* 添加菜单[ 会议查询 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82caeb45dff32a0145e01815900003', 'a', '会议查询', '0', '', '', '1', '', '3', '402881a743aed4670143aedc8e810000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82caeb45dff32a0145e01815900004', '1', '', '/bmp/meeting/meeting_main.action', 'ca82caeb45dff32a0145e01815900003');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82caeb45dff32a0145e01815900003', '1');

