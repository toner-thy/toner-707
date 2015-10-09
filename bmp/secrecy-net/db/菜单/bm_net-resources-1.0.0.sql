-- ******************************* 添加菜单[ 数据填报 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('4028819f3eb682be013eb683ec250000', 'a', '数据填报', '0', '', '', '1', '', '1', '402881a23b9813c6013b98180dbd0002');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('4028819f3eb682be013eb683ec440001', '1', '', '', '4028819f3eb682be013eb683ec250000');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('4028819f3eb682be013eb683ec250000', '1');

	-- ******************************* 添加菜单[ 涉密网络基本情况统计表 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('4028818b3eb206ee013eb20e025c0000', 'a', '涉密网络基本情况统计表', '0', '', '', '1', '', '1', '4028819f3eb682be013eb683ec250000');
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

	-- ******************************* 添加菜单[ 非涉密网络基本情况 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('4028819f3eb27866013eb27faa130000', 'a', '非涉密网络基本情况', '0', '', '', '1', '', '20', '4028819f3eb682be013eb683ec250000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('4028819f3eb7ad47013eb7af20940001', '1', '', '/bmp/pucha/noSecNet/save.action', '4028819f3eb27866013eb27faa130000');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('4028819f3eb27866013eb27faa130000', '1');

	-- ******************************* 添加菜单[ 涉密计算机及“三合一” ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('4028819f3eb20910013eb21f025c0007', 'a', '涉密计算机及“三合一”', '0', '/#', '/#', '1', '', '30', '4028819f3eb682be013eb683ec250000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('4028819f3eb7ad47013eb7af45240002', '1', '', '/bmp/pucha/seccomputer3i1/save.action', '4028819f3eb20910013eb21f025c0007');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('4028819f3eb20910013eb21f025c0007', '1');

	-- ******************************* 添加菜单[ 机关队伍建设培训统计 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881a23eb1acce013eb1af2a090000', 'a', '机关队伍建设培训统计', '0', '', '', '1', '', '40', '4028819f3eb682be013eb683ec250000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881a23eb2bedb013eb2c306470002', '1', '', '/bmp/secrecyFoundTrain/secrecyFoundTrain_add.action', '402881a23eb1acce013eb1af2a090000');
	INSERT INTO sys_resource VALUES ('4028819f3eb7ad47013eb7afdb690003', '1', '', '/bmp/secrecyFoundTrain/secrecyFoundTrain_add.action', '402881a23eb1acce013eb1af2a090000');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881a23eb1acce013eb1af2a090000', '1');

	-- ******************************* 添加菜单[ 保密队伍建设情况统计表 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('4028818b3eb17ab6013eb17c0b110000', 'a', '保密队伍建设情况统计表', '0', '', '', '1', '', '45', '4028819f3eb682be013eb683ec250000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('4028819f3eb7ad47013eb7b0212c0004', '1', '', '/net/secrecyTroops/secrecyTroops_add.action', '4028818b3eb17ab6013eb17c0b110000');
	INSERT INTO sys_resource VALUES ('4028818b3eb206ee013eb2423c5c000c', '1', '', '/net/secrecyTroops/secrecyTroops_add.action', '4028818b3eb17ab6013eb17c0b110000');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('4028818b3eb17ab6013eb17c0b110000', '1');

	-- ******************************* 添加菜单[ 技术手段情况统计 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('4028819f3eb62daf013eb62fcaca0000', 'a', '技术手段情况统计', '0', '', '', '1', '', '50', '4028819f3eb682be013eb683ec250000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('4028819f3eb7ad47013eb7b07e020005', '1', '', '/bmp/pucha/tecToolInfo/save.action', '4028819f3eb62daf013eb62fcaca0000');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('4028819f3eb62daf013eb62fcaca0000', '1');

	-- ******************************* 添加菜单[ 网络监督管理工作机制及制度建设 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881a23eb6c2b3013eb6c740c70002', 'a', '网络监督管理工作机制及制度建设', '-1', '', '', '1', '', '60', '4028819f3eb682be013eb683ec250000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('4028819f3eb7ad47013eb7b3dd3a0008', '1', '', '/bmp/secrecySupervision/secrecySupervision_add.action', '402881a23eb6c2b3013eb6c740c70002');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881a23eb6c2b3013eb6c740c70002', '1');

	-- ******************************* 添加菜单[ 保密技术培训情况 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881a23eb2bedb013eb2c2dd230000', 'a', '保密技术培训情况', '0', '', '', '1', '', '70', '4028819f3eb682be013eb683ec250000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881a23eb2bedb013eb2c3ec320003', '1', '', '/bmp/secrecyTechnologyTrain/secrecyTechnologyTrain_add.action', '402881a23eb2bedb013eb2c2dd230000');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881a23eb2bedb013eb2c2dd230000', '1');

	-- ******************************* 添加菜单[ 网络保密管理情况意见建议 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881a33eb16ac2013eb16e03e00002', 'a', '网络保密管理情况意见建议', '0', '', '', '1', '', '80', '4028819f3eb682be013eb683ec250000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881a33eb16ac2013eb16f540f0004', '1', '', '/bmp/secrecyadvice/secrecyAdvice_add.action', '402881a33eb16ac2013eb16e03e00002');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881a33eb16ac2013eb16e03e00002', '1');

	-- ******************************* 添加菜单[ 填报一览 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('4028819f3eb682be013eb68531180002', 'a', '填报一览', '0', '', '', '1', '', '200', '4028819f3eb682be013eb683ec250000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('4028819f3eb71b17013eb732cbf70000', '1', '', '/bmp/pucha/detail/detail.action', '4028819f3eb682be013eb68531180002');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('4028819f3eb682be013eb68531180002', '1');

	-- ******************************* 添加菜单[ 各单位填报一览 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('4028819f3eb6a707013eb6c70ae20003', 'a', '各单位填报一览', '0', '', '', '1', '', '300', '4028819f3eb682be013eb683ec250000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('4028819f3eb71b17013eb733110f0001', '1', '', '/bmp/pucha/detail/query.action', '4028819f3eb6a707013eb6c70ae20003');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('4028819f3eb6a707013eb6c70ae20003', '1');

