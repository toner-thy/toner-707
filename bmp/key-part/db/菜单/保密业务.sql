-- ******************************* 添加菜单[ 保密业务 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a23b9813c6013b981a045d0004', 'a', '保密业务', '0', '/platform/theme/default/images/platformmenu/menu_00.gif', '/platform/theme/default/images/platformmenu/menu_00.gif', '1', '', '100', '402881a23b9813c6013b98180dbd0002');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a13bcc73a3013bcca0a45c0003', '1', '', '', '402881a23b9813c6013b981a045d0004');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a23b9813c6013b981a045d0004', '1');
INSERT INTO sys_domain_organ VALUES ('402881a23b9813c6013b981a045d0004', '402881a13c1dcb59013c1e1c9d70000f');

	-- ******************************* 添加菜单[ 保密要害部门 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881a23b9813c6013b981b513f0007', 'a', '保密要害部门', '0', '/platform/theme/default/images/platformmenu/menu_01.gif', '/explorer/images/ico/491.gif', '1', '', '1', '402881a23b9813c6013b981a045d0004');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881a23b9cec63013b9ceecfcf0000', '1', '', '/bmp/keySection/keySection_list.action', '402881a23b9813c6013b981b513f0007');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881a23b9813c6013b981b513f0007', '1');
	INSERT INTO sys_domain_organ VALUES ('402881a23b9813c6013b981b513f0007', '402881a13c1dcb59013c1e1c9d70000f');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a23babfb74013bac1036fe0000', 'a', '新增', '1', '', '', '1', '', '1', '402881a23b9813c6013b981b513f0007');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a23babfb74013bac10d7940004', '1', 'doAdd', '/bmp/keySection/keySection_add.action', '402881a23babfb74013bac1036fe0000');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a23babfb74013bac1036fe0000', '1');
		INSERT INTO sys_domain_organ VALUES ('402881a23babfb74013bac1036fe0000', '402881a13c1dcb59013c1e1c9d70000f');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a23babfb74013bac10b15f0002', 'a', '编辑', '1', '', '', '1', '', '2', '402881a23b9813c6013b981b513f0007');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a23babfb74013bac10b15f0003', '1', 'doEdit', '/bmp/keySection/keySection_edit.action', '402881a23babfb74013bac10b15f0002');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a23babfb74013bac10b15f0002', '1');
		INSERT INTO sys_domain_organ VALUES ('402881a23babfb74013bac10b15f0002', '402881a13c1dcb59013c1e1c9d70000f');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a23babfb74013bac1132a40005', 'a', '删除', '1', '', '', '1', '', '3', '402881a23b9813c6013b981b513f0007');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a23babfb74013bac1c14780007', '1', 'doDel', '/bmp/keySection/keySection_delete.action', '402881a23babfb74013bac1132a40005');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a23babfb74013bac1132a40005', '1');
		INSERT INTO sys_domain_organ VALUES ('402881a23babfb74013bac1132a40005', '402881a13c1dcb59013c1e1c9d70000f');

	-- ******************************* 添加菜单[ 保密要害部位 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881a43b990414013b9905b2970000', 'a', '保密要害部位', '0', '', '', '1', '', '2', '402881a23b9813c6013b981a045d0004');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881a13c222101013c222958b20003', '1', '', '/bmp/part/part_list.action', '402881a43b990414013b9905b2970000');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881a43b990414013b9905b2970000', '1');
	INSERT INTO sys_domain_organ VALUES ('402881a43b990414013b9905b2970000', '402881a13c1dcb59013c1e1c9d70000f');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a43bb0c954013bb0e7b3390000', 'a', '新增', '1', '', '', '1', '', '1', '402881a43b990414013b9905b2970000');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a43bb1adc8013bb1b3dc9a0001', '1', 'doAdd', '/bmp/part/part_add.action', '402881a43bb0c954013bb0e7b3390000');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a43bb0c954013bb0e7b3390000', '1');
		INSERT INTO sys_domain_organ VALUES ('402881a43bb0c954013bb0e7b3390000', '402881a13c1dcb59013c1e1c9d70000f');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a43bb0c954013bb0e8f4630002', 'a', '编辑', '1', '', '', '1', '', '2', '402881a43b990414013b9905b2970000');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a43bb18b00013bb194c6c70003', '1', 'doEdit', '/bmp/part/part_edit.action', '402881a43bb0c954013bb0e8f4630002');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a43bb0c954013bb0e8f4630002', '1');
		INSERT INTO sys_domain_organ VALUES ('402881a43bb0c954013bb0e8f4630002', '402881a13c1dcb59013c1e1c9d70000f');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a43bb0c954013bb0eac73f0005', 'a', '删除', '1', '', '', '1', '', '3', '402881a43b990414013b9905b2970000');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a43bb18b00013bb19482f80002', '1', 'doDelete', '/bmp/part/part_delete.action', '402881a43bb0c954013bb0eac73f0005');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a43bb0c954013bb0eac73f0005', '1');
		INSERT INTO sys_domain_organ VALUES ('402881a43bb0c954013bb0eac73f0005', '402881a13c1dcb59013c1e1c9d70000f');


		-- ******************************* 添加菜单[ 导入 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a13c651600013c65319db00003', 'a', '导入', '1', '', '', '1', '', '4', '402881a13c222101013c2227fa3b0000');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a13c6535d3013c653730ef0000', '1', 'doReportWord', '/bmp/secrecyperson/secrecyPerson_importWord.action', '402881a13c651600013c65319db00003');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a13c651600013c65319db00003', '1');

	-- ******************************* 添加菜单[ 机关涉密人员 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881a13c222101013c2227fa3b0000', 'a', '机关涉密人员', '0', '', '', '1', '', '3', '402881a23b9813c6013b981a045d0004');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881a13c222101013c222a4b4f0005', '1', '', '/bmp/secrecyperson/secrecyPerson_list.action', '402881a13c222101013c2227fa3b0000');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881a13c222101013c2227fa3b0000', '1');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a13c222101013c222b28a00006', 'a', '新增', '1', '', '', '1', '', '1', '402881a13c222101013c2227fa3b0000');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a13c222101013c222b28a00007', '1', 'doAdd', '/bmp/secrecyperson/secrecyPerson_add.action', '402881a13c222101013c222b28a00006');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a13c222101013c222b28a00006', '1');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a13c222101013c222bd1b10008', 'a', '编辑', '1', '', '', '1', '', '2', '402881a13c222101013c2227fa3b0000');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a13c222101013c222cd1fa000c', '1', 'doEdit', '/bmp/secrecyperson/secrecyPerson_edit.action', '402881a13c222101013c222bd1b10008');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a13c222101013c222bd1b10008', '1');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('402881a13c222101013c222c9a7f000a', 'a', '删除', '1', '', '', '1', '', '3', '402881a13c222101013c2227fa3b0000');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('402881a13c222101013c222c9a7f000b', '1', 'doDelete', '/bmp/secrecyperson/secrecyPerson_delete.action', '402881a13c222101013c222c9a7f000a');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('402881a13c222101013c222c9a7f000a', '1');

	-- ******************************* 添加菜单[ 要害部位查询(未完善) ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881a43bbb33de013bbb3598100000', 'a', '要害部位查询(未完善)', '0', '', '', '1', '', '4', '402881a23b9813c6013b981a045d0004');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881a13c222101013c2229c3430004', '1', '', '/bmp/part/part_main.action', '402881a43bbb33de013bbb3598100000');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881a43bbb33de013bbb3598100000', '1');
	INSERT INTO sys_domain_organ VALUES ('402881a43bbb33de013bbb3598100000', '402881a13c1dcb59013c1e1c9d70000f');

	-- ******************************* 添加菜单[ 机关涉密人员查询 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881a13c222101013c222d8491000d', 'a', '机关涉密人员查询', '0', '', '', '1', '', '5', '402881a23b9813c6013b981a045d0004');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881a13c222101013c222d8491000e', '1', '', '/bmp/secrecyperson/secrecyPerson_main.action', '402881a13c222101013c222d8491000d');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881a13c222101013c222d8491000d', '1');

