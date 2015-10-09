-- ******************************* 添加菜单[ 数据一览 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('402881a23e3f1ec2013e3ff27b8e0001', 'a', '数据一览', '0', '', '', '1', '', '101', '402881a23b9813c6013b981a045d0004');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a23e3f1ec2013e4005fe0f0005', '1', '', '/bmp/secrecyStatistics/secrecyStatistics_list.action', '402881a23e3f1ec2013e3ff27b8e0001');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('402881a23e3f1ec2013e3ff27b8e0001', '1');
	-- ******************************* 添加菜单[ 打印 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881a23e45226a013e454a310a0000', 'a', '打印', '1', '', '', '1', '', '1', '402881a23e3f1ec2013e3ff27b8e0001');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881a23e45226a013e454bd0910004', '1', 'doPrint', '/bmp/secrecyStatistics/secrecyStatistics_print.action', '402881a23e45226a013e454a310a0000');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881a23e45226a013e454a310a0000', '1');
	-- ******************************* 添加菜单[ 导出 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('402881a23e45226a013e454b2caf0002', 'a', '导出', '1', '', '', '1', '', '2', '402881a23e3f1ec2013e3ff27b8e0001');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('402881a23e45226a013e454d916b0006', '1', 'doExport()', '/bmp/secrecyStatistics/secrecyStatistics_exportData.action', '402881a23e45226a013e454b2caf0002');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('402881a23e45226a013e454b2caf0002', '1');

