-- ******************************* 添加菜单[ 个人邮件 ] *******************************
-- ----------------------------- sys_domain表insert语句 ----------------------------
INSERT INTO sys_domain VALUES ('mail-1001d', 'a', '个人邮件', '0', '', '', '1', '', '2', '402881cd36a4941c0136a4961cb90002');
-- ----------------------------- sys_resource表insert语句 ----------------------------
INSERT INTO sys_resource VALUES ('402881a13d397af1013d397c255b0000', '1', '', '', 'mail-1001d');
-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
INSERT INTO sys_domain_organ VALUES ('mail-1001d', '1');

	-- ******************************* 添加菜单[ 收件箱 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('mail-1001001d', 'a', '收件箱', '0', '', '', '1', '', '1', 'mail-1001d');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('mail-1001001r', '1', '', '/mail/person/mail_inboxList.action', 'mail-1001001d');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('mail-1001001d', '1');

	-- ******************************* 添加菜单[ 发件箱 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('mail-1001002d', 'a', '发件箱', '0', '', '', '1', '', '2', 'mail-1001d');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('mail-1001002r', '1', '', '/mail/person/mail_sentList.action', 'mail-1001002d');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('mail-1001002d', '1');

	-- ******************************* 添加菜单[ 草稿箱 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('mail-1001003d', 'a', '草稿箱', '0', '', '', '1', '', '3', 'mail-1001d');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('mail-1001003r', '1', '', '/mail/person/mail_draftList.action', 'mail-1001003d');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('mail-1001003d', '1');

	-- ******************************* 添加菜单[ 回收站 ] *******************************
	-- ----------------------------- sys_domain表insert语句 ----------------------------
	INSERT INTO sys_domain VALUES ('mail-1001004d', 'a', '回收站', '0', '', '', '1', '', '4', 'mail-1001d');
	-- ----------------------------- sys_resource表insert语句 ----------------------------
	INSERT INTO sys_resource VALUES ('mail-1001004r', '1', '', '/mail/person/mail_trashList.action', 'mail-1001004d');
	-- ----------------------------- sys_domain_organ表insert语句 ----------------------------
	INSERT INTO sys_domain_organ VALUES ('mail-1001004d', '1');