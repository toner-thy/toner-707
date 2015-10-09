
DROP TABLE IF EXISTS `bm_secrecy_check_event`;
CREATE TABLE `bm_secrecy_check_event` (
  `check_person` varchar(1000) default NULL,
  `SECRECY_CHECK_EVENT_ID` varchar(32) NOT NULL COMMENT '主键ID',
  `EVENT_NAME` varchar(100) default NULL COMMENT '检查事件名',
  `EVENT_DESCRIPTION` varchar(200) default NULL COMMENT '检查事件说明',
  `EVENT_DATE` date default NULL COMMENT '检查日期',
  `DISPOSE_IDEA` varchar(1000) default NULL COMMENT '处理意见',
  `CHECK_CIRCS` varchar(1000) default NULL COMMENT '检查情况',
  `CHECK_MEANS` varchar(1000) default NULL COMMENT '检查手段',
  `CHECKED_ORGAN` varchar(1000) default NULL COMMENT '受检单位（外键）',
  `CHECKED_DEPARTMENT` varchar(1000) default NULL COMMENT '受检部门（外键）',
  `MODIFY_TIME` datetime default NULL COMMENT '修改时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人员',
  `CREATE_TIME` datetime default NULL COMMENT '输入时间',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '输入人员',
  `ORGAN_ID` varchar(39) default NULL COMMENT '单位ID',
  `check_position` varchar(1000) default NULL,
  `DEPARTMENT_ID` varchar(39) default NULL COMMENT '部门ID',
  `type` int(11) default NULL COMMENT '检查类型 1:专项检查 2:专项技术检查',
  `joinNumber` int(11) default NULL,
  `rectification` varchar(1000) default NULL COMMENT '受检单位（外键）',
  PRIMARY KEY  (`SECRECY_CHECK_EVENT_ID`),
  KEY `FKBC777AAE402B565` (`DEPARTMENT_ID`),
  KEY `FKBC777AA28BE6B27` (`CHECKED_DEPARTMENT`(333)),
  KEY `FKBC777AA1260E254` (`ORGAN_ID`),
  KEY `FKBC777AAC44B8469` (`MODIFY_PERSON`),
  KEY `FKBC777AA7B01EDE7` (`CREATE_PERSON`),
  KEY `FKBC777AA9E30B3C4` (`CHECKED_ORGAN`(333))
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='保密检查事件';

-- ******************************* 添加菜单[ 检查事件 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('ca82ca8d4442e7ee014442f6cd900000', 'a', '检查事件', '0', '', '', '1', '', '1', '402881a23b9813c6013b98180dbd0002');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('ca82ca8d4442e7ee014442f806ac0002', '1', '', '', 'ca82ca8d4442e7ee014442f6cd900000');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('ca82ca8d4442e7ee014442f6cd900000', '1');

	-- ******************************* 添加菜单[ 检查事件 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82ca8d4442e7ee014442f8b7dc0003', 'a', '检查事件', '0', '', '', '1', '', '1', 'ca82ca8d4442e7ee014442f6cd900000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82caeb45f495c10145f49c71ff0000', '1', '', '/bmp/secrecyCheckEvent/secrecyCheckEvent_list.action', 'ca82ca8d4442e7ee014442f8b7dc0003');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82ca8d4442e7ee014442f8b7dc0003', '1');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82ca8d4442e7ee014442fb22640006', 'a', '新增', '1', '', '', '1', '', '1', 'ca82ca8d4442e7ee014442f8b7dc0003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82ca8d4443041b014443920b720001', '1', 'doAdd', '/bmp/secrecyCheckEvent/secrecyCheckEvent_add.action', 'ca82ca8d4442e7ee014442fb22640006');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82ca8d4442e7ee014442fb22640006', '1');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82ca8d4442e7ee014442fbd95f0008', 'a', '编辑', '1', '', '', '1', '', '1', 'ca82ca8d4442e7ee014442f8b7dc0003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82ca8d4443041b0144439250990002', '1', 'doEdit', '/bmp/secrecyCheckEvent/secrecyCheckEvent_edit.action', 'ca82ca8d4442e7ee014442fbd95f0008');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82ca8d4442e7ee014442fbd95f0008', '1');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82ca8d4442e7ee014442fc9c90000a', 'a', '删除', '1', '', '', '1', '', '1', 'ca82ca8d4442e7ee014442f8b7dc0003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82ca8d4443041b014443929f750003', '1', 'doDel', '/bmp/secrecyCheckEvent/secrecyCheckEvent_delete.action', 'ca82ca8d4442e7ee014442fc9c90000a');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82ca8d4442e7ee014442fc9c90000a', '1');

	-- ******************************* 添加菜单[ 检查事件查询 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ff8080814444088e0144445069450006', 'a', '检查事件查询', '0', '', '', '1', '', '2', 'ca82ca8d4442e7ee014442f6cd900000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82caeb45f495c10145f49cd3f50001', '1', '', '/bmp/secrecyCheckEvent/secrecyCheckEvent_main.action', 'ff8080814444088e0144445069450006');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ff8080814444088e0144445069450006', '1');

