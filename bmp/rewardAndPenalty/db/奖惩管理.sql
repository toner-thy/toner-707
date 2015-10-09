DROP TABLE IF EXISTS `bm_rewardandpenalty`;
CREATE TABLE `bm_rewardandpenalty` (
  `id` varchar(39) NOT NULL COMMENT 'ID',
  `name` varchar(100) default NULL COMMENT '名称',
  `description` varchar(200) default NULL COMMENT '备注',
  `rewardCircs` varchar(1000) default NULL COMMENT '奖励情况',
  `penaltyCircs` varchar(1000) default NULL COMMENT '惩罚情况',
  `STATE` int(11) default NULL COMMENT '状态：0：使用1：删除',
  `CREATE_ORGAN` varchar(39) default NULL COMMENT '创建单位',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '创建人',
  `date` date default NULL COMMENT '时间',
  `CREATE_TIME` date default NULL COMMENT '创建时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人',
  `MODIFY_TIME` date default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `bm_paperpresented`;
CREATE TABLE `bm_paperpresented` (
  `id` varchar(39) NOT NULL COMMENT 'ID',
  `author` varchar(100) default NULL COMMENT '作者',
  `name` varchar(100) default NULL COMMENT '篇名',
  `periodical` varchar(500) default NULL COMMENT '刊物',
  `date` date default NULL COMMENT '期号',
  `awardName` varchar(200) default NULL COMMENT '奖项名称',
  `awardOrgan` varchar(200) default NULL COMMENT '评奖单位',
  `description` varchar(200) default NULL COMMENT '备注',
  `STATE` int(11) default NULL COMMENT '状态：0：使用1：删除',
  `CREATE_ORGAN` varchar(39) default NULL COMMENT '创建单位',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '创建人',
  `CREATE_TIME` date default NULL COMMENT '创建时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人',
  `MODIFY_TIME` date default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ******************************* 添加菜单[ 奖惩管理 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('ca82caeb45ef81790145efa1ab480000', 'a', '奖惩管理', '0', '', '', '1', '', '2', 'lawsystem-1001d');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('ca82caeb45ef81790145efa1ab680001', '1', '', '', 'ca82caeb45ef81790145efa1ab480000');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('ca82caeb45ef81790145efa1ab480000', '1');

	-- ******************************* 添加菜单[ 实施奖惩情况 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82caeb45f878710145f87b67ba0000', 'a', '实施奖惩情况', '0', '', '', '1', '', '1', 'ca82caeb45ef81790145efa1ab480000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82caeb45f878710145f87b67c90001', '1', '', '/bmp/rewardAndPenalty/rewardAndPenalty_list.action', 'ca82caeb45f878710145f87b67ba0000');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82caeb45f878710145f87b67ba0000', '1');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb45f878710145f87c3ea10002', 'a', '新增', '1', '', '', '1', '', '1', 'ca82caeb45f878710145f87b67ba0000');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45f878710145f87c3ea10003', '1', 'doAdd', '/bmp/rewardAndPenalty/rewardAndPenalty_add.action', 'ca82caeb45f878710145f87c3ea10002');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb45f878710145f87c3ea10002', '1');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb45f878710145f87d1c110004', 'a', '编辑', '1', '', '', '1', '', '2', 'ca82caeb45f878710145f87b67ba0000');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45f878710145f87d1c110005', '1', 'doEdit', '/bmp/rewardAndPenalty/rewardAndPenalty_edit.action', 'ca82caeb45f878710145f87d1c110004');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb45f878710145f87d1c110004', '1');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb45f878710145f87e1c790006', 'a', '删除', '1', '', '', '1', '', '3', 'ca82caeb45f878710145f87b67ba0000');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45f878710145f87e1c790007', '1', 'doDel', '/bmp/rewardAndPenalty/rewardAndPenalty_delete.action', 'ca82caeb45f878710145f87e1c790006');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb45f878710145f87e1c790006', '1');

	-- ******************************* 添加菜单[ 实施奖惩情况查询 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82caeb45f878710145f87f07950008', 'a', '实施奖惩情况查询', '0', '', '', '1', '', '1', 'ca82caeb45ef81790145efa1ab480000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82caeb45f878710145f87f07950009', '1', '', '/bmp/rewardAndPenalty/rewardAndPenalty_main.action', 'ca82caeb45f878710145f87f07950008');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82caeb45f878710145f87f07950008', '1');

	-- ******************************* 添加菜单[ 论文评奖情况 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82caeb45f878710145f8803094000a', 'a', '论文评奖情况', '0', '', '', '1', '', '1', 'ca82caeb45ef81790145efa1ab480000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82caeb45f878710145f8803094000b', '1', '', '/bmp/paperPresented/paperPresented_list.action', 'ca82caeb45f878710145f8803094000a');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82caeb45f878710145f8803094000a', '1');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb45f878710145f881e5b6000e', 'a', '新增', '1', '', '', '1', '', '1', 'ca82caeb45f878710145f8803094000a');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45f878710145f881e5b6000f', '1', 'doAdd', '/bmp/paperPresented/paperPresented_add.action', 'ca82caeb45f878710145f881e5b6000e');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb45f878710145f881e5b6000e', '1');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb45f878710145f8827bdc0010', 'a', '编辑', '1', '', '', '1', '', '2', 'ca82caeb45f878710145f8803094000a');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45f878710145f8827bdc0011', '1', 'doEdit', '/bmp/paperPresented/paperPresented_edit.action', 'ca82caeb45f878710145f8827bdc0010');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb45f878710145f8827bdc0010', '1');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb45f878710145f883250c0012', 'a', '删除', '1', '', '', '1', '', '3', 'ca82caeb45f878710145f8803094000a');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45f878710145f883250c0013', '1', 'doDel', '/bmp/paperPresented/paperPresented_delete.action', 'ca82caeb45f878710145f883250c0012');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb45f878710145f883250c0012', '1');

	-- ******************************* 添加菜单[ 论文评奖情况查询 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82caeb45f878710145f88147a2000c', 'a', '论文评奖情况查询', '0', '', '', '1', '', '4', 'ca82caeb45ef81790145efa1ab480000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82caeb45f878710145f88147a2000d', '1', '', '/bmp/paperPresented/paperPresented_main.action', 'ca82caeb45f878710145f88147a2000c');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82caeb45f878710145f88147a2000c', '1');

