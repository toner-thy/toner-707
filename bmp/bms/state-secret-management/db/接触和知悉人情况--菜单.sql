-- ******************************* 添加菜单[ 接触和知悉人情况 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('ca82cae445ee159f0145ee272b97000b', 'a', '接触和知悉人情况', '0', '', '', '1', '', '57', 'lawsystem-1001001d');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('ca82cae445ee159f0145ee27ba1c000d', '1', '', '/bmp/contactSecretPerson/contactSecretPerson_list.action', 'ca82cae445ee159f0145ee272b97000b');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('ca82cae445ee159f0145ee272b97000b', '1');

	-- ******************************* 添加菜单[ 新增 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ee159f0145ee28997f000e', 'a', '新增', '1', '', '', '1', '', '1', 'ca82cae445ee159f0145ee272b97000b');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ee159f0145ee28997f000f', '1', 'doAdd', '/bmp/contactSecretPerson/contactSecretPerson_add.action', 'ca82cae445ee159f0145ee28997f000e');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ee159f0145ee28997f000e', '1');

	-- ******************************* 添加菜单[ 编辑 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ee159f0145ee5d78880010', 'a', '编辑', '1', '', '', '1', '', '2', 'ca82cae445ee159f0145ee272b97000b');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ee159f0145ee5d78880011', '1', 'doEdit', '/bmp/contactSecretPerson/contactSecretPerson_edit.action', 'ca82cae445ee159f0145ee5d78880010');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ee159f0145ee5d78880010', '1');

	-- ******************************* 添加菜单[ 导出 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ee159f0145ee68524d0014', 'a', '导出', '1', '', '', '1', '', '4', 'ca82cae445ee159f0145ee272b97000b');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ee159f0145ee68524d0015', '1', 'doExport', '/bmp/contactSecretPerson/contactSecretPerson_export.action', 'ca82cae445ee159f0145ee68524d0014');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ee159f0145ee68524d0014', '1');

	-- ******************************* 添加菜单[ 删除 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ee159f0145ee6365320012', 'a', '删除', '1', '', '', '1', '', '3', 'ca82cae445ee159f0145ee272b97000b');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ee159f0145ee6365320013', '1', 'doDel', '/bmp/contactSecretPerson/contactSecretPerson_delete.action', 'ca82cae445ee159f0145ee6365320012');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ee159f0145ee6365320012', '1');

