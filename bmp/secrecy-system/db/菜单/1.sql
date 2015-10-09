	-- ******************************* 添加菜单[ 涉密计算机 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyComputer', 'a', '涉密计算机', '0', '', '', '1', '', '1', '402881a240333e88014033433bc90000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyComputer_resource', '1', '', '/bmp/secrecycomputer/secrecyComputer_main.action', 'secrecyComputer');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyComputer', '1');

	-- ******************************* 添加菜单[ 新增 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyComputer_add', 'a', '新增', '1', '', '', '1', '', '1', 'secrecyComputer');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyComputer_add_resource', '1', 'doAdd', '/bmp/secrecycomputer/secrecyComputer_add.action', 'secrecyComputer_add');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyComputer_add', '1');

	-- ******************************* 添加菜单[ 编辑 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyComputer_edit', 'a', '编辑', '1', '', '', '1', '', '2', 'secrecyComputer');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyComputer_edit_resource', '1', 'doEdit', '/bmp/secrecycomputer/secrecyComputer_edit.action', 'secrecyComputer_edit');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyComputer_edit', '1');

	-- ******************************* 添加菜单[ 删除 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyComputer_delete', 'a', '删除', '1', '', '', '1', '', '3', 'secrecyComputer');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyComputer_delete_resource', '1', 'doDel', '/bmp/secrecycomputer/secrecyComputer_delete.action', 'secrecyComputer_delete');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyComputer_delete', '1');

	-- ******************************* 添加菜单[ 密级变更 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyComputer_change', 'a', '密级变更', '1', '', '', '1', '', '4', 'secrecyComputer');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyComputer_change_resource', '1', 'doSecrecyChange', '/bmp/secrecycomputer/secrecyComputer_secrecyChange.action', 'secrecyComputer_change');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyComputer_change', '1');

	-- ******************************* 添加菜单[ 解除 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyComputer_decryption', 'a', '解除', '1', '', '', '1', '', '5', 'secrecyComputer');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyComputer_decryption_resource', '1', 'doDecryption', '/bmp/secrecycomputer/secrecyComputer_decryption.action', 'secrecyComputer_decryption');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyComputer_decryption', '1');

	-- ******************************* 添加菜单[ 接入网络 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyComputer_addNet', 'a', '接入网络', '1', '', '', '1', '', '7', 'secrecyComputer');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyComputer_addNet_resource', '1', 'doAddNet', '/bmp/secrecycomputer/secrecyComputer_addNet.action', 'secrecyComputer_addNet');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyComputer_addNet', '1');

	-- ******************************* 添加菜单[ 移除网络 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyComputer_removeNet', 'a', '移除网络', '1', '', '', '1', '', '8', 'secrecyComputer');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyComputer_removeNet_resource', '1', 'doRemoveNet', '/bmp/secrecycomputer/secrecyComputer_removeNet.action', 'secrecyComputer_removeNet');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyComputer_removeNet', '1');

	-- ******************************* 添加菜单[ 导出 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyComputer_export', 'a', '导出', '1', '', '', '1', '', '10', 'secrecyComputer');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyComputer_export_resource', '1', 'doExport', '/bmp/secrecycomputer/secrecyComputer_export.action', 'secrecyComputer_export');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyComputer_export', '1');


----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	-- ******************************* 添加菜单[ 涉密网络 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyNetwork', 'a', '涉密网络', '0', '', '', '1', '', '1', '402881a240333e88014033433bc90000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyNetwork_resource', '1', '', '/bmp/secrecynetwork/secrecyNetwork_main.action', 'secrecyNetwork');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyNetwork', '1');

	-- ******************************* 添加菜单[ 新增 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyNetwork_add', 'a', '新增', '1', '', '', '1', '', '1', 'secrecyNetwork');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyNetwork_add_resource', '1', 'doAdd', '/bmp/secrecynetwork/secrecyNetwork_add.action', 'secrecyNetwork_add');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyNetwork_add', '1');

	-- ******************************* 添加菜单[ 编辑 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyNetwork_edit', 'a', '编辑', '1', '', '', '1', '', '2', 'secrecyNetwork');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyNetwork_edit_resource', '1', 'doEdit', '/bmp/secrecynetwork/secrecyNetwork_edit.action', 'secrecyNetwork_edit');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyNetwork_edit', '1');

	-- ******************************* 添加菜单[ 删除 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyNetwork_delete', 'a', '删除', '1', '', '', '1', '', '3', 'secrecyNetwork');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyNetwork_delete_resource', '1', 'doDel', '/bmp/secrecynetwork/secrecyNetwork_delete.action', 'secrecyNetwork_delete');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyNetwork_delete', '1');

	-- ******************************* 添加菜单[ 密级变更 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyNetwork_change', 'a', '密级变更', '1', '', '', '1', '', '4', 'secrecyNetwork');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyNetwork_change_resource', '1', 'doSecrecyChange', '/bmp/secrecynetwork/secrecyNetwork_secrecyChange.action', 'secrecyNetwork_change');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyNetwork_change', '1');

	-- ******************************* 添加菜单[ 解除 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyNetwork_decryption', 'a', '解除', '1', '', '', '1', '', '5', 'secrecyNetwork');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyNetwork_decryption_resource', '1', 'doDecryption', '/bmp/secrecynetwork/secrecyNetwork_decryption.action', 'secrecyNetwork_decryption');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyNetwork_decryption', '1');


	-- ******************************* 添加菜单[ 导出 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('secrecyNetwork_export', 'a', '导出', '1', '', '', '1', '', '10', 'secrecyNetwork');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('secrecyNetwork_export_resource', '1', 'doExport', '/bmp/secrecynetwork/secrecyNetwork_export.action', 'secrecyNetwork_export');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('secrecyNetwork_export', '1');


