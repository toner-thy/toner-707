-- ******************************* 添加菜单[ 收到密件情况 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('ca82cae445ee159f0145ee6e078c0016', 'a', '收到密件情况', '0', '', '', '1', '', '58', 'lawsystem-1001001d');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('ca82cae445ee159f0145ee6eac380018', '1', '', '/bmp/receiveConfidential/receiveConfidential_list.action', 'ca82cae445ee159f0145ee6e078c0016');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('ca82cae445ee159f0145ee6e078c0016', '1');

	-- ******************************* 添加菜单[ 增加 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ee159f0145ee70e2610019', 'a', '增加', '1', '', '', '1', '', '1', 'ca82cae445ee159f0145ee6e078c0016');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ee159f0145ee70e261001a', '1', 'doAdd', '/bmp/receiveConfidential/receiveConfidential_add.action', 'ca82cae445ee159f0145ee70e2610019');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ee159f0145ee70e2610019', '1');

	-- ******************************* 添加菜单[ 编辑 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ee159f0145ee729d6f001b', 'a', '编辑', '1', '', '', '1', '', '2', 'ca82cae445ee159f0145ee6e078c0016');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ee159f0145ee741922001f', '1', 'doEdit', '/bmp/receiveConfidential/receiveConfidential_edit.action', 'ca82cae445ee159f0145ee729d6f001b');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ee159f0145ee729d6f001b', '1');

	-- ******************************* 添加菜单[ 删除 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ee159f0145ee73c342001d', 'a', '删除', '1', '', '', '1', '', '3', 'ca82cae445ee159f0145ee6e078c0016');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ee159f0145ee73c342001e', '1', 'doDel', '/bmp/receiveConfidential/receiveConfidential_delete.action', 'ca82cae445ee159f0145ee73c342001d');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ee159f0145ee73c342001d', '1');

	-- ******************************* 添加菜单[ 导出 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ee159f0145ee752cf40020', 'a', '导出', '1', '', '', '1', '', '4', 'ca82cae445ee159f0145ee6e078c0016');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ee159f0145ee75ad9d0022', '1', 'doExport', '/bmp/receiveConfidential/receiveConfidential_export.action', 'ca82cae445ee159f0145ee752cf40020');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ee159f0145ee752cf40020', '1');

