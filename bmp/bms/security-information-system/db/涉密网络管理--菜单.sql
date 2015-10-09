-- ******************************* 添加菜单[ 涉密网络管理 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('ca82cae445ee159f0145ee1e05fa0000', 'a', '涉密网络管理', '0', '', '', '1', '', '56', 'lawsystem-1001001d');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('ca82cae445ee159f0145ee21109a0002', '1', '', '/bmp/secretNetwork/secretNetwork_list.action', 'ca82cae445ee159f0145ee1e05fa0000');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('ca82cae445ee159f0145ee1e05fa0000', '1');

	-- ******************************* 添加菜单[ 新增 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ee159f0145ee226d3c0003', 'a', '新增', '1', '', '', '1', '', '1', 'ca82cae445ee159f0145ee1e05fa0000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ee159f0145ee226d3c0004', '1', 'doAdd', '//bmp/secretNetwork/secretNetwork_add.action', 'ca82cae445ee159f0145ee226d3c0003');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ee159f0145ee226d3c0003', '1');

	-- ******************************* 添加菜单[ 编辑 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ee159f0145ee236b630005', 'a', '编辑', '1', '', '', '1', '', '2', 'ca82cae445ee159f0145ee1e05fa0000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ee159f0145ee236b630006', '1', 'doEdit', '/bmp/secretNetwork/secretNetwork_edit.action', 'ca82cae445ee159f0145ee236b630005');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ee159f0145ee236b630005', '1');

	-- ******************************* 添加菜单[ 删除 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ee159f0145ee246fd30007', 'a', '删除', '1', '', '', '1', '', '3', 'ca82cae445ee159f0145ee1e05fa0000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ee159f0145ee246fd30008', '1', 'doDel', '/bmp/secretNetwork/secretNetwork_delete.action', 'ca82cae445ee159f0145ee246fd30007');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ee159f0145ee246fd30007', '1');

	-- ******************************* 添加菜单[ 导出 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ee159f0145ee2589900009', 'a', '导出', '1', '', '', '1', '', '4', 'ca82cae445ee159f0145ee1e05fa0000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ee159f0145ee258990000a', '1', 'doExport', '/bmp/secretNetwork/secretNetwork_export.action', 'ca82cae445ee159f0145ee2589900009');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ee159f0145ee2589900009', '1');

