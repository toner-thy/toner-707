-- ******************************* 添加菜单[ 承担课题情况 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb570e40000e', 'a', '承担课题情况', '0', '', '', '1', '', '53', 'lawsystem-1001001d');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb570e40000f', '1', '', '/bmp/undertaketask/undertaketask_list.action', 'ca82cae445cb28a00145cb570e40000e');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb570e40000e', '1');

	-- ******************************* 添加菜单[ 新增 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb57fc0b0010', 'a', '新增', '1', '', '', '1', '', '1', 'ca82cae445cb28a00145cb570e40000e');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb57fc0b0011', '1', 'doAdd', '/bmp/undertaketask/undertaketask_add.action', 'ca82cae445cb28a00145cb57fc0b0010');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb57fc0b0010', '1');

	-- ******************************* 添加菜单[ 编辑 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb58c6210012', 'a', '编辑', '1', '', '', '1', '', '2', 'ca82cae445cb28a00145cb570e40000e');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb58c6210013', '1', 'doEdit', '/bmp/undertaketask/undertaketask_edit.action', 'ca82cae445cb28a00145cb58c6210012');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb58c6210012', '1');

	-- ******************************* 添加菜单[ 删除 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb59924b0014', 'a', '删除', '1', '', '', '1', '', '3', 'ca82cae445cb28a00145cb570e40000e');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb5a0d860017', '1', 'doDel', '/bmp/undertaketask/undertaketask_delete.action', 'ca82cae445cb28a00145cb59924b0014');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb59924b0014', '1');

	-- ******************************* 添加菜单[ 导出 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb5b2f320018', 'a', '导出', '1', '', '', '1', '', '4', 'ca82cae445cb28a00145cb570e40000e');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb5b2f320019', '1', 'doExport', '/bmp/undertaketask/undertaketask_export.action', 'ca82cae445cb28a00145cb5b2f320018');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb5b2f320018', '1');

