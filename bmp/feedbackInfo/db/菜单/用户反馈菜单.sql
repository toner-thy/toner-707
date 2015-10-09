-- ******************************* 添加菜单[ 用户反馈 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a23ea5b7ca013ea64728bc0000', 'a', '用户反馈', '0', '', '', '1', '', '21', '402881cd36a4941c0136a4961cb90002');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a23ea5b7ca013ea648cb410004', '1', '', '', '402881a23ea5b7ca013ea64728bc0000');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a23ea5b7ca013ea64728bc0000', '1');

	-- ******************************* 添加菜单[ 用户反馈信息 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881a23ea5b7ca013ea64af5640005', 'a', '用户反馈信息', '0', '', '', '1', '', '1', '402881a23ea5b7ca013ea64728bc0000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881a23ea704cf013ea7098f370004', '1', '', '/platform/feedbackInfo/feedbackInfo_userList.action', '402881a23ea5b7ca013ea64af5640005');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881a23ea5b7ca013ea64af5640005', '1');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a23ea69c40013ea69dbab90001', 'a', '新增', '1', '', '', '1', '', '1', '402881a23ea5b7ca013ea64af5640005');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a23ea704cf013ea709cac90005', '1', 'doAdd', '/platform/feedbackInfo/feedbackInfo_add.action', '402881a23ea69c40013ea69dbab90001');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a23ea69c40013ea69dbab90001', '1');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a23ea69c40013ea69e65120003', 'a', '编辑', '1', '', '', '1', '', '1', '402881a23ea5b7ca013ea64af5640005');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a23ea704cf013ea7089da30002', '1', 'doEdit', '/platform/feedbackInfo/feedbackInfo_edit.action', '402881a23ea69c40013ea69e65120003');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a23ea69c40013ea69e65120003', '1');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a23ea5b7ca013ea64f1d9a0009', 'a', '删除', '1', '', '', '1', '', '3', '402881a23ea5b7ca013ea64af5640005');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a23eaad4bd013eaafc00cd0003', '1', 'doDelete', '/platform/feedbackInfo/feedbackInfo_delete.action', '402881a23ea5b7ca013ea64f1d9a0009');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a23ea5b7ca013ea64f1d9a0009', '1');

	-- ******************************* 添加菜单[ 反馈意见 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881a23ea5b7ca013ea64d22560007', 'a', '反馈意见', '0', '', '', '1', '', '1', '402881a23ea5b7ca013ea64728bc0000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881a23eaad4bd013eaad6dc120000', '1', '', '/platform/feedbackInfo/feedbackInfo_list.action', '402881a23ea5b7ca013ea64d22560007');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881a23ea5b7ca013ea64d22560007', '1');

		-- ******************************* 添加菜单[ 意见处理 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a23eab0246013eab09476b0000', 'a', '意见处理', '1', '', '', '1', '', '1', '402881a23ea5b7ca013ea64d22560007');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a23eab0246013eab0947aa0001', '1', 'doEdit', '/platform/feedbackInfo/feedbackInfo_edit.action', '402881a23eab0246013eab09476b0000');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a23eab0246013eab09476b0000', '1');

