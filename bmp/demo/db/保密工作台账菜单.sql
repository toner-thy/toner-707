-- ******************************* 添加菜单[ 保密工作台帐 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('ca82cae64581e009014581e86df20000', 'a', '保密工作台帐', '-1', '', '', '1', '', '10', '1');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('ca82cae64581e009014581e8ac240002', '1', '', '', 'ca82cae64581e009014581e86df20000');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('ca82cae64581e009014581e86df20000', '1');

	-- ******************************* 添加菜单[ 保密工作台帐 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('ca82cae64581e009014581ed08790003', 'a', '保密工作台帐', '0', '', '', '1', '', '1', 'ca82cae64581e009014581e86df20000');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('ca82cae64581e009014581eef8b00008', '1', '', '', 'ca82cae64581e009014581ed08790003');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('ca82cae64581e009014581ed08790003', '1');

		-- ******************************* 添加菜单[ 年度工作计划 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82cae64581e009014581ee9a450006', 'a', '年度工作计划', '0', '', '', '1', '', '1', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82cae64581e009014582de896d0017', '1', '', '/bmp/workbench-demo/annualWorkPlan/annualWorkPlan_list.jsp', 'ca82cae64581e009014581ee9a450006');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82cae64581e009014581ee9a450006', '1');

		-- ******************************* 添加菜单[ 会议记录 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82cae64581e009014582dd67630015', 'a', '会议记录', '0', '', '', '1', '', '2', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82cae64581e009014582ded6930018', '1', '', '/bmp/workbench-demo/cahier/cahier_list.jsp', 'ca82cae64581e009014582dd67630015');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82cae64581e009014582dd67630015', '1');

		-- ******************************* 添加菜单[ 保密会议活动 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82cae64581e00901458324a3970019', 'a', '保密会议活动', '0', '', '', '1', '', '3', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82cae64581e00901458325901a001b', '1', '', '/bmp/workbench-demo/participateActivitie/participateActivitie_list.jsp', 'ca82cae64581e00901458324a3970019');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82cae64581e00901458324a3970019', '1');

		-- ******************************* 添加菜单[ 保密工作制度建设情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82cae64581e00901458354cf32001c', 'a', '保密工作制度建设情况', '0', '', '', '1', '', '4', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82cae64581e00901458355cefe001e', '1', '', '/bmp/workbench-demo/buildingSecuritySystem/buildingSecuritySystem_list.jsp', 'ca82cae64581e00901458354cf32001c');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82cae64581e00901458354cf32001c', '1');

		-- ******************************* 添加菜单[ 宣传教育培训情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82cae64586f96c01458710e85d0000', 'a', '宣传教育培训情况', '0', '', '', '1', '', '5', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82cae64586f96c01458710e8ac0001', '1', '', '/bmp/workbench-demo/education/education_list.jsp', 'ca82cae64586f96c01458710e85d0000');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82cae64586f96c01458710e85d0000', '1');

		-- ******************************* 添加菜单[ 保密监督检查情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82cae64586f96c01458711aa750002', 'a', '保密监督检查情况', '0', '', '', '1', '', '6', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82cae64586f96c0145871202690004', '1', '', '/bmp/workbench-demo/supervision/supervision_list.jsp', 'ca82cae64586f96c01458711aa750002');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82cae64586f96c01458711aa750002', '1');

		-- ******************************* 添加菜单[ 实施奖惩情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82cae64586f96c0145871399640005', 'a', '实施奖惩情况', '0', '', '', '1', '', '7', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82cae64586f96c0145871399640006', '1', '', '/bmp/workbench-demo/punishment/punishment_list.jsp', 'ca82cae64586f96c0145871399640005');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82cae64586f96c0145871399640005', '1');

		-- ******************************* 添加菜单[ 论文评奖情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82cae64586f96c0145871565a80007', 'a', '论文评奖情况', '0', '', '', '1', '', '8', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82cae64587f36501458820f16b0000', '1', '', '/bmp/workbench-demo/awards/awards_list.jsp', 'ca82cae64586f96c0145871565a80007');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82cae64586f96c0145871565a80007', '1');

		-- ******************************* 添加菜单[ 承担课题情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82cae64587f365014588228f9a0003', 'a', '承担课题情况', '0', '', '', '1', '', '9', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82cae64587f365014588228f9a0004', '1', '', '/bmp/workbench-demo/subject/subject_list.jsp', 'ca82cae64587f365014588228f9a0003');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82cae64587f365014588228f9a0003', '1');

		-- ******************************* 添加菜单[ 销毁报废情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82cae64586f96c014587169c250009', 'a', '销毁报废情况', '0', '', '', '1', '', '10', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82cae64587f365014588214eec0001', '1', '', '/bmp/workbench-demo/disposal/disposal_list.jsp', 'ca82cae64586f96c014587169c250009');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82cae64586f96c014587169c250009', '1');

		-- ******************************* 添加菜单[ 年度工作总结 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82cae64586f96c01458717785c000b', 'a', '年度工作总结', '-1', '', '', '1', '', '11', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82cae64587f365014588218ba70002', '1', '', '/bmp/workbench-demo/workSummary/workSummary_list.jsp', 'ca82cae64586f96c01458717785c000b');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82cae64586f96c01458717785c000b', '1');

		-- ******************************* 添加菜单[ 定（解）密工作情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb4587562e0145875c81a30000', 'a', '定（解）密工作情况', '0', '', '', '1', '', '20', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb4587562e0145875c81b30001', '1', '', '/bmp/workbench-demo/solutionSecuritySystem/solutionSecuritySystem_list.jsp', 'ca82caeb4587562e0145875c81a30000');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb4587562e0145875c81a30000', '1');

		-- ******************************* 添加菜单[ 本年度收到密件情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb4587562e0145875ecebf0002', 'a', '本年度收到密件情况', '0', '', '', '1', '', '21', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb4587562e0145875ecebf0003', '1', '', '/bmp/workbench-demo/receivedSecurityDocuments/receivedSecurityDocuments_list.jsp', 'ca82caeb4587562e0145875ecebf0002');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb4587562e0145875ecebf0002', '1');

		-- ******************************* 添加菜单[ 接触和知悉绝密级国家秘密文件人员情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb4587562e0145876161510004', 'a', '接触和知悉绝密级国家秘密文件人员情况', '0', '', '', '1', '', '22', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb4587562e01458845e59f000d', '1', '', '/bmp/workbench-demo/contactAndKnowStateSecrets/contactStateSecrets_list.jsp', 'ca82caeb4587562e0145876161510004');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb4587562e0145876161510004', '1');

		-- ******************************* 添加菜单[ 涉密会议（活动）情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb4587562e0145876349990006', 'a', '涉密会议（活动）情况', '0', '', '', '1', '', '23', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb4587562e0145876349990007', '1', '', '/bmp/workbench-demo/classifiedMeeting/classifiedMeeting_list.jsp', 'ca82caeb4587562e0145876349990006');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb4587562e0145876349990006', '1');

		-- ******************************* 添加菜单[ 信息发布保密审查情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb4587562e01458765bdc60008', 'a', '信息发布保密审查情况', '0', '', '', '1', '', '24', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb4587562e014588a4184f000e', '1', '', '/bmp/workbench-demo/informationConfidentiality/informationConfidentiality_list.jsp', 'ca82caeb4587562e01458765bdc60008');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb4587562e01458765bdc60008', '1');

		-- ******************************* 添加菜单[ 涉密网络管理 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb458c232301458c34a8a40000', 'a', '涉密网络管理', '0', '', '', '1', '', '25', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82cae6458c614201458c6431c50000', '1', '', '/bmp/workbench-demo/secretNetwork/secretNetwork_list.jsp', 'ca82caeb458c232301458c34a8a40000');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb458c232301458c34a8a40000', '1');

		-- ******************************* 添加菜单[ 部门网站管理 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb458c232301458c36c53b0002', 'a', '部门网站管理', '0', '', '', '1', '', '26', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb458c232301458c36c53b0003', '1', '', '/bmp/workbench-demo/departmentNetwork/departmentNetwork_list.jsp', 'ca82caeb458c232301458c36c53b0002');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb458c232301458c36c53b0002', '1');

		-- ******************************* 添加菜单[ 涉密载体借阅情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb458c232301458c3c366b0004', 'a', '涉密载体借阅情况', '0', '', '', '1', '', '27', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb458c232301458c42c58e000f', '1', '', '/bmp/workbench-demo/secrecyCarrierLend/secrecyCarrierLend_list.jsp', 'ca82caeb458c232301458c3c366b0004');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb458c232301458c3c366b0004', '1');

		-- ******************************* 添加菜单[ 涉密载体打印情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb458c232301458c3e0cb10007', 'a', '涉密载体打印情况', '0', '', '', '1', '', '28', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb458c232301458c43be850010', '1', '', '/bmp/workbench-demo/secrecyCarrierPrint/secrecyCarrierPrint_list.jsp', 'ca82caeb458c232301458c3e0cb10007');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb458c232301458c3e0cb10007', '1');

		-- ******************************* 添加菜单[ 涉密载体复印情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb458c232301458c3e98c50009', 'a', '涉密载体复印情况', '0', '', '', '1', '', '29', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb458c232301458c5aa7240011', '1', '', '/bmp/workbench-demo/secrecyCarrierCopy/secrecyCarrierCopy_list.jsp', 'ca82caeb458c232301458c3e98c50009');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb458c232301458c3e98c50009', '1');

		-- ******************************* 添加菜单[ 涉密设备维修情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb458c232301458c3f2814000b', 'a', '涉密设备维修情况', '0', '', '', '1', '', '30', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb458c232301458c5c2b730012', '1', '', '/bmp/workbench-demo/secrecyEquipmentMaintain/secrecyEquipmentMaintain_list.jsp', 'ca82caeb458c232301458c3f2814000b');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb458c232301458c3f2814000b', '1');

		-- ******************************* 添加菜单[ 装备保密技术防范设备情况 ] *******************************
		-- ----------------------------- sys_domain表insert语句 ----------------------------
		INSERT INTO sys_domain VALUES ('ca82caeb458c232301458c3fd888000d', 'a', '装备保密技术防范设备情况', '0', '', '', '1', '', '31', 'ca82cae64581e009014581ed08790003');
		-- ----------------------------- sys_resource表insert语句 ----------------------------
		INSERT INTO sys_resource VALUES ('ca82caeb458c232301458c6dbd300013', '1', '', '/bmp/workbench-demo/secrecyTechnologyPrevention/secrecyTechnologyPrevention_list.jsp', 'ca82caeb458c232301458c3fd888000d');
		-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
		INSERT INTO sys_domain_organ VALUES ('ca82caeb458c232301458c3fd888000d', '1');

