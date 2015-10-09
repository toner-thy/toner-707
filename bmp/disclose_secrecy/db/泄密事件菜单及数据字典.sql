INSERT INTO sys_dictionary_field (`FIELD_ID`, `FIELD_CODE`, `FIELD_NAME`, `IS_EXTRA`, `ORDER_NO`, `TABLE_ID`) VALUES ('discloseSecrecy_case_kind', 'case_kind', '泄密事件案件性质', 0, 3, 'bmp');
INSERT INTO sys_dictionary_field (`FIELD_ID`, `FIELD_CODE`, `FIELD_NAME`, `IS_EXTRA`, `ORDER_NO`, `TABLE_ID`) VALUES ('discloseSecrecy_case_Type', 'case_Type', '泄密事件发案形式', 0, 30, 'bmp');
INSERT INTO sys_dictionary_field (`FIELD_ID`, `FIELD_CODE`, `FIELD_NAME`, `IS_EXTRA`, `ORDER_NO`, `TABLE_ID`) VALUES ('discloseSecrecy_duty_organ_kind', 'duty_organ_kind', '泄密事件责任单位性质', 0, 4, 'bmp');
INSERT INTO sys_dictionary_field (`FIELD_ID`, `FIELD_CODE`, `FIELD_NAME`, `IS_EXTRA`, `ORDER_NO`, `TABLE_ID`) VALUES ('discloseSecrecy_find_result', 'find_result', '泄密事件查处结果', 0, 1, 'bmp');


INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('402881b84028254e014028292e8a0000', '已查结', 1, 1, 1, 'discloseSecrecy_find_result');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('402881b840282e180140282ffb810000', '未查结', 2, 2, 1, 'discloseSecrecy_find_result');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_duty_organ_kind_1', '中央和国家机关', 1, 1, 1, 'discloseSecrecy_duty_organ_kind');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_duty_organ_kind_2', '地方党政机关', 2, 2, 1, 'discloseSecrecy_duty_organ_kind');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_duty_organ_kind_3', '武器装备科研生产事业单位', 3, 3, 1, 'discloseSecrecy_duty_organ_kind');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_duty_organ_kind_4', '非武器装备科研生产企业', 4, 4, 1, 'discloseSecrecy_duty_organ_kind');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_duty_organ_kind_6', '非武器装备科研生产事业单位', 5, 5, 1, 'discloseSecrecy_duty_organ_kind');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_1', '间谍窃密和故意向境外提供国家秘密', 1, 1, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_10', '非法复制、记录、存储国家秘密泄密', 10, 10, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_11', '私人交往和通信泄密', 11, 11, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_12', '出版宣传工作泄密', 12, 12, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_13', '丢失被盗泄密', 13, 13, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_14', '考试泄密', 14, 41, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_15', '密电明传泄密', 15, 15, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_16', '其他形式泄密', 16, 16, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_2', '网络窃密', 2, 2, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_3', '社会网站泄密', 3, 3, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_4', '其他', 4, 4, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_5', '利用电子邮等方式通过互联网及其他公共信息网络传递涉密文件资料泄密', 5, 5, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_6', '非法获取、持有国家秘密载体泄密', 6, 6, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_7', '买卖、转送或私自销毁国家秘密载体泄密', 7, 7, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_8', '通过普通邮政、快递等无保密措施的渠道传递国家秘密载体泄密', 8, 8, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('discloseSecrecy_case_Type_9', '邮寄、托运国家秘密载体出境，或者未经批准携带、传递国家秘密载体出境泄密', 9, 9, 1, 'discloseSecrecy_case_Type');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('402881b8402814980140281d977f0001', '故意泄密', 1, 1, 1, 'discloseSecrecy_case_kind');
INSERT INTO sys_dictionary_option (`OPTION_ID`, `OPTION_TEXT`, `OPTION_VALUE`, `ORDER_NO`, `STATUS`, `FIELD_ID`) VALUES ('402881b8402814980140281e5bc90002', '过失泄密', 2, 2, 1, 'discloseSecrecy_case_kind');


