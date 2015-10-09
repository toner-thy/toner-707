DROP TABLE IF EXISTS `bm_setTheDecryption`;
CREATE TABLE `bm_setTheDecryption` (
  `id` varchar(39) NOT NULL COMMENT 'ID',
  `date` date default NULL COMMENT '日期',
  `name` varchar(100) default NULL COMMENT '密件名称',
  `secrecyLevel` int(11) default NULL COMMENT '密级',
  `secrecyType` int(11) default NULL COMMENT '涉密事项类型0：定密事项 1：解密事项',
  `docNumber` varchar(100) default NULL COMMENT '文号',
  `carrierType` varchar(500) default NULL COMMENT '载体形式',
  `number` varchar(500) default NULL COMMENT '数量',
  `scope` varchar(500) default NULL COMMENT '分发范围',
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
	INSERT INTO sys_domain VALUES ('bm_setTheDecryption001', 'a', '定（解）密工作情况', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('bm_setTheDecryption002', '1', '', '/bmp/setTheDecryption/setTheDecryption_list.action', 'bm_setTheDecryption001');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('bm_setTheDecryption001', '1');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bm_setTheDecryption003', 'a', '新增', '1', '', '', '1', '', '1', 'bm_setTheDecryption001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ta82caeb45fdddcc0145fdeb2501111b', '1', 'doAdd', '/bmp/setTheDecryption/setTheDecryption_add.action', 'bm_setTheDecryption003');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bm_setTheDecryption003', '1');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bm_setTheDecryption004', 'a', '编辑', '1', '', '', '1', '', '2', 'bm_setTheDecryption001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ta82caeb45fdddcc0145fdeb25011216', '1', 'doEdit', '/bmp/setTheDecryption/setTheDecryption_edit.action', 'bm_setTheDecryption004');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bm_setTheDecryption004', '1');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bm_setTheDecryption005', 'a', '删除', '1', '', '', '1', '', '3', 'bm_setTheDecryption001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ta82caeb45fdddcc0145fdeb25011214', '1', 'doDel', '/bmp/setTheDecryption/setTheDecryption_delete.action', 'bm_setTheDecryption005');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bm_setTheDecryption005', '1');

	-- ******************************* 添加菜单[ 实施奖惩情况查询 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ta82caeb45fdddcc0145fdeb25011113', 'a', '定（解）密工作情况查询', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ta82caeb45fdddcc0145fdeb25011114', '1', '', '/bmp/setTheDecryption/setTheDecryption_main.action', 'ta82caeb45fdddcc0145fdeb25011113');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ta82caeb45fdddcc0145fdeb25011113', '1');