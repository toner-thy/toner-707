-- ******************************* 添加菜单[ 通知公告 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a23e36488f013e364a35930000', 'a', '通知公告', '0', '', '', '1', '', '7', '402881cd36a4941c0136a4961cb90002');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a23e4abb20013e4ad4fd90000e', '1', '', '', '402881a23e36488f013e364a35930000');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a23e36488f013e364a35930000', '1');

	-- ******************************* 添加菜单[ 通知公告 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881a23e36488f013e364b198b0002', 'a', '通知公告', '-1', '', '', '1', '', '1', '402881a23e36488f013e364a35930000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881a23e4abb20013e4ad52c31000f', '1', '', '/bmp/notice/notice_list.action', '402881a23e36488f013e364b198b0002');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881a23e36488f013e364b198b0002', '1');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a23e36488f013e364ca77e0005', 'a', '新增', '1', '', '', '1', '', '1', '402881a23e36488f013e364b198b0002');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a23e36488f013e36515d4d000f', '1', 'doAdd', '/bmp/notice/notice_add.do', '402881a23e36488f013e364ca77e0005');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a23e36488f013e364ca77e0005', '1');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a23e36488f013e364e13140009', 'a', '编辑', '1', '', '', '1', '', '1', '402881a23e36488f013e364b198b0002');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a23e36488f013e365189cc0010', '1', 'doEdit', '/bmp/notice/notice_edit.action', '402881a23e36488f013e364e13140009');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a23e36488f013e364e13140009', '1');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a23e36488f013e364f34ff000b', 'a', '删除', '1', '', '', '1', '', '1', '402881a23e36488f013e364b198b0002');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a23e365a29013e366118d40000', '1', 'doDel', '/bmp/notice/notice_delete.action', '402881a23e36488f013e364f34ff000b');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a23e36488f013e364f34ff000b', '1');

