-- 注意：先要执行【保密工作机构】菜单，否则父ID找不到。
-- ******************************* 添加菜单[ 单位保密工作机构 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('bmp_secrecy_organization_sec_work_org', 'a', '单位保密工作机构', '2', '', '', '1', '', '1', 'bmp_secrecy_organization');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a43c0da55a013c0da7ea630001', '1', '', '/secrecyorganization/secrecyWorkOrgan/secrecyWorkOrgan_detail.action', 'bmp_secrecy_organization_sec_work_org');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('bmp_secrecy_organization_sec_work_org', '1');