-- ******************************* 添加菜单[ 装备配备类别 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('40288153271cf34d01271db6cb820004', 'a', '装备配备类别', '0', 'bmp/BMP_ICO/bm/sb/1.gif', '/bmp/BMP_ICO/保密业务/单位设备配备/设备分类.gif', '1', '', '6', '402881a23b9813c6013b981a045d0004');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('4028818c376e5acd01376e99abf3003d', '1', '', '/equipmentType_list.action', '40288153271cf34d01271db6cb820004');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('40288153271cf34d01271db6cb820004', '1');

	-- ******************************* 添加菜单[ 新增 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('40288153271cf34d01271db892d40006', 'a', '新增', '1', '/platform/theme/default/images/icon/info.gif', '/platform/theme/default/images/icon/info.gif', '1', 'null', '1', '40288153271cf34d01271db6cb820004');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('40288153271cf34d01271db892d40007', '1', 'doAdd', '/equipmentType_add.action', '40288153271cf34d01271db892d40006');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('40288153271cf34d01271db892d40006', '1');

	-- ******************************* 添加菜单[ 编辑 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('40288153271cf34d01271db985df0008', 'a', '编辑', '1', '/platform/theme/default/images/icon/info.gif', '/platform/theme/default/images/icon/info.gif', '1', 'null', '1', '40288153271cf34d01271db6cb820004');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('40288153271cf34d01271db985df0009', '1', 'doEdit', '/equipmentType_edit.action', '40288153271cf34d01271db985df0008');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('40288153271cf34d01271db985df0008', '1');

	-- ******************************* 添加菜单[ 删除 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('40288153271cf34d01271dba6d90000a', 'a', '删除', '1', '/platform/theme/default/images/icon/info.gif', '/platform/theme/default/images/icon/info.gif', '1', 'null', '1', '40288153271cf34d01271db6cb820004');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('40288153271cf34d01271dba6d90000b', '1', 'doDel', '/equipmentType_delete.action', '40288153271cf34d01271dba6d90000a');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('40288153271cf34d01271dba6d90000a', '1');

	-- ******************************* 添加菜单[ 详情 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('40288153271cf34d01271dbba6db000c', 'a', '详情', '2', '/platform/theme/default/images/icon/info.gif', '/platform/theme/default/images/icon/info.gif', '1', 'null', '1', '40288153271cf34d01271db6cb820004');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881532721fed30127221b39350004', '1', 'doView', '/equipmentType_view.action', '40288153271cf34d01271dbba6db000c');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('40288153271cf34d01271dbba6db000c', '1');

	-- ******************************* 添加菜单[ 发布 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('4028818d3246e58801324712c74a0006', 'a', '发布', '1', 'null', 'null', '1', 'null', '100', '40288153271cf34d01271db6cb820004');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('4028818d3246e58801324712c74a0007', '1', 'doPublish', '/equipmentType_publishEquipment.action', '4028818d3246e58801324712c74a0006');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('4028818d3246e58801324712c74a0006', '1');

-- ******************************* 添加菜单[ 单位填报装备配备 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('40288153271dd7b201271e1dacba0004', 'a', '单位填报装备配备', '0', 'bmp/BMP_ICO/bm/sb/2.gif', '/bmp/BMP_ICO/保密业务/单位设备配备/设备填报.gif', '1', 'null', '7', '402881a23b9813c6013b981a045d0004');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('4028818d32e21cc20132e248eb0c0024', '1', '', '/equipment_list.action', '40288153271dd7b201271e1dacba0004');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('40288153271dd7b201271e1dacba0004', '1');

	-- ******************************* 添加菜单[ 保存 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('40288153271dd7b201271e1f91d60006', 'a', '保存', '1', '/platform/theme/default/images/icon/info.gif', '/platform/theme/default/images/icon/info.gif', '1', 'null', '1', '40288153271dd7b201271e1dacba0004');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('40288153271dd7b201271e1f91d60007', '1', 'doSave', '/equipment_save.action', '40288153271dd7b201271e1f91d60006');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('40288153271dd7b201271e1f91d60006', '1');

	-- ******************************* 添加菜单[ 详情 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('40288153271dd7b201271e219c6b0008', 'a', '详情', '2', '/platform/theme/default/images/icon/info.gif', '/platform/theme/default/images/icon/info.gif', '1', 'null', '1', '40288153271dd7b201271e1dacba0004');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('40288153273c1d8201273c4b8b4c0001', '1', 'doView', '/equipment_view.action', '40288153271dd7b201271e219c6b0008');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('40288153271dd7b201271e219c6b0008', '1');

-- ******************************* 添加菜单[ 保密装备配备查询 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('40288191324380a40132438505370004', 'a', '保密装备配备查询', '0', 'bmp/theme/borderlayout/skin/blue/img/ico/equipment_checkDistrictList.gif', 'bmp/theme/borderlayout/skin/blue/img/ico/equipment_checkDistrictList.gif', '1', '', '9', '402881a23b9813c6013b981a045d0004');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('4028814736ec51c10136ec66c31f0008', '1', '', '/equipment_checkmain.action', '40288191324380a40132438505370004');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('40288191324380a40132438505370004', '1');

	-- ******************************* 添加菜单[ 树列表 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881913261b110013261b73f150002', 'a', '树列表', '0', 'null', 'null', '1', 'null', '1', '40288191324380a40132438505370004');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881913261b110013261b7e0f30004', '1', '', '/equipment_checkDistrictList.action', '402881913261b110013261b73f150002');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881913261b110013261b73f150002', '1');

-- ******************************* 添加菜单[ 保密装备配备检测 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('4028818c308bbb2201308c0cbae40069', 'a', '保密装备配备检测', '0', 'bmp/BMP_ICO/sys/sys/9.gif', 'bmp/BMP_ICO/sys/sys/9.gif', '1', '', '8', '402881a23b9813c6013b981a045d0004');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('4028814736ec6d2e0136ec9686c80ee7', '1', '', '/bmp/equipmentcheck/equipmentCheck_list.action', '4028818c308bbb2201308c0cbae40069');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('4028818c308bbb2201308c0cbae40069', '1');

	-- ******************************* 添加菜单[ 新增 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('4028818d308d86a401308d8ae1070002', 'a', '新增', '1', 'null', 'null', '1', 'null', '1', '4028818c308bbb2201308c0cbae40069');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('4028818d308d86a401308d8ae1070003', '1', 'doAdd', '/bmp/equipmentcheck/equipmentCheck_add.action', '4028818d308d86a401308d8ae1070002');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('4028818d308d86a401308d8ae1070002', '1');

	-- ******************************* 添加菜单[ 编辑 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('4028818d308d86a401308d8b7e030004', 'a', '编辑', '1', 'null', 'null', '1', 'null', '2', '4028818c308bbb2201308c0cbae40069');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('4028818d308d86a401308d8b7e030005', '1', 'doEdit', '/bmp/equipmentcheck/equipmentCheck_edit.action', '4028818d308d86a401308d8b7e030004');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('4028818d308d86a401308d8b7e030004', '1');

	-- ******************************* 添加菜单[ 删除 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('4028818d308d86a401308d8cc8840006', 'a', '删除', '1', 'null', 'null', '1', 'null', '3', '4028818c308bbb2201308c0cbae40069');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('4028818d308d86a401308d8cc8840007', '1', 'doDel', '/bmp/equipmentcheck/equipmentCheck_del.action', '4028818d308d86a401308d8cc8840006');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('4028818d308d86a401308d8cc8840006', '1');

-- ******************************* 添加菜单[ 单位装备配备情况 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('4028815327229aa2012722a9b8f10001', 'a', '单位装备配备情况', '0', 'bmp/BMP_ICO/bm/sb/3.gif', '/bmp/BMP_ICO/保密业务/单位设备配备/配备情况.gif', '1', '', '10', '402881a23b9813c6013b981a045d0004');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('4028814736ec6d2e0136ec96ba7b0ee8', '1', '', '/equipment_main.action', '4028815327229aa2012722a9b8f10001');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('4028815327229aa2012722a9b8f10001', '1');

	-- ******************************* 添加菜单[ 树列表 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881af3758e80d0137596741a80007', 'a', '树列表', '2', '', '', '1', '', '1', '4028815327229aa2012722a9b8f10001');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881af3758e80d01375967f52a0009', '1', '', '/equipment_mainList.action', '402881af3758e80d0137596741a80007');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881af3758e80d0137596741a80007', '1');




