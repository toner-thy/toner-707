-- ******************************* 添加菜单[ 年度工作计划 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('ca82cae445ab6bfd0145ab720ff80000', 'a', '年度工作计划', '0', '', '', '1', '', '50', 'lawsystem-1001001d');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('ca82cae445ab6bfd0145ab7210070001', '1', '', '/bmp/annualPlan/annualPlan_list.action', 'ca82cae445ab6bfd0145ab720ff80000');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('ca82cae445ab6bfd0145ab720ff80000', '1');

	-- ******************************* 添加菜单[ 新增 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ab84a20145ab98caf20000', 'a', '新增', '1', '', '', '1', '', '1', 'ca82cae445ab6bfd0145ab720ff80000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ab84a20145ab98caf20001', '1', 'doAdd', '/bmp/annualPlan/annualPlan_add.action', 'ca82cae445ab84a20145ab98caf20000');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ab84a20145ab98caf20000', '1');

	-- ******************************* 添加菜单[ 编辑 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ab84a20145ab99af570002', 'a', '编辑', '1', '', '', '1', '', '2', 'ca82cae445ab6bfd0145ab720ff80000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ab84a20145ab99af570003', '1', 'doEdit', '/bmp/annualPlan/annualPlan_edit.action', 'ca82cae445ab84a20145ab99af570002');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ab84a20145ab99af570002', '1');

	-- ******************************* 添加菜单[ 删除 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ab84a20145ab9ac9a00004', 'a', '删除', '1', '', '', '1', '', '3', 'ca82cae445ab6bfd0145ab720ff80000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ab84a20145ab9ac9a00005', '1', 'doDel', '/bmp/annualPlan/annualPlan_delete.action', 'ca82cae445ab84a20145ab9ac9a00004');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ab84a20145ab9ac9a00004', '1');

	-- ******************************* 添加菜单[ 引用 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ab84a20145ab9c9a690006', 'a', '引用', '1', '', '', '1', '', '4', 'ca82cae445ab6bfd0145ab720ff80000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ab84a20145ab9c9a690007', '1', 'doQuote', '/bmp/annualPlan/annualPlan_quote.action', 'ca82cae445ab84a20145ab9c9a690006');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ab84a20145ab9c9a690006', '1');

