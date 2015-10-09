-- ******************************* 添加菜单[ 保密工作机构 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('bmp_secrecy_organization', 'a', '保密工作机构', '-1', '', '', '1', '', '1', '402881a23b9813c6013b98180dbd0002');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881c43ba7a1ab013ba7ab5b9d0006', '1', '', '', 'bmp_secrecy_organization');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('bmp_secrecy_organization', '1');