INSERT INTO `sys_domain` (`DOMAIN_ID`, `DOMAIN_CATEGORY`, `DOMAIN_NAME`, `DOMAIN_TYPE`, `ICO`, `ICO_OVER`, `IS_INUSE`, `REMARK`, `SORT`, `PARENT_ID`) VALUES ('402881b8401944b7014019486bef0000', 'A', '泄密事件', 0, '', '', 1, '', 100, '402881cd36a4941c0136a4961cb90002');
INSERT INTO `sys_domain` (`DOMAIN_ID`, `DOMAIN_CATEGORY`, `DOMAIN_NAME`, `DOMAIN_TYPE`, `ICO`, `ICO_OVER`, `IS_INUSE`, `REMARK`, `SORT`, `PARENT_ID`) VALUES ('402881b84019744c014019767df10002', 'a', '泄密事件', 0, '', '', 1, '', 1, '402881b8401944b7014019486bef0000');
INSERT INTO `sys_resource` (`RESOURCE_ID`, `IS_MAIN`, `JS_FUCTION`, `URL`, `DOMAIN_FK`) VALUES ('402881b84019744c014019756fcd0001', 1, NULL, '', '402881b8401944b7014019486bef0000');
INSERT INTO `sys_resource` (`RESOURCE_ID`, `IS_MAIN`, `JS_FUCTION`, `URL`, `DOMAIN_FK`) VALUES ('402881b8402974e101402976b9320003', 1, NULL, '/bmp/discloseSecrecy/discloseSecrecy_list.action', '402881b84019744c014019767df10002');
INSERT INTO `sys_domain` (`DOMAIN_ID`, `DOMAIN_CATEGORY`, `DOMAIN_NAME`, `DOMAIN_TYPE`, `ICO`, `ICO_OVER`, `IS_INUSE`, `REMARK`, `SORT`, `PARENT_ID`) VALUES ('402881b84019744c01401978a9ca0004', 'A', '新增', 1, '', '', 1, '', 1, '402881b84019744c014019767df10002');
INSERT INTO `sys_domain` (`DOMAIN_ID`, `DOMAIN_CATEGORY`, `DOMAIN_NAME`, `DOMAIN_TYPE`, `ICO`, `ICO_OVER`, `IS_INUSE`, `REMARK`, `SORT`, `PARENT_ID`) VALUES ('402881b84028f48101402904e84b0006', 'a', '删除', 1, '', '', 1, '', 3, '402881b84019744c014019767df10002');
INSERT INTO `sys_domain` (`DOMAIN_ID`, `DOMAIN_CATEGORY`, `DOMAIN_NAME`, `DOMAIN_TYPE`, `ICO`, `ICO_OVER`, `IS_INUSE`, `REMARK`, `SORT`, `PARENT_ID`) VALUES ('402881b8402913be014029162fc10000', 'a', '编辑', 1, '', '', 1, '', 2, '402881b84019744c014019767df10002');
INSERT INTO `sys_resource` (`RESOURCE_ID`, `IS_MAIN`, `JS_FUCTION`, `URL`, `DOMAIN_FK`) VALUES ('402881b8402d33e801402d4ac5870002', 1, 'doAdd', '/bmp/discloseSecrecy/discloseSecrecy_add.action', '402881b84019744c01401978a9ca0004');
INSERT INTO `sys_resource` (`RESOURCE_ID`, `IS_MAIN`, `JS_FUCTION`, `URL`, `DOMAIN_FK`) VALUES ('402881b8402d33e801402d4af6b90003', 1, 'doDel', '/bmp/discloseSecrecy/discloseSecrecy_del.action', '402881b84028f48101402904e84b0006');
INSERT INTO `sys_resource` (`RESOURCE_ID`, `IS_MAIN`, `JS_FUCTION`, `URL`, `DOMAIN_FK`) VALUES ('402881b8402974e101402977e6380006', 1, 'doEdit', '/bmp/discloseSecrecy/discloseSecrecy_edit.action', '402881b8402913be014029162fc10000');
insert into sys_domain_organ values('402881b8401944b7014019486bef0000','1');
insert into sys_domain_organ values('402881b84019744c014019767df10002','1');
insert into sys_domain_organ values('402881b84019744c01401978a9ca0004','1');
insert into sys_domain_organ values('402881b84028f48101402904e84b0006','1');
insert into sys_domain_organ values('402881b8402913be014029162fc10000','1');