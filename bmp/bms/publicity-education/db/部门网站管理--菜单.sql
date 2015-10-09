-- ******************************* 添加菜单[ 部门网站管理 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb62f8e80027', 'a', '部门网站管理', '0', '', '', '1', '', '55', 'lawsystem-1001001d');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb635a620029', '1', '', '/bmp/departmentWebsite/departmentWebsite_list.action', 'ca82cae445cb28a00145cb62f8e80027');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb62f8e80027', '1');

	-- ******************************* 添加菜单[ 新增 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb6427b4002a', 'a', '新增', '1', '', '', '1', '', '1', 'ca82cae445cb28a00145cb62f8e80027');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb6427b4002b', '1', 'doAdd', '/bmp/departmentWebsite/departmentWebsite_add.action', 'ca82cae445cb28a00145cb6427b4002a');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb6427b4002a', '1');

	-- ******************************* 添加菜单[ 编辑 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb652be5002c', 'a', '编辑', '1', '', '', '1', '', '2', 'ca82cae445cb28a00145cb62f8e80027');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb652be5002d', '1', 'doEdit', '/bmp/departmentWebsite/departmentWebsite_edit.action', 'ca82cae445cb28a00145cb652be5002c');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb652be5002c', '1');

	-- ******************************* 添加菜单[ 删除 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb65ff52002e', 'a', '删除', '1', '', '', '1', '', '3', 'ca82cae445cb28a00145cb62f8e80027');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb65ff52002f', '1', 'doDel', '/bmp/departmentWebsite/departmentWebsite_delete.action', 'ca82cae445cb28a00145cb65ff52002e');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb65ff52002e', '1');

	-- ******************************* 添加菜单[ 导出 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb66cec80030', 'a', '导出', '1', '', '', '1', '', '4', 'ca82cae445cb28a00145cb62f8e80027');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb66cec80031', '1', 'doExport', '/bmp/departmentWebsite/departmentWebsite_export.action', 'ca82cae445cb28a00145cb66cec80030');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb66cec80030', '1');

