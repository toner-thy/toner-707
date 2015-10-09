-- ******************************* 添加菜单[ 资料下载 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881af366647190136668926200002', 'a', '资料下载', '0', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/645.gif', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/645.gif', '1', '个人单位附件管理', '8', '402881cd36a4941c0136a4961cb90002');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a23e4abb20013e4ad57f810010', '1', '', '/', '402881af366647190136668926200002');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881af366647190136668926200002', '1');

	-- ******************************* 添加菜单[ 资料下载 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881af366647190136668a87940004', 'a', '资料管理', '0', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/734.gif', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/734.gif', '1', '', '1', '402881af366647190136668926200002');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881a23e4abb20013e4ad5b3050011', '1', '', '/bmp/attachment/attachment_myList.action', '402881af366647190136668a87940004');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881af366647190136668a87940004', '1');

		-- ******************************* 添加菜单[ 上传附件 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881af366647190136668e3a3c000d', 'a', '上传附件', '1', '', '', '1', '', '1', '402881af366647190136668a87940004');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881af366b6bfd01366b76b24d0006', '1', 'doAdd', '/bmp/attachment/attachment_add.action', '402881af366647190136668e3a3c000d');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881af366647190136668e3a3c000d', '1');

		-- ******************************* 添加菜单[ 删除附件 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881af366647190136668edc77000f', 'a', '删除附件', '1', '', '', '1', '', '2', '402881af366647190136668a87940004');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881af366b6bfd01366b76dc1c0007', '1', 'doDelete', '/bmp/attachment/attachment_delete.action', '402881af366647190136668edc77000f');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881af366647190136668edc77000f', '1');

