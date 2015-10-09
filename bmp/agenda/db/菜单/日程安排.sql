-- ******************************* 添加菜单[ 协同办公 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881cd36a4941c0136a4961cb90002', 'a', '协同办公', '-1', '', '', '1', '', '1', '1');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881cd36a4941c0136a4961cd80003', '1', '', '', '402881cd36a4941c0136a4961cb90002');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881cd36a4941c0136a4961cb90002', '1');

	-- ******************************* 添加菜单[ 日程管理 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881e52eb9186d012eb9485de80006', 'a', '日程管理', '0', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/250.gif', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/250.gif', '1', '', '1', '402881cd36a4941c0136a4961cb90002');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881ce36ec72410136ec9368df000d', '1', '', '', '402881e52eb9186d012eb9485de80006');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881e52eb9186d012eb9485de80006', '1');

		-- ******************************* 添加菜单[ 我的日程 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881e52eb9186d012eb948ba7f0008', 'a', '我的日程', '0', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/251.gif', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/251.gif', '1', '', '2', '402881e52eb9186d012eb9485de80006');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881ce36ec72410136ec9530120010', '1', '', '/agenda/agenda/agenda_list.action', '402881e52eb9186d012eb948ba7f0008');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881e52eb9186d012eb948ba7f0008', '1');

			-- ******************************* 添加菜单[ 新增 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881ff2f669967012f669de89d0002', 'a', '新增', '1', 'null', 'null', '1', 'null', '1', '402881e52eb9186d012eb948ba7f0008');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881ff2f669967012f669de8ad0003', '1', 'doAdd', '/agenda/agenda/agenda_add.action', '402881ff2f669967012f669de89d0002');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881ff2f669967012f669de89d0002', '1');

			-- ******************************* 添加菜单[ 编辑 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881ff2f669967012f669edf520004', 'a', '编辑', '1', 'null', 'null', '1', 'null', '2', '402881e52eb9186d012eb948ba7f0008');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881ff2f669967012f669edf520005', '1', 'doEdit', '/agenda/agenda/agenda_edit.action', '402881ff2f669967012f669edf520004');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881ff2f669967012f669edf520004', '1');

			-- ******************************* 添加菜单[ 删除 ] *******************************
			-- ----------------------------- sys_domain表insert语句 ----------------------------
			INSERT INTO sys_domain VALUES ('402881ff2f669967012f669fdea10006', 'a', '删除', '1', 'null', 'null', '1', 'null', '3', '402881e52eb9186d012eb948ba7f0008');
			-- ----------------------------- sys_resource表insert语句 ----------------------------
			INSERT INTO sys_resource VALUES ('402881ff2f669967012f669fdea10007', '1', 'doDel', '/agenda/agenda/agenda_delete.action', '402881ff2f669967012f669fdea10006');
			-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
			INSERT INTO sys_domain_organ VALUES ('402881ff2f669967012f669fdea10006', '1');

		-- ******************************* 添加菜单[ 日程安排 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881e52eb9186d012eb94984f3000a', 'a', '日程安排', '0', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/256.gif', 'cd2/exam/theme/borderlayout/skin/blue/img/ico/256.gif', '1', '', '3', '402881e52eb9186d012eb9485de80006');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881ce36ec72410136ec948df6000f', '1', '', '/agenda/agenda/agenda_calendar.action', '402881e52eb9186d012eb94984f3000a');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881e52eb9186d012eb94984f3000a', '1');
