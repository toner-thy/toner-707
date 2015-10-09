-- 注意：先要执行【保密工作机构】菜单，否则父ID找不到。
-- ******************************* 添加菜单[ 保密委员会 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('bmp_secrecy_organization_sec_committee', 'a', '保密委员会', '0', '', '', '1', '各级保密局自己填写的各个行政区划的保密委', '1', 'bmp_secrecy_organization');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881c43ba7a1ab013ba7ad6596000a', '1', '', '/secrecyorganization/secrecycommittee/secrecyCommittee_detail.action', 'bmp_secrecy_organization_sec_committee');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('bmp_secrecy_organization_sec_committee', '1');