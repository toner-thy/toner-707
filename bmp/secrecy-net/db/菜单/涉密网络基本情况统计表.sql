

	-- ******************************* 添加菜单[ 涉密网络基本情况统计表 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('4028818b3eb206ee013eb20e025c0000', 'a', '涉密网络基本情况统计表', '0', '', '', '1', '', '1', '4028819136e8139b0136e82e82df0005');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('4028818b3eb206ee013eb23f9b950003', '1', '', '/net/secrecyBasic/secrecyBasic_list.action', '4028818b3eb206ee013eb20e025c0000');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('4028818b3eb206ee013eb20e025c0000', '1');

		-- ******************************* 添加菜单[ （市直机关）新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('4028818b3eb206ee013eb24078fe0004', 'a', '（市直机关）新增', '1', '', '', '1', '', '1', '4028818b3eb206ee013eb20e025c0000');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('4028818b3eb265da013eb28e7dac0000', '1', 'doAdd', '/net/secrecyBasic/secrecyBasic_add.action', '4028818b3eb206ee013eb24078fe0004');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('4028818b3eb206ee013eb24078fe0004', '1');

		-- ******************************* 添加菜单[ 区、县（市）新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('4028818b3eb265da013eb28f4bdb0001', 'a', '区、县（市）新增', '1', '', '', '1', '', '1', '4028818b3eb206ee013eb20e025c0000');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('4028818b3eb265da013eb28f4bdb0002', '1', 'doAdd2', '/net/secrecyBasic/secrecyBasic_add2.action', '4028818b3eb265da013eb28f4bdb0001');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('4028818b3eb265da013eb28f4bdb0001', '1');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('4028818b3eb206ee013eb240ed5f0006', 'a', '编辑', '1', '', '', '1', '', '2', '4028818b3eb206ee013eb20e025c0000');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('4028818b3eb206ee013eb2418751000a', '1', 'doEdit', '/net/secrecyBasic/secrecyBasic_edit.action', '4028818b3eb206ee013eb240ed5f0006');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('4028818b3eb206ee013eb240ed5f0006', '1');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('4028818b3eb206ee013eb2415d7d0008', 'a', '删除', '1', '', '', '1', '', '3', '4028818b3eb206ee013eb20e025c0000');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('4028818b3eb206ee013eb241be1a000b', '1', 'doDel', '/net/secrecyBasic/secrecyBasic_del.action', '4028818b3eb206ee013eb2415d7d0008');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('4028818b3eb206ee013eb2415d7d0008', '1');