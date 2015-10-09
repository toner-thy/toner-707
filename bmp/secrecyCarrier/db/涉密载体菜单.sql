
DROP TABLE IF EXISTS `bm_destructionscrap`;
CREATE TABLE `bm_destructionscrap` (
  `id` varchar(39) NOT NULL COMMENT 'ID',
  `type` int(11) default NULL COMMENT '涉密载体形式（含报废涉密设备）',
  `content` varchar(100) default NULL COMMENT '涉密载体内容',
  `number` int(11) default NULL COMMENT '数量',
  `secrecyLevel` int(11) default NULL COMMENT '密级',
  `toPlace` varchar(200) default NULL COMMENT '销毁、报废流向',
  `responsibleUser` varchar(39) default NULL COMMENT '经办人',
  `approver` varchar(39) default NULL COMMENT '审批人',
  `description` varchar(200) default NULL COMMENT '备注',
  `STATE` int(11) default NULL COMMENT '状态：0：使用1：删除',
  `CREATE_ORGAN` varchar(39) default NULL COMMENT '创建单位',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '创建人',
  `CREATE_TIME` date default NULL COMMENT '创建时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人',
  `MODIFY_TIME` date default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `bm_secrecyborrow`;
CREATE TABLE `bm_secrecyborrow` (
  `id` varchar(39) NOT NULL COMMENT 'ID',
  `date` date default NULL COMMENT '借阅时间',
  `name` varchar(100) default NULL COMMENT '文件名称',
  `number` varchar(11) default NULL COMMENT '数量',
  `secrecyLevel` int(11) default NULL COMMENT '密级',
  `docNumber` varchar(100) default NULL COMMENT '文号',
  `borrowUserInfo` varchar(39) default NULL COMMENT '借阅人',
  `approver` varchar(39) default NULL COMMENT '审批人',
  `returnDate` date default NULL COMMENT '归还时间',
  `description` varchar(200) default NULL COMMENT '备注',
  `STATE` int(11) default NULL COMMENT '状态：0：使用1：删除',
  `CREATE_ORGAN` varchar(39) default NULL COMMENT '创建单位',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '创建人',
  `CREATE_TIME` date default NULL COMMENT '创建时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人',
  `MODIFY_TIME` date default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `bm_secrecymaintain`;
CREATE TABLE `bm_secrecymaintain` (
  `id` varchar(39) NOT NULL COMMENT 'ID',
  `date` date default NULL COMMENT '送修时间',
  `type` varchar(100) default NULL COMMENT '设备类型(品牌型号)',
  `leaderIdea` varchar(11) default NULL COMMENT '领导审批意见',
  `depIdea` varchar(500) default NULL COMMENT '部门意见',
  `reason` varchar(39) default NULL COMMENT '维修原因',
  `secrecyLevel` int(11) default NULL COMMENT '密级',
  `useDepartment` varchar(39) default NULL COMMENT '使用部门',
  `seeUserInfo` varchar(39) default NULL COMMENT '监修人',
  `maintainOrgan` varchar(39) default NULL COMMENT '维修单位',
  `description` varchar(500) default NULL COMMENT '备注',
  `STATE` int(11) default NULL COMMENT '状态：0：使用1：删除',
  `CREATE_ORGAN` varchar(39) default NULL COMMENT '创建单位',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '创建人',
  `CREATE_TIME` date default NULL COMMENT '创建时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人',
  `MODIFY_TIME` date default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `bm_secrecyprint`;
CREATE TABLE `bm_secrecyprint` (
  `id` varchar(39) NOT NULL COMMENT 'ID',
  `date` date default NULL COMMENT '打印时间',
  `name` varchar(100) default NULL COMMENT '文件名称',
  `number` varchar(11) default NULL COMMENT '份数',
  `pageNo` varchar(11) default NULL COMMENT '每份页数',
  `secrecyLevel` int(11) default NULL COMMENT '密级',
  `docNumber` varchar(100) default NULL COMMENT '文号',
  `undertaker` varchar(500) default NULL COMMENT '承办人',
  `approver` varchar(500) default NULL COMMENT '审批人',
  `draftingDep` varchar(39) default NULL COMMENT '起草部门',
  `description` varchar(200) default NULL COMMENT '备注',
  `STATE` int(11) default NULL COMMENT '状态：0：使用1：删除',
  `CREATE_ORGAN` varchar(39) default NULL COMMENT '创建单位',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '创建人',
  `CREATE_TIME` date default NULL COMMENT '创建时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人',
  `MODIFY_TIME` date default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `bm_secrecyCopy`;
CREATE TABLE `bm_secrecyCopy` (
  `id` varchar(39) NOT NULL COMMENT 'ID',
  `date` date default NULL COMMENT '复印时间',
  `name` varchar(100) default NULL COMMENT '文件名称',
  `number` varchar(11) default NULL COMMENT '份数',
  `pageNo` varchar(11) default NULL COMMENT '每份页数',
  `secrecyLevel` int(11) default NULL COMMENT '密级',
  `docNumber` varchar(100) default NULL COMMENT '文号',
  `undertaker` varchar(39) default NULL COMMENT '承办人',
  `applicant` varchar(39) default NULL COMMENT '申请人',
  `approver` varchar(39) default NULL COMMENT '审批人',
  `didOrgan` varchar(39) default NULL COMMENT '文件制发单位',
   `usePlace` varchar(500) default NULL COMMENT '用途',
  `draftingDep` varchar(39) default NULL COMMENT '申请部门',
  `description` varchar(200) default NULL COMMENT '备注',
  `STATE` int(11) default NULL COMMENT '状态：0：使用1：删除',
  `CREATE_ORGAN` varchar(39) default NULL COMMENT '创建单位',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '创建人',
  `CREATE_TIME` date default NULL COMMENT '创建时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人',
  `MODIFY_TIME` date default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `bm_secrecytechnologyprevention`;
CREATE TABLE `bm_secrecytechnologyprevention` (
  `id` varchar(39) NOT NULL COMMENT 'ID',
  `date` date default NULL COMMENT '时间',
  `name` varchar(100) default NULL COMMENT '设备名称',
  `number` varchar(11) default NULL COMMENT '数量',
  `purpose` varchar(500) default NULL COMMENT '用途',
  `equipDep` varchar(39) default NULL COMMENT '装备部门',
  `keyPart` varchar(39) default NULL COMMENT '要害部位',
  `description` varchar(500) default NULL COMMENT '备注',
  `STATE` int(11) default NULL COMMENT '状态：0：使用1：删除',
  `CREATE_ORGAN` varchar(39) default NULL COMMENT '创建单位',
  `CREATE_PERSON` varchar(39) default NULL COMMENT '创建人',
  `CREATE_TIME` date default NULL COMMENT '创建时间',
  `MODIFY_PERSON` varchar(39) default NULL COMMENT '修改人',
  `MODIFY_TIME` date default NULL COMMENT '修改时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `sys_dictionary_field` VALUES ('secrecyCarrier_type', 'secrecyCarrier_type', '涉密载体形式', 0, 70, 'bmp');
INSERT INTO `sys_dictionary_option` VALUES ('secrecyCarrier_type_1', '涉密计算机', 1, 1, 1, 'secrecyCarrier_type', NULL);
INSERT INTO `sys_dictionary_option` VALUES ('secrecyCarrier_type_2', '涉密传真机', 2, 2, 1, 'secrecyCarrier_type', NULL);
INSERT INTO `sys_dictionary_option` VALUES ('secrecyCarrier_type_3', '涉密复印机', 3, 3, 1, 'secrecyCarrier_type', NULL);
INSERT INTO `sys_dictionary_option` VALUES ('secrecyCarrier_type_4', '涉密扫描仪', 4, 4, 1, 'secrecyCarrier_type', NULL);
INSERT INTO `sys_dictionary_option` VALUES ('secrecyCarrier_type_5', '涉密刻录机', 5, 5, 1, 'secrecyCarrier_type', NULL);
INSERT INTO `sys_dictionary_option` VALUES ('secrecyCarrier_type_6', '涉密打印机', 6, 6, 1, 'secrecyCarrier_type', NULL);
INSERT INTO `sys_dictionary_option` VALUES ('secrecyCarrier_type_7', '涉密移动硬盘', 7, 7, 1, 'secrecyCarrier_type', NULL);
INSERT INTO `sys_dictionary_option` VALUES ('secrecyCarrier_type_8', '涉密U盘', 8, 8, 1, 'secrecyCarrier_type', NULL);
INSERT INTO `sys_dictionary_option` VALUES ('secrecyCarrier_type_9', '涉密光盘', 9, 9, 1, 'secrecyCarrier_type', NULL);
INSERT INTO `sys_dictionary_option` VALUES ('secrecyCarrier_type_0', '其它',10, 10, 1, 'secrecyCarrier_type', NULL);

-- ******************************* 添加菜单[ 涉密载体管理 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('ca82caeb45fdddcc0145fdeb25010000', 'a', '涉密载体管理', '0', '', '', '1', '', '3', 'lawsystem-1001d');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25010001', '1', '', '', 'ca82caeb45fdddcc0145fdeb25010000');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('ca82caeb45fdddcc0145fdeb25010000', '1');

	-- ******************************* 添加菜单[ 涉密载体打印情况 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('bm_secrecyPrint001', 'a', '涉密载体打印情况', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('bm_secrecyPrint002', '1', '', '/bmp/secrecyPrint/secrecyPrint_list.action', 'bm_secrecyPrint001');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('bm_secrecyPrint001', '1');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bm_secrecyPrint003', 'a', '新增', '1', '', '', '1', '', '1', 'bm_secrecyPrint001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb2501001b', '1', 'doAdd', '/bmp/secrecyPrint/secrecyPrint_add.action', 'bm_secrecyPrint003');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bm_secrecyPrint003', '1');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bm_secrecyPrint004', 'a', '编辑', '1', '', '', '1', '', '2', 'bm_secrecyPrint001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25010016', '1', 'doEdit', '/bmp/secrecyPrint/secrecyPrint_edit.action', 'bm_secrecyPrint004');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bm_secrecyPrint004', '1');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bm_secrecyPrint005', 'a', '删除', '1', '', '', '1', '', '3', 'bm_secrecyPrint001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25010014', '1', 'doDel', '/bmp/secrecyPrint/secrecyPrint_delete.action', 'bm_secrecyPrint005');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bm_secrecyPrint005', '1');

	-- ******************************* 添加菜单[ 涉密载体借阅情况 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('bm_secrecyBorrow001', 'a', '涉密载体借阅情况', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('bm_secrecyBorrow002', '1', '', '/bmp/secrecyBorrow/secrecyBorrow_list.action', 'bm_secrecyBorrow001');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('bm_secrecyBorrow001', '1');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bm_secrecyBorrow003', 'a', '新增', '1', '', '', '1', '', '1', 'bm_secrecyBorrow001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25010010', '1', 'doAdd', '/bmp/secrecyBorrow/secrecyBorrow_add.action', 'bm_secrecyBorrow003');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bm_secrecyBorrow003', '1');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bm_secrecyBorrow004', 'a', '编辑', '1', '', '', '1', '', '2', 'bm_secrecyBorrow001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25010009', '1', 'doEdit', '/bmp/secrecyBorrow/secrecyBorrow_edit.action', 'bm_secrecyBorrow004');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bm_secrecyBorrow004', '1');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bm_secrecyBorrow005', 'a', '删除', '1', '', '', '1', '', '3', 'bm_secrecyBorrow001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25010008', '1', 'doDel', '/bmp/secrecyBorrow/secrecyBorrow_delete.action', 'bm_secrecyBorrow005');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bm_secrecyBorrow005', '1');

	-- ******************************* 添加菜单[ 销毁报废情况 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('bm_destructionScrap001', 'a', '销毁报废情况', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('bm_destructionScrap002', '1', '', '/bmp/destructionScrap/destructionScrap_list.action', 'bm_destructionScrap001');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('bm_destructionScrap001', '1');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bm_destructionScrap003', 'a', '新增', '1', '', '', '1', '', '1', 'bm_destructionScrap001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25010002', '1', 'doAdd', '/bmp/destructionScrap/destructionScrap_add.action', 'bm_destructionScrap003');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bm_destructionScrap003', '1');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bm_destructionScrap004', 'a', '编辑', '1', '', '', '1', '', '2', 'bm_destructionScrap001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25010003', '1', 'doEdit', '/bmp/destructionScrap/destructionScrap_edit.action', 'bm_destructionScrap004');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bm_destructionScrap004', '1');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bm_destructionScrap005', 'a', '删除', '1', '', '', '1', '', '3', 'bm_destructionScrap001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25010004', '1', 'doDel', '/bmp/destructionScrap/destructionScrap_delete.action', 'bm_destructionScrap005');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bm_destructionScrap005', '1');

	-- ******************************* 添加菜单[ 涉密设备维修情况 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('bmp_secrecyMaintain001', 'a', '涉密设备维修情况', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('bmp_secrecyMaintain002', '1', '', '/bmp/secrecyMaintain/secrecyMaintain_list.action', 'bmp_secrecyMaintain001');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('bmp_secrecyMaintain001', '1');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bmp_secrecyMaintain003', 'a', '新增', '1', '', '', '1', '', '1', 'bmp_secrecyMaintain001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb2501111b', '1', 'doAdd', '/bmp/secrecyMaintain/secrecyMaintain_add.action', 'bmp_secrecyMaintain003');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bmp_secrecyMaintain003', '1');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bmp_secrecyMaintain004', 'a', '编辑', '1', '', '', '1', '', '2', 'bmp_secrecyMaintain001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25011216', '1', 'doEdit', '/bmp/secrecyMaintain/secrecyMaintain_edit.action', 'bmp_secrecyMaintain004');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bmp_secrecyMaintain004', '1');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bmp_secrecyMaintain005', 'a', '删除', '1', '', '', '1', '', '3', 'bmp_secrecyMaintain001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25011214', '1', 'doDel', '/bmp/secrecyMaintain/secrecyMaintain_delete.action', 'bmp_secrecyMaintain005');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bmp_secrecyMaintain005', '1');

	-- ******************************* 添加菜单[ 销毁报废情况查询 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82caeb45fdddcc0145fdeb25010011', 'a', '销毁报废情况查询', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25010005', '1', '', '/bmp/destructionScrap/destructionScrap_main.action', 'ca82caeb45fdddcc0145fdeb25010011');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82caeb45fdddcc0145fdeb25010011', '1');

	-- ******************************* 添加菜单[ 涉密载体借阅情况查询 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82caeb45fdddcc0145fdeb25010012', 'a', '涉密载体借阅情况查询', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25010007', '1', '', '/bmp/secrecyBorrow/secrecyBorrow_main.action', 'ca82caeb45fdddcc0145fdeb25010012');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82caeb45fdddcc0145fdeb25010012', '1');

	-- ******************************* 添加菜单[ 涉密载体打印情况查询 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82caeb45fdddcc0145fdeb25010013', 'a', '涉密载体打印情况查询', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25010015', '1', '', '/bmp/secrecyPrint/secrecyPrint_main.action', 'ca82caeb45fdddcc0145fdeb25010013');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82caeb45fdddcc0145fdeb25010013', '1');

	-- ******************************* 添加菜单[ 装备保密技术防范设备情况查询 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82caeb45fdddcc0145fdeb25010113', 'a', '装备保密技术防范设备情况查询', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25010114', '1', '', '/bmp/secrecyTechnologyPrevention/secrecyTechnologyPrevention_main.action', 'ca82caeb45fdddcc0145fdeb25010113');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82caeb45fdddcc0145fdeb25010113', '1');

	-- ******************************* 添加菜单[ 涉密设备维修情况查询 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82caeb45fdddcc0145fdeb25011113', 'a', '涉密设备维修情况查询', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25011114', '1', '', '/bmp/secrecyMaintain/secrecyMaintain_main.action', 'ca82caeb45fdddcc0145fdeb25011113');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82caeb45fdddcc0145fdeb25011113', '1');

	-- ******************************* 添加菜单[ 装备保密技术防范设备情况 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('bmp_secrecyTechnologyPrevention001', 'a', '装备保密技术防范设备情况', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('bmp_secrecyTechnologyPrevention002', '1', '', '/bmp/secrecyTechnologyPrevention/secrecyTechnologyPrevention_list.action', 'bmp_secrecyTechnologyPrevention001');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('bmp_secrecyTechnologyPrevention001', '1');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bmp_secrecyTechnologyPrevention003', 'a', '新增', '1', '', '', '1', '', '1', 'bmp_secrecyTechnologyPrevention001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb2501011b', '1', 'doAdd', '/bmp/secrecyTechnologyPrevention/secrecyTechnologyPrevention_add.action', 'bmp_secrecyTechnologyPrevention003');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bmp_secrecyTechnologyPrevention003', '1');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bmp_secrecyTechnologyPrevention004', 'a', '编辑', '1', '', '', '1', '', '2', 'bmp_secrecyTechnologyPrevention001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25010216', '1', 'doEdit', '/bmp/secrecyTechnologyPrevention/secrecyTechnologyPrevention_edit.action', 'bmp_secrecyTechnologyPrevention004');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bmp_secrecyTechnologyPrevention004', '1');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bmp_secrecyTechnologyPrevention005', 'a', '删除', '1', '', '', '1', '', '3', 'bmp_secrecyTechnologyPrevention001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb45fdddcc0145fdeb25010214', '1', 'doDel', '/bmp/secrecyTechnologyPrevention/secrecyTechnologyPrevention_delete.action', 'bmp_secrecyTechnologyPrevention005');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bmp_secrecyTechnologyPrevention005', '1');



		-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('bmp_secrecyCopy001', 'a', '涉密载体复印情况', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('bmp_secrecyCopy002', '1', '', '/bmp/secrecyCopy/secrecyCopy_list.action', 'bmp_secrecyCopy001');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('bmp_secrecyCopy001', '1');

		-- ******************************* 添加菜单[ 新增 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bmp_secrecyCopy003', 'a', '新增', '1', '', '', '1', '', '1', 'bmp_secrecyCopy001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('fa82caeb45fdddcc0145fdeb2501111b', '1', 'doAdd', '/bmp/secrecyCopy/secrecyCopy_add.action', 'bmp_secrecyCopy003');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bmp_secrecyCopy003', '1');

		-- ******************************* 添加菜单[ 编辑 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bmp_secrecyCopy004', 'a', '编辑', '1', '', '', '1', '', '2', 'bmp_secrecyCopy001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('fa82caeb45fdddcc0145fdeb25011216', '1', 'doEdit', '/bmp/secrecyCopy/secrecyCopy_edit.action', 'bmp_secrecyCopy004');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bmp_secrecyCopy004', '1');

		-- ******************************* 添加菜单[ 删除 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('bmp_secrecyCopy005', 'a', '删除', '1', '', '', '1', '', '3', 'bmp_secrecyCopy001');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('fa82caeb45fdddcc0145fdeb25011214', '1', 'doDel', '/bmp/secrecyCopy/secrecyCopy_delete.action', 'bmp_secrecyCopy005');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('bmp_secrecyCopy005', '1');

	-- ******************************* 添加菜单[ 涉密载体复印情况查询 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('fa82caeb45fdddcc0145fdeb25011113', 'a', '涉密载体复印情况查询', '0', '', '', '1', '', '1', 'ca82caeb45fdddcc0145fdeb25010000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('fa82caeb45fdddcc0145fdeb25011114', '1', '', '/bmp/secrecyCopy/secrecyCopy_main.action', 'fa82caeb45fdddcc0145fdeb25011113');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('fa82caeb45fdddcc0145fdeb25011113', '1');

