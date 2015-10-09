-- ******************************* 添加菜单[ 年度工作总结 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('ca82cae445ab6bfd0145ab7317560002', 'a', '年度工作总结', '0', '', '', '1', '', '51', 'lawsystem-1001001d');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('ca82cae445ab6bfd0145ab7317560003', '1', '', '/bmp/annualSummary/annualSummary_list.action', 'ca82cae445ab6bfd0145ab7317560002');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('ca82cae445ab6bfd0145ab7317560002', '1');

	-- ******************************* 添加菜单[ 新增 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ab84a20145ab9d8b310008', 'a', '新增', '1', '', '', '1', '', '1', 'ca82cae445ab6bfd0145ab7317560002');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ab84a20145ab9d8b310009', '1', 'doAdd', '/bmp/annualSummary/annualSummary_add.action', 'ca82cae445ab84a20145ab9d8b310008');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ab84a20145ab9d8b310008', '1');

	-- ******************************* 添加菜单[ 编辑 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ab84a20145ab9e46d1000a', 'a', '编辑', '1', '', '', '1', '', '2', 'ca82cae445ab6bfd0145ab7317560002');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ab84a20145ab9e46d1000b', '1', 'doEdit', '/bmp/annualSummary/annualSummary_edit.action', 'ca82cae445ab84a20145ab9e46d1000a');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ab84a20145ab9e46d1000a', '1');

	-- ******************************* 添加菜单[ 删除 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ab84a20145ab9f0ec4000c', 'a', '删除', '1', '', '', '1', '', '3', 'ca82cae445ab6bfd0145ab7317560002');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ab84a20145ab9f0ec4000d', '1', 'doDel', '/bmp/annualSummary/annualSummary_delete.action', 'ca82cae445ab84a20145ab9f0ec4000c');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ab84a20145ab9f0ec4000c', '1');

	-- ******************************* 添加菜单[ 引用 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae445ab84a20145ab9fc8dd000e', 'a', '引用', '1', '', '', '1', '', '4', 'ca82cae445ab6bfd0145ab7317560002');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae445ab84a20145ab9fc8dd000f', '1', 'doQuote', '/bmp/annualSummary/annualSummary_quote.action', 'ca82cae445ab84a20145ab9fc8dd000e');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae445ab84a20145ab9fc8dd000e', '1');

