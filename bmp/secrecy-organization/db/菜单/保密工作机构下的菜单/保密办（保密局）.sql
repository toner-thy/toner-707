-- 注意：先要执行【保密工作机构】菜单，否则父ID找不到。
-- ******************************* 添加菜单[ 保密办（保密局）密局） ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('bmp_secrecy_organization_sec_office', 'a', '保密办（保密局））', '0', '', '', '1', '', '4', 'bmp_secrecy_organization');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a03bcc67ed013bcc69d5e70000', '1', '', '/secrecyorganization/secrecyoffice/secrecyOffice_detail.action', 'bmp_secrecy_organization_sec_office');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('bmp_secrecy_organization_sec_office', '1');