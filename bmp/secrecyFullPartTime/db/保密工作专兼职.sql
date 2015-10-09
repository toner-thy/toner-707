DROP TABLE IF EXISTS `bm_secrecyFullPartTime`;
CREATE TABLE `bm_secrecyFullPartTime` (
  `id` varchar(39) NOT NULL COMMENT 'ID',
  `date` date default NULL COMMENT '接受保密培训时间',
  `position` varchar(100) default NULL COMMENT '职务',
  `degree` int(11) default NULL COMMENT '文化程度',
  `isFull` int(11) default NULL COMMENT '专职或者兼职 0：专职 1：兼职；',
  `isTrain` int(11) default NULL COMMENT '是否介绍保密培训 0：接受 1 ：没有',
  `content` varchar(1000) default NULL COMMENT '接受保密培训内容',
  `workYear` varchar(100) default NULL COMMENT '从事保密工作年限',
  `name` varchar(39) default NULL COMMENT '姓名',
  `description` varchar(200) default NULL COMMENT '备注',
  `STATE` int(11) default NULL COMMENT '状态：0：使用1：删除',
  `CREATE_ORGAN` varchar(39) default NULL COMMENT '创建单位',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '创建人',
  `CREATE_TIME` date default NULL COMMENT '创建时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人',
  `MODIFY_TIME` date default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('bm_secrecyFullPartTime001', 'a', '保密工作专（兼）职人员情况', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('bm_secrecyFullPartTime002', '1', '', '/bmp/secrecyFullPartTime/secrecyFullPartTime_list.action', 'bm_secrecyFullPartTime001');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('bm_secrecyFullPartTime001', '1');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bm_secrecyFullPartTime003', 'a', '新增', '1', '', '', '1', '', '1', 'bm_secrecyFullPartTime001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('na82caeb45fdddcc0145fdeb2501111b', '1', 'doAdd', '/bmp/secrecyFullPartTime/secrecyFullPartTime_add.action', 'bm_secrecyFullPartTime003');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bm_secrecyFullPartTime003', '1');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bm_secrecyFullPartTime004', 'a', '编辑', '1', '', '', '1', '', '2', 'bm_secrecyFullPartTime001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('na82caeb45fdddcc0145fdeb25011216', '1', 'doEdit', '/bmp/secrecyFullPartTime/secrecyFullPartTime_edit.action', 'bm_secrecyFullPartTime004');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bm_secrecyFullPartTime004', '1');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bm_secrecyFullPartTime005', 'a', '删除', '1', '', '', '1', '', '3', 'bm_secrecyFullPartTime001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('na82caeb45fdddcc0145fdeb25011214', '1', 'doDel', '/bmp/secrecyFullPartTime/secrecyFullPartTime_delete.action', 'bm_secrecyFullPartTime005');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bm_secrecyFullPartTime005', '1');

	-- ******************************* 添加菜单[ 实施奖惩情况查询 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('na82caeb45fdddcc0145fdeb25011113', 'a', '保密工作专（兼）职人员情况查询', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('na82caeb45fdddcc0145fdeb25011114', '1', '', '/bmp/secrecyFullPartTime/secrecyFullPartTime_main.action', 'na82caeb45fdddcc0145fdeb25011113');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('na82caeb45fdddcc0145fdeb25011113', '1');