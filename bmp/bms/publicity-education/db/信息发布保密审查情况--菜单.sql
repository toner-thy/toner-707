-- ******************************* 添加菜单[ 信息发布保密审查情况 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb5cb99b001a', 'a', '信息发布保密审查情况', '0', '', '', '1', '', '54', 'lawsystem-1001001d');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb5d028a001c', '1', '', '/bmp/informationReview/informationReview_list.action', 'ca82cae445cb28a00145cb5cb99b001a');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb5cb99b001a', '1');

	-- ******************************* 添加菜单[ 新增 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb5dbc74001d', 'a', '新增', '1', '', '', '1', '', '1', 'ca82cae445cb28a00145cb5cb99b001a');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb5f830b0021', '1', 'doAdd', '/bmp/informationReview/informationReview_add.action', 'ca82cae445cb28a00145cb5dbc74001d');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb5dbc74001d', '1');

	-- ******************************* 添加菜单[ 编辑 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb5ebaba001f', 'a', '编辑', '1', '', '', '1', '', '2', 'ca82cae445cb28a00145cb5cb99b001a');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb5ebaba0020', '1', 'doEdit', '/bmp/informationReview/informationReview_edit.action', 'ca82cae445cb28a00145cb5ebaba001f');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb5ebaba001f', '1');

	-- ******************************* 添加菜单[ 删除 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb60bbd90022', 'a', '删除', '1', '', '', '1', '', '3', 'ca82cae445cb28a00145cb5cb99b001a');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb60bbd90023', '1', 'doDel', '/bmp/informationReview/informationReview_delete.action', 'ca82cae445cb28a00145cb60bbd90022');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb60bbd90022', '1');

	-- ******************************* 添加菜单[ 导出 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb61672d0024', 'a', '导出', '1', '', '', '1', '', '4', 'ca82cae445cb28a00145cb5cb99b001a');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb61c8c50026', '1', 'doExport', '/bmp/informationReview/informationReview_export.action', 'ca82cae445cb28a00145cb61672d0024');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb61672d0024', '1');

