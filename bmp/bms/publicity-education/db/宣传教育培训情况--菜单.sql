-- ******************************* 添加菜单[ 宣传教育培训情况 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb3965c80000', 'a', '宣传教育培训情况', '0', '', '', '1', '', '52', 'lawsystem-1001001d');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb3965d70001', '1', '', '/bmp/publicityEducation/publicityEducation_list.action', 'ca82cae445cb28a00145cb3965c80000');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb3965c80000', '1');

	-- ******************************* 添加菜单[ 新增 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb4427ee0002', 'a', '新增', '1', '', '', '1', '', '1', 'ca82cae445cb28a00145cb3965c80000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb543de9000a', '1', 'doAdd', '/bmp/publicityEducation/publicityEducation_add.action', 'ca82cae445cb28a00145cb4427ee0002');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb4427ee0002', '1');

	-- ******************************* 添加菜单[ 编辑 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb457bb70004', 'a', '编辑', '1', '', '', '1', '', '2', 'ca82cae445cb28a00145cb3965c80000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb5486e9000b', '1', 'doEdit', '/bmp/publicityEducation/publicityEducation_edit.action', 'ca82cae445cb28a00145cb457bb70004');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb457bb70004', '1');

	-- ******************************* 添加菜单[ 删除 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb46f97d0006', 'a', '删除', '1', '', '', '1', '', '3', 'ca82cae445cb28a00145cb3965c80000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb55004f000c', '1', 'doDel', '/bmp/publicityEducation/publicityEducation_delete.action', 'ca82cae445cb28a00145cb46f97d0006');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb46f97d0006', '1');

	-- ******************************* 添加菜单[ 导出 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445cb28a00145cb4ad0280008', 'a', '导出', '1', '', '', '1', '', '4', 'ca82cae445cb28a00145cb3965c80000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445cb28a00145cb552dd7000d', '1', 'doExport', '/bmp/publicityEducation/publicityEducation_export.action', 'ca82cae445cb28a00145cb4ad0280008');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445cb28a00145cb4ad0280008', '1');

