-- ******************************* 添加菜单[ demo ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881c43bd52e8d013bd52f5bd90000', 'a', 'demo', '0', '', '', '1', '', '0', '402881a23b9813c6013b98180dbd0002');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881c43bd52e8d013bd57c3e77000e', '1', '', '', '402881c43bd52e8d013bd52f5bd90000');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881c43bd52e8d013bd52f5bd90000', '1');

	-- ******************************* 添加菜单[ 保密委员会 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881c43bd52e8d013bd52fbe3c0002', 'a', '保密委员会', '0', '', '', '1', '', '1', '402881c43bd52e8d013bd52f5bd90000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881c43bd58ddf013bd58ea2b70000', '1', '', '/bmp/demo/secrecyOrgan/secrecyCommittee/secrecyCommittee_detail_nodate.jsp', '402881c43bd52e8d013bd52fbe3c0002');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881c43bd52e8d013bd52fbe3c0002', '1');

	-- ******************************* 添加菜单[ 保密办（保密局） ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881c43bd52e8d013bd565308c0009', 'a', '保密办（保密局）', '0', '', '', '1', '', '1', '402881c43bd52e8d013bd52f5bd90000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881c43bd593dd013bd59528e20000', '1', '', '/bmp/demo/secrecyOrgan/secrecyBureau/secrecyBureau_detail_nodate.jsp', '402881c43bd52e8d013bd565308c0009');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881c43bd52e8d013bd565308c0009', '1');

	-- ******************************* 添加菜单[ 单位保密工作机构 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881c43bd52e8d013bd578679d000c', 'a', '单位保密工作机构', '0', '', '', '1', '', '1', '402881c43bd52e8d013bd52f5bd90000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881c43bd593dd013bd5953a950001', '1', '', '/bmp/demo/secrecyOrgan/secrecyOrgan_detail_nodate.jsp', '402881c43bd52e8d013bd578679d000c');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881c43bd52e8d013bd578679d000c', '1');

	-- ******************************* 添加菜单[ 涉密人员 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881c43bd52e8d013bd581af78000f', 'a', '涉密人员', '0', '', '', '1', '', '1', '402881c43bd52e8d013bd52f5bd90000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881c43bd593dd013bd59554950002', '1', '', '/bmp/demo/secrecyPerson/secrecyPerson_list.jsp', '402881c43bd52e8d013bd581af78000f');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881c43bd52e8d013bd581af78000f', '1');

	-- ******************************* 添加菜单[ 要害部门 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881c43bd593dd013bd5a149020003', 'a', '要害部门', '0', '', '', '1', '', '1', '402881c43bd52e8d013bd52f5bd90000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881c43bd593dd013bd5a149020004', '1', '', '/bmp/demo/keyDepartment/keyDepartment_list.jsp', '402881c43bd593dd013bd5a149020003');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881c43bd593dd013bd5a149020003', '1');

	-- ******************************* 添加菜单[ 要害部位 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881c43bd593dd013bd5a1efc10005', 'a', '要害部位', '0', '', '', '1', '', '1', '402881c43bd52e8d013bd52f5bd90000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881c43bd593dd013bd5a8398f0007', '1', '', '/bmp/demo/keyPart/keyPart_list.jsp', '402881c43bd593dd013bd5a1efc10005');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881c43bd593dd013bd5a1efc10005', '1');

