-- ******************************* 添加菜单[ 考试管理 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881ec297d4a2601297dfacf8e0001', 'a', '考试管理', '-1', '/explorer/images/disktop_ico/16.gif', '/explorer/images/disktop_ico/16.gif', '1', '', '4', '1');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881af369f1a0601369f4b890e0002', '1', '', '', '402881ec297d4a2601297dfacf8e0001');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881ec297d4a2601297dfacf8e0001', '1');

	-- ******************************* 添加菜单[ 考试管理 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881ec297e0a3e01297e0ea5dd0001', 'a', '考试管理', '0', '/explorer/images/ico/016.gif', '/explorer/images/ico/016.gif', '1', '', '1', '402881ec297d4a2601297dfacf8e0001');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881f42c28eef2012c290dcb8e0015', '1', '', 'a', '402881ec297e0a3e01297e0ea5dd0001');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881ec297e0a3e01297e0ea5dd0001', '1');

		-- ******************************* 添加菜单[ 发起考试 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881ec297e0a3e01297e1037c80009', 'a', '发起考试', '0', '/explorer/images/ico/946.gif', '/explorer/images/ico/946.gif', '1', '', '1', '402881ec297e0a3e01297e0ea5dd0001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881f42c28eef2012c290ea3be0016', '1', '', '/exam/examination/examination_list.action', '402881ec297e0a3e01297e1037c80009');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881ec297e0a3e01297e1037c80009', '1');

			-- ******************************* 添加菜单[ 添加 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881f42bd21750012bd23968160001', 'a', '新增', '1', '', '', '1', '', '1', '402881ec297e0a3e01297e1037c80009');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881f42bd24c79012bd253cdb70004', '1', 'doAdd', '/exam/examination/examination_add.action', '402881f42bd21750012bd23968160001');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881f42bd21750012bd23968160001', '1');

			-- ******************************* 添加菜单[ 编辑 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881f42bd21750012bd239da680003', 'a', '编辑', '1', '', '', '1', '', '1', '402881ec297e0a3e01297e1037c80009');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881f42bd24c79012bd25414b30005', '1', 'doEdit', '/exam/examination/examination_edit.action', '402881f42bd21750012bd239da680003');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881f42bd21750012bd239da680003', '1');

			-- ******************************* 添加菜单[ 删除 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881f42bd21750012bd23a55060005', 'a', '删除', '1', '', '', '1', '', '3', '402881ec297e0a3e01297e1037c80009');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881f42be26807012be26c5abd0003', '1', 'doDelete', '/exam/examination/examination_delete.action', '402881f42bd21750012bd23a55060005');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881f42bd21750012bd23a55060005', '1');

		-- ******************************* 添加菜单[ 题库管理 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881d92986fd8c01298728b3d20005', 'a', '题库管理', '0', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/1035.gif', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/1035.gif', '1', '', '2', '402881ec297e0a3e01297e0ea5dd0001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881ce36ec72410136ecb95946001f', '1', '', '/question/questionCategory/questionCategory_main.action', '402881d92986fd8c01298728b3d20005');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881d92986fd8c01298728b3d20005', '1');

			-- ******************************* 添加菜单[ 题库分类列表管理 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881e62be71f6a012be73eb6b60002', 'a', '题库分类列表管理', '0', '/a', '/a', '1', '', '1', '402881d92986fd8c01298728b3d20005');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881e62be742f9012be743f9c20002', '1', '', '/question/questionCategory/questionCategory_list.action', '402881e62be71f6a012be73eb6b60002');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881e62be71f6a012be73eb6b60002', '1');

				-- ******************************* 添加菜单[ 添加 ] *******************************
				-- ----------------------------- sys_domain表insert语句 ----------------------------
				INSERT INTO sys_domain VALUES ('402881e62be71f6a012be73f49030004', 'a', '新增', '1', '/a', '/a', '1', '', '1', '402881e62be71f6a012be73eb6b60002');
				-- ----------------------------- sys_resource表insert语句 ----------------------------
				INSERT INTO sys_resource VALUES ('402881e62be742f9012be7441fb80003', '1', 'doAdd', '/question/questionCategory/questionCategory_add.action', '402881e62be71f6a012be73f49030004');
				-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
				INSERT INTO sys_domain_organ VALUES ('402881e62be71f6a012be73f49030004', '1');

				-- ******************************* 添加菜单[ 编辑 ] *******************************
				-- ----------------------------- sys_domain表insert语句 ----------------------------
				INSERT INTO sys_domain VALUES ('402881e62be71f6a012be73ff6c70006', 'a', '编辑', '1', '/a', '/a', '1', '', '1', '402881e62be71f6a012be73eb6b60002');
				-- ----------------------------- sys_resource表insert语句 ----------------------------
				INSERT INTO sys_resource VALUES ('402881e62be799b9012be79d0ec00001', '1', 'doEdit', '/question/questionCategory/questionCategory_edit.action', '402881e62be71f6a012be73ff6c70006');
				-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
				INSERT INTO sys_domain_organ VALUES ('402881e62be71f6a012be73ff6c70006', '1');

				-- ******************************* 添加菜单[ 删除 ] *******************************
				-- ----------------------------- sys_domain表insert语句 ----------------------------
				INSERT INTO sys_domain VALUES ('402881e62be71f6a012be74077bf0008', 'a', '删除', '1', '/a', '/a', '1', '', '1', '402881e62be71f6a012be73eb6b60002');
				-- ----------------------------- sys_resource表insert语句 ----------------------------
				INSERT INTO sys_resource VALUES ('402881e62be742f9012be74489cd0005', '1', 'doDelete', '/question/questionCategory/questionCategory_delete.action', '402881e62be71f6a012be74077bf0008');
				-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
				INSERT INTO sys_domain_organ VALUES ('402881e62be71f6a012be74077bf0008', '1');

		-- ******************************* 添加菜单[ 题目管理 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881e62becee71012bed0b37ab0007', 'a', '题目管理', '0', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/1042.gif', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/1042.gif', '1', '', '3', '402881ec297e0a3e01297e0ea5dd0001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881ce36eccb6d0136ed44ce910005', '1', '', '/question/question/question_main.action', '402881e62becee71012bed0b37ab0007');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881e62becee71012bed0b37ab0007', '1');

			-- ******************************* 添加菜单[ 题目列表管理 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881e62becee71012bed0bc2280009', 'a', '题目列表管理', '0', '/a', '/a', '1', '', '1', '402881e62becee71012bed0b37ab0007');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881e62bf0ba56012bf0bdb7a00002', '1', '', '/question/question/question_list.action', '402881e62becee71012bed0bc2280009');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881e62becee71012bed0bc2280009', '1');

				-- ******************************* 添加菜单[ 添加 ] *******************************
				-- ----------------------------- sys_domain表insert语句 ----------------------------
				INSERT INTO sys_domain VALUES ('402881e62becee71012bed0c4022000b', 'a', '新增', '1', '/a', '/a', '1', '', '1', '402881e62becee71012bed0bc2280009');
				-- ----------------------------- sys_resource表insert语句 ----------------------------
				INSERT INTO sys_resource VALUES ('402881e62bf0ba56012bf0be2ee30003', '1', 'doAdd', '/question/question/question_add.action', '402881e62becee71012bed0c4022000b');
				-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
				INSERT INTO sys_domain_organ VALUES ('402881e62becee71012bed0c4022000b', '1');

				-- ******************************* 添加菜单[ 导入 ] *******************************
				-- ----------------------------- sys_domain表insert语句 ----------------------------
				INSERT INTO sys_domain VALUES ('402881e62becee71012bed0c9f4a000d', 'a', '导入', '1', '/a', '/a', '1', '', '1', '402881e62becee71012bed0bc2280009');
				-- ----------------------------- sys_resource表insert语句 ----------------------------
				INSERT INTO sys_resource VALUES ('402881e62c053bd5012c053e48310001', '1', 'doImport', '/question/question/question_importQuestion.action', '402881e62becee71012bed0c9f4a000d');
				-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
				INSERT INTO sys_domain_organ VALUES ('402881e62becee71012bed0c9f4a000d', '1');

				-- ******************************* 添加菜单[ 编辑 ] *******************************
				-- ----------------------------- sys_domain表insert语句 ----------------------------
				INSERT INTO sys_domain VALUES ('402881e62becee71012bed0d0ab6000f', 'a', '编辑', '1', '/q', '/q', '1', '', '1', '402881e62becee71012bed0bc2280009');
				-- ----------------------------- sys_resource表insert语句 ----------------------------
				INSERT INTO sys_resource VALUES ('402881e62bf0ba56012bf0bef6790005', '1', 'doEdit', '/question/question/question_edit.action', '402881e62becee71012bed0d0ab6000f');
				-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
				INSERT INTO sys_domain_organ VALUES ('402881e62becee71012bed0d0ab6000f', '1');

				-- ******************************* 添加菜单[ 删除 ] *******************************
				-- ----------------------------- sys_domain表insert语句 ----------------------------
				INSERT INTO sys_domain VALUES ('402881e62becee71012bed0d61810011', 'a', '删除', '1', '/a', '/a', '1', '', '1', '402881e62becee71012bed0bc2280009');
				-- ----------------------------- sys_resource表insert语句 ----------------------------
				INSERT INTO sys_resource VALUES ('402881e62bf0ba56012bf0bf3bb00006', '1', 'doDelete', '/question/question/question_delete.action', '402881e62becee71012bed0d61810011');
				-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
				INSERT INTO sys_domain_organ VALUES ('402881e62becee71012bed0d61810011', '1');

		-- ******************************* 添加菜单[ 考试角色管理 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881d9298dd70d01298de7ab470001', 'a', '考试角色管理', '0', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/netchat.gif', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/netchat.gif', '1', '', '4', '402881ec297e0a3e01297e0ea5dd0001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881ce36ec72410136ecb9fdc30021', '1', '', '/examrole/examRole/examRole_list.action', '402881d9298dd70d01298de7ab470001');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881d9298dd70d01298de7ab470001', '1');

			-- ******************************* 添加菜单[ 添加 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881e62bd176cc012bd1dc72f80002', 'a', '新增', '1', '/examination/images/ico/netchat.gif', '/examination/images/ico/netchat.gif', '1', '', '1', '402881d9298dd70d01298de7ab470001');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881e62bd176cc012bd1dc72f80003', '1', 'doAdd', '/examrole/examRole/examRole_add.action', '402881e62bd176cc012bd1dc72f80002');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881e62bd176cc012bd1dc72f80002', '1');

			-- ******************************* 添加菜单[ 编辑 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881e62bd176cc012bd1dcf0f20004', 'a', '编辑', '1', '/examination/images/ico/netchat.gif', '/examination/images/ico/netchat.gif', '1', '', '1', '402881d9298dd70d01298de7ab470001');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881e62bd20f2b012bd2152f710001', '1', 'doEdit', '/examrole/examRole/examRloe_edit.action', '402881e62bd176cc012bd1dcf0f20004');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881e62bd176cc012bd1dcf0f20004', '1');

			-- ******************************* 添加菜单[ 删除 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881e62bd176cc012bd1dd87a40006', 'a', '删除', '1', '/examination/images/ico/netchat.gif', '/examination/images/ico/netchat.gif', '1', '', '1', '402881d9298dd70d01298de7ab470001');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881e62bd176cc012bd1dd87a40007', '1', 'doDelete', '/examrole/examRole/examRole_delete.action', '402881e62bd176cc012bd1dd87a40006');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881e62bd176cc012bd1dd87a40006', '1');

			-- ******************************* 添加菜单[ 分配角色所包含的用户 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881e62bd25460012bd26420bb0001', 'a', '分配角色所包含的用户', '2', '/platform/theme/default/images/icon/info.gif', '/platform/theme/default/images/icon/info.gif', '1', '', '1', '402881d9298dd70d01298de7ab470001');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881e62bd2aec5012bd2c0f8400001', '1', 'doAssignRoleUser', '/examrole/examRole/examRole_assignUser.action', '402881e62bd25460012bd26420bb0001');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881e62bd25460012bd26420bb0001', '1');

		-- ******************************* 添加菜单[ 试卷管理 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881ec297e0a3e01297e108fcb000b', 'a', '试卷管理', '0', '/explorer/images/ico/005.gif', '/explorer/images/ico/005.gif', '1', '', '5', '402881ec297e0a3e01297e0ea5dd0001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881f42c28eef2012c2901368c0005', '1', '', '/paper/paper/paper_list.action', '402881ec297e0a3e01297e108fcb000b');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881ec297e0a3e01297e108fcb000b', '1');

			-- ******************************* 添加菜单[ 添加 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881ec2bcd992f012bcda852040005', 'a', '新增', '1', '/#', '/#', '1', '', '1', '402881ec297e0a3e01297e108fcb000b');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881ec2bcd992f012bcda852040006', '1', 'doAdd', '/paper/paper/paper_add.action', '402881ec2bcd992f012bcda852040005');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881ec2bcd992f012bcda852040005', '1');

			-- ******************************* 添加菜单[ 导入试卷 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881f52be1540e012be1668a920008', 'a', '导入试卷', '1', '', '', '1', '', '1', '402881ec297e0a3e01297e108fcb000b');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881f52be167a8012be1f43683000c', '1', 'doImport', '/paper/paper/paper_importView.action', '402881f52be1540e012be1668a920008');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881f52be1540e012be1668a920008', '1');

			-- ******************************* 添加菜单[ 编辑 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881ec2bcd992f012bcda8cca20007', 'a', '编辑', '1', '/#', '/#', '1', '', '5', '402881ec297e0a3e01297e108fcb000b');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881ec2bcd992f012bcda8cca20008', '1', 'doEdit', '/paper/paper/paper_edit.action', '402881ec2bcd992f012bcda8cca20007');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881ec2bcd992f012bcda8cca20007', '1');

			-- ******************************* 添加菜单[ 删除 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881ec2bcd992f012bcda957ac0009', 'a', '删除', '1', '/#', '/#', '1', '', '10', '402881ec297e0a3e01297e108fcb000b');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881ec2bcd992f012bcda957ac000a', '1', 'doDel', '/paper/paper/paper_delete.action', '402881ec2bcd992f012bcda957ac0009');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881ec2bcd992f012bcda957ac0009', '1');

	-- ******************************* 添加菜单[ 参加考试 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881ec297e151a01297e1aaa960006', 'a', '参加考试', '0', '/explorer/images/ico/001.gif', '/explorer/images/ico/001.gif', '1', '', '3', '402881ec297d4a2601297dfacf8e0001');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881f42c28eef2012c28ff061f0001', '1', '', 'aaa', '402881ec297e151a01297e1aaa960006');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881ec297e151a01297e1aaa960006', '1');

		-- ******************************* 添加菜单[ 正式考试 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881ec297e151a01297e1b0d570008', 'a', '正式考试', '0', '/explorer/images/ico/006.gif', '/explorer/images/ico/006.gif', '1', '', '1', '402881ec297e151a01297e1aaa960006');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881f42c28eef2012c29020ad40006', '1', '', '/join/join/join_list.action', '402881ec297e151a01297e1b0d570008');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881ec297e151a01297e1b0d570008', '1');

		-- ******************************* 添加菜单[ 模拟考试 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881ec297e151a01297e1b6a8b000a', 'a', '模拟考试', '0', '/explorer/images/ico/007.gif', '/explorer/images/ico/007.gif', '1', '', '5', '402881ec297e151a01297e1aaa960006');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881f42c28eef2012c29024af90007', '1', '', '/join/simulation/simulation_list.action', '402881ec297e151a01297e1b6a8b000a');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881ec297e151a01297e1b6a8b000a', '1');

		-- ******************************* 添加菜单[ 自由练习 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881ec297e151a01297e1bb436000c', 'a', '自由练习', '0', '/explorer/images/ico/008.gif', '/explorer/images/ico/008.gif', '1', '', '15', '402881ec297e151a01297e1aaa960006');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881f42c28eef2012c29028f170008', '1', '', '/join/free/free_startExam.action', '402881ec297e151a01297e1bb436000c');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881ec297e151a01297e1bb436000c', '1');

	-- ******************************* 添加菜单[ 考试统计  ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881ec297e0a3e01297e0efc6a0003', 'a', '考试统计 ', '0', '/explorer/images/ico/107.gif', '/explorer/images/ico/107.gif', '1', '', '5', '402881ec297d4a2601297dfacf8e0001');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881f42c28eef2012c291161090017', '1', '', '/aa', '402881ec297e0a3e01297e0efc6a0003');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881ec297e0a3e01297e0efc6a0003', '1');

		-- ******************************* 添加菜单[ 按单位统计 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881ec297e0a3e01297e1164800011', 'a', '按单位统计', '0', '/explorer/images/ico/129.gif', '/explorer/images/ico/129.gif', '1', '', '1', '402881ec297e0a3e01297e0efc6a0003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881f42c28eef2012c29124be60018', '1', '', '/statistic/statistic/statistic_examCountByOrgan.action', '402881ec297e0a3e01297e1164800011');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881ec297e0a3e01297e1164800011', '1');

		-- ******************************* 添加菜单[ 考试角色统计 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881ec297e0a3e01297e119e4d0013', 'a', '考试角色统计', '0', '/explorer/images/ico/956.gif', '/explorer/images/ico/956.gif', '1', '', '5', '402881ec297e0a3e01297e0efc6a0003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881f42c28eef2012c291332ac0019', '1', '', '/statistic/statistic/statistic_examCountByRole.action', '402881ec297e0a3e01297e119e4d0013');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881ec297e0a3e01297e119e4d0013', '1');

		-- ******************************* 添加菜单[ 我的考试试卷 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881af35d111c80135d1152bdd0003', 'a', '我的考试试卷', '0', '/explorer/images/ico/947.gif', '/explorer/images/ico/947.gif', '1', '', '12', '402881ec297e0a3e01297e0efc6a0003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881af35d111c80135d115e6c10006', '1', '', '/statistic/statistic/statistic_myPaper.action', '402881af35d111c80135d1152bdd0003');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881af35d111c80135d1152bdd0003', '1');

		-- ******************************* 添加菜单[ 按考试统计 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881ec297e0a3e01297e11e75c0015', 'a', '按考试统计', '0', '/explorer/images/ico/958.gif', '/explorer/images/ico/958.gif', '1', '', '15', '402881ec297e0a3e01297e0efc6a0003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881f42c28eef2012c291425f5001a', '1', '', '/statistic/statistic/statistic_main.action', '402881ec297e0a3e01297e11e75c0015');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881ec297e0a3e01297e11e75c0015', '1');

		-- ******************************* 添加菜单[ 部门考试试卷 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881f52e1249bb012e12560a400002', 'a', '部门考试试卷', '0', '/explorer/images/ico/947.gif', '/explorer/images/ico/947.gif', '1', '看本部门的', '20', '402881ec297e0a3e01297e0efc6a0003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881af35d111c80135d114735b0002', '1', '', '/statistic/statistic/statistic_examHistoryDept.action', '402881f52e1249bb012e12560a400002');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881f52e1249bb012e12560a400002', '1');

		-- ******************************* 添加菜单[ 发起考试统计 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881e62bc755f5012bc76711d9000b', 'a', '发起考试统计', '0', '/explorer/images/ico/453.gif', '/explorer/images/ico/453.gif', '1', '', '21', '402881ec297e0a3e01297e0efc6a0003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881f42c28eef2012c2916b720001c', '1', '', '/statistic/statistic/statistic_examTimesByOrgan.action', '402881e62bc755f5012bc76711d9000b');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881e62bc755f5012bc76711d9000b', '1');

		-- ******************************* 添加菜单[ 考试汇总排行 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881ec297e0a3e01297e123a010017', 'a', '考试汇总排行', '0', '/explorer/images/ico/947.gif', '/explorer/images/ico/947.gif', '1', '看所有的', '30', '402881ec297e0a3e01297e0efc6a0003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881af35d111c80135d11435e50001', '1', '', '/statistic/statistic/statistic_examHistory.action', '402881ec297e0a3e01297e123a010017');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881ec297e0a3e01297e123a010017', '1');

	-- ******************************* 添加菜单[ 错情分析 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881ec297e0a3e01297e0f41ee0005', 'a', '错情分析', '0', '/explorer/images/ico/003.gif', '/explorer/images/ico/003.gif', '1', '', '10', '402881ec297d4a2601297dfacf8e0001');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881f42c28eef2012c2900955a0003', '1', '', 'ddff', '402881ec297e0a3e01297e0f41ee0005');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881ec297e0a3e01297e0f41ee0005', '1');

		-- ******************************* 添加菜单[ 题目错情分析 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881ec297e0a3e01297e127b5f0019', 'a', '题目错情分析', '0', '/explorer/images/ico/571.gif', '/explorer/images/ico/571.gif', '1', '', '1', '402881ec297e0a3e01297e0f41ee0005');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881f42c28eef2012c2918ab20001d', '1', '', '/errorAnalysis/errorAnalysis/errorAnalysis_main.action', '402881ec297e0a3e01297e127b5f0019');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881ec297e0a3e01297e127b5f0019', '1');

			-- ******************************* 添加菜单[ 题目错情列表 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881d92bf223bb012bf226f44f0002', 'a', '题目错情列表', '0', '/a', '/a', '1', '', '1', '402881ec297e0a3e01297e127b5f0019');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881d92bf22752012bf22c2fd90003', '1', '', '/errorAnalysis/errorAnalysis/errorAnalysis_list.action', '402881d92bf223bb012bf226f44f0002');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881d92bf223bb012bf226f44f0002', '1');

		-- ******************************* 添加菜单[ 题库错情分析 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881ec297e0a3e01297e12b1d0001b', 'a', '题库错情分析', '0', '/explorer/images/ico/015.gif', '/explorer/images/ico/015.gif', '1', '', '10', '402881ec297e0a3e01297e0f41ee0005');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881f42c28eef2012c29050b90000f', '1', '', '/errorAnalysis/errorAnalysis/errorAnalysis_categoryMain.action', '402881ec297e0a3e01297e12b1d0001b');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881ec297e0a3e01297e12b1d0001b', '1');

			-- ******************************* 添加菜单[ 题库错情分析列表 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881d92c0b3ab6012c0b7bbccd0002', 'a', '题库错情分析列表', '0', '/a', '/a', '1', '', '1', '402881ec297e0a3e01297e12b1d0001b');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881d92c0b3ab6012c0b7bbccd0003', '1', '', '/errorAnalysis/errorAnalysis/errorAnalysis_categoryList.action', '402881d92c0b3ab6012c0b7bbccd0002');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881d92c0b3ab6012c0b7bbccd0002', '1');

	-- ******************************* 添加菜单[ 保密学习系统 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881ec297e0a3e01297e0fa3c50007', 'a', '保密学习系统', '0', '/explorer/images/ico/902.gif', '/explorer/images/ico/902.gif', '1', '', '15', '402881ec297d4a2601297dfacf8e0001');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881f42c28eef2012c2925fc5d001e', '1', '', 'aaa', '402881ec297e0a3e01297e0fa3c50007');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881ec297e0a3e01297e0fa3c50007', '1');

		-- ******************************* 添加菜单[ 知识库维护 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881e62bc755f5012bc76a78a30010', 'a', '知识库维护', '0', '/explorer/images/ico/573.gif', '/explorer/images/ico/573.gif', '1', '', '1', '402881ec297e0a3e01297e0fa3c50007');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881af3614dcf3013614e9a5490016', '1', '', '/law/lawClass/lawClass_main.action', '402881e62bc755f5012bc76a78a30010');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881e62bc755f5012bc76a78a30010', '1');

			-- ******************************* 添加菜单[ 法律法规分类列表 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881d92be170aa012be17704580004', 'a', '法律法规分类列表', '2', '/aaa', '/aaa', '1', '', '1', '402881e62bc755f5012bc76a78a30010');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881d92be170aa012be17704580005', '1', '', '/law/lawClass/lawClass_list.action', '402881d92be170aa012be17704580004');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881d92be170aa012be17704580004', '1');

				-- ******************************* 添加菜单[ 新增 ] *******************************
				-- ----------------------------- sys_domain表insert语句 ----------------------------
				INSERT INTO sys_domain VALUES ('402881d92bcd7c11012bcd8098180002', 'a', '新增', '1', '/a', '/a', '1', '', '2', '402881d92be170aa012be17704580004');
				-- ----------------------------- sys_resource表insert语句 ----------------------------
				INSERT INTO sys_resource VALUES ('402881d92bcd7c11012bcd8098270003', '1', 'doAdd', '/law/lawClass/lawClass_add.action', '402881d92bcd7c11012bcd8098180002');
				-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
				INSERT INTO sys_domain_organ VALUES ('402881d92bcd7c11012bcd8098180002', '1');

				-- ******************************* 添加菜单[ 编辑 ] *******************************
				-- ----------------------------- sys_domain表insert语句 ----------------------------
				INSERT INTO sys_domain VALUES ('402881d92bcd7c11012bcd819b4f0004', 'a', '编辑', '1', '/a', '/a', '1', '', '3', '402881d92be170aa012be17704580004');
				-- ----------------------------- sys_resource表insert语句 ----------------------------
				INSERT INTO sys_resource VALUES ('402881d92bcd7c11012bcd819b4f0005', '1', 'doEdit', '/law/lawClass/lawClass_edit.action', '402881d92bcd7c11012bcd819b4f0004');
				-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
				INSERT INTO sys_domain_organ VALUES ('402881d92bcd7c11012bcd819b4f0004', '1');

				-- ******************************* 添加菜单[ 删除 ] *******************************
				-- ----------------------------- sys_domain表insert语句 ----------------------------
				INSERT INTO sys_domain VALUES ('402881d92bcd7c11012bcd824ff90006', 'a', '删除', '1', '/a', '/a', '1', '', '4', '402881d92be170aa012be17704580004');
				-- ----------------------------- sys_resource表insert语句 ----------------------------
				INSERT INTO sys_resource VALUES ('402881d92bcdc9ad012bcde9350a0001', '1', 'doDel', '/law/lawClass/lawClass_delete.action', '402881d92bcd7c11012bcd824ff90006');
				-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
				INSERT INTO sys_domain_organ VALUES ('402881d92bcd7c11012bcd824ff90006', '1');

		-- ******************************* 添加菜单[ 知识点维护 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881e62bc755f5012bc76aff860012', 'a', '知识点维护', '0', '/explorer/images/ico/575.gif', '/explorer/images/ico/575.gif', '1', '', '3', '402881ec297e0a3e01297e0fa3c50007');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881af3614dcf3013614e9c91d0017', '1', '', '/law/law/law_main.action', '402881e62bc755f5012bc76aff860012');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881e62bc755f5012bc76aff860012', '1');

			-- ******************************* 添加菜单[ 法律法规列表 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881d92be1782c012be1a8ad7a0005', 'a', '法律法规列表', '0', '/a', '/a', '1', '', '1', '402881e62bc755f5012bc76aff860012');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881d92be1782c012be1a8ad7a0006', '1', '', '/law/law/law_list.action', '402881d92be1782c012be1a8ad7a0005');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881d92be1782c012be1a8ad7a0005', '1');

				-- ******************************* 添加菜单[ 新增 ] *******************************
				-- ----------------------------- sys_domain表insert语句 ----------------------------
				INSERT INTO sys_domain VALUES ('402881d92bd20282012bd20f4b720001', 'a', '新增', '1', '/a', '/a', '1', '', '2', '402881d92be1782c012be1a8ad7a0005');
				-- ----------------------------- sys_resource表insert语句 ----------------------------
				INSERT INTO sys_resource VALUES ('402881d92bd20282012bd20f4bb10002', '1', 'doAdd', '/law/law/law_add.action', '402881d92bd20282012bd20f4b720001');
				-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
				INSERT INTO sys_domain_organ VALUES ('402881d92bd20282012bd20f4b720001', '1');

				-- ******************************* 添加菜单[ 编辑 ] *******************************
				-- ----------------------------- sys_domain表insert语句 ----------------------------
				INSERT INTO sys_domain VALUES ('402881d92bd20282012bd20fdd420003', 'a', '编辑', '1', '/a', '/a', '1', '', '3', '402881d92be1782c012be1a8ad7a0005');
				-- ----------------------------- sys_resource表insert语句 ----------------------------
				INSERT INTO sys_resource VALUES ('402881d92bd20282012bd210244e0005', '1', 'doEdit', '/law/law/law_edit.action', '402881d92bd20282012bd20fdd420003');
				-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
				INSERT INTO sys_domain_organ VALUES ('402881d92bd20282012bd20fdd420003', '1');

				-- ******************************* 添加菜单[ 删除 ] *******************************
				-- ----------------------------- sys_domain表insert语句 ----------------------------
				INSERT INTO sys_domain VALUES ('402881d92bd20282012bd210ae8d0006', 'a', '删除', '1', '/a', '/a', '1', '', '4', '402881d92be1782c012be1a8ad7a0005');
				-- ----------------------------- sys_resource表insert语句 ----------------------------
				INSERT INTO sys_resource VALUES ('402881d92be6804d012be6878ed50001', '1', 'doDel', '/law/law/law_delete.action', '402881d92bd20282012bd210ae8d0006');
				-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
				INSERT INTO sys_domain_organ VALUES ('402881d92bd20282012bd210ae8d0006', '1');

				-- ******************************* 添加菜单[ 导入 ] *******************************
				-- ----------------------------- sys_domain表insert语句 ----------------------------
				INSERT INTO sys_domain VALUES ('402881d92be6804d012be68884120002', 'a', '导入', '1', '/a', '/a', '1', '', '5', '402881d92be1782c012be1a8ad7a0005');
				-- ----------------------------- sys_resource表insert语句 ----------------------------
				INSERT INTO sys_resource VALUES ('402881d92be6804d012be68884120003', '1', 'doImport', '/', '402881d92be6804d012be68884120002');
				-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
				INSERT INTO sys_domain_organ VALUES ('402881d92be6804d012be68884120002', '1');

		-- ******************************* 添加菜单[ 发起在线学习 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881af3614dcf3013614e08f5b0002', 'a', '发起在线学习', '0', '/explorer/images/ico/575.gif', '/explorer/images/ico/575.gif', '1', '', '6', '402881ec297e0a3e01297e0fa3c50007');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881af36158093013615c35e680028', '1', '', '/examination/learnPhase/learnPhase_list.action', '402881af3614dcf3013614e08f5b0002');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881af3614dcf3013614e08f5b0002', '1');

			-- ******************************* 添加菜单[ 添加学习阶段 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881af3614dcf3013614e163c20005', 'a', '添加学习阶段', '1', '', '', '1', '', '1', '402881af3614dcf3013614e08f5b0002');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881af3614dcf3013614e163c20006', '1', 'doAdd', '/examination/learnPhase/learnPhase_add.action', '402881af3614dcf3013614e163c20005');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881af3614dcf3013614e163c20005', '1');

			-- ******************************* 添加菜单[ 修改学习阶段 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881af3614dcf3013614e20c180007', 'a', '修改学习阶段', '1', '', '', '1', '', '2', '402881af3614dcf3013614e08f5b0002');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881af3614eb7c01361512be770007', '1', 'doEdit', '/examination/learnPhase/learnPhase_edit.action', '402881af3614dcf3013614e20c180007');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881af3614dcf3013614e20c180007', '1');

			-- ******************************* 添加菜单[ 删除学习阶段 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881af3614dcf3013614e2ada70009', 'a', '删除学习阶段', '1', '', '', '1', '', '5', '402881af3614dcf3013614e08f5b0002');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881af3614dcf3013614e2ada7000a', '1', 'doDelete', '/examination/learnPhase/learnPhase_delete.action', '402881af3614dcf3013614e2ada70009');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881af3614dcf3013614e2ada70009', '1');

		-- ******************************* 添加菜单[ 组织在线学习 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881af3614dcf3013614e56555000b', 'a', '组织在线学习', '0', '/explorer/images/ico/575.gif', '/explorer/images/ico/575.gif', '1', '', '8', '402881ec297e0a3e01297e0fa3c50007');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881af36158093013615c32b520027', '1', '', '/examination/learn/learn_list.action', '402881af3614dcf3013614e56555000b');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881af3614dcf3013614e56555000b', '1');

			-- ******************************* 添加菜单[ 组织学习 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881af3614dcf3013614e5e69b000d', 'a', '组织学习', '1', '', '', '1', '', '1', '402881af3614dcf3013614e56555000b');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881af3614dcf3013614e63344000f', '1', 'doAdd', '/examination/learn/learn_add.action', '402881af3614dcf3013614e5e69b000d');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881af3614dcf3013614e5e69b000d', '1');

			-- ******************************* 添加菜单[ 修改 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881af3614dcf3013614e741680010', 'a', '修改', '1', '', '', '1', '', '1', '402881af3614dcf3013614e56555000b');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881af3614eb7c01361512940b0006', '1', 'doEdit', '/examination/learn/learn_edit.action', '402881af3614dcf3013614e741680010');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881af3614dcf3013614e741680010', '1');

			-- ******************************* 添加菜单[ 删除 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881af3614dcf3013614e8ce130014', 'a', '删除', '1', '', '', '1', '', '5', '402881af3614dcf3013614e56555000b');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881af3614eb7c013615130a640008', '1', 'doDelete', '/examination/learn/learn_delete.action', '402881af3614dcf3013614e8ce130014');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881af3614dcf3013614e8ce130014', '1');

		-- ******************************* 添加菜单[ 参加在线学习 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881af3614dcf3013614eafe9f0019', 'a', '参加在线学习', '0', '/explorer/images/ico/575.gif', '/explorer/images/ico/575.gif', '1', '', '12', '402881ec297e0a3e01297e0fa3c50007');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881af36158093013615c38fa90029', '1', '', '/examination/enterLearn/enterLearn_list.action', '402881af3614dcf3013614eafe9f0019');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881af3614dcf3013614eafe9f0019', '1');

