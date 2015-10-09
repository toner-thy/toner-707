/*
MySQL Data Transfer
Source Host: localhost
Source Database: bmp
Target Host: localhost
Target Database: bmp
Date: 2013-4-12 9:02:18
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for bm_technic_equipment_type
-- ----------------------------
DROP TABLE IF EXISTS `bm_technic_equipment_type`;
CREATE TABLE `bm_technic_equipment_type` (
  `TECHNIC_EQUIPMENT_TYPE_ID` varchar(32) NOT NULL,
  `STATE` int(11) DEFAULT NULL COMMENT '0：未发布，1：发布',
  `NAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `DESCRIPTION` longtext COMMENT '说明',
  `organ_id` varchar(39) DEFAULT NULL COMMENT '单位ID',
  `department_id` varchar(39) DEFAULT NULL COMMENT '部门ID',
  `modify_person` varchar(39) DEFAULT NULL COMMENT '修改人员',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `create_person` varchar(39) DEFAULT NULL COMMENT '输入人员',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '输入时间',
  `TRANSMIT_STATE` int(11) DEFAULT NULL,
  PRIMARY KEY (`TECHNIC_EQUIPMENT_TYPE_ID`),
  KEY `FK1B3F55ECE402B565` (`department_id`),
  KEY `FK1B3F55EC1260E254` (`organ_id`),
  KEY `FK1B3F55ECC44B8469` (`modify_person`),
  KEY `FK1B3F55EC7B01EDE7` (`create_person`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `bm_technic_equipment_type` VALUES ('ff8080812ebd754a012edb3202e70030', '1', '涉密会议干扰器', '', 'ff80808129880e9a0129a159facd009a', 'ff80808129880e9a0129a159facd009a', 'SYSTEM_40282a81303ec7bd01303ed006060002', '2011-12-06 10:26:26', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-03-22 09:33:31', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff80808129cab98a0129e86d237800df', '1', '视频干扰器', '', '402881ee264ad3d401264ad612270003', '402881ee264ea2a301264ed287a40003', '402881d4278a21a801278a3bc2ff0032', '2010-07-19 09:59:13', '402881d4278a21a801278a3bc2ff0032', '2010-07-19 09:59:13', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff80808130f8ca620130fea1b7590071', '1', '安全隔离卡', '对内、外网进行安全隔离。', 'ff80808129880e9a0129a159facd009a', 'ff80808129880e9a0129a159facd009a', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:47:49', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:47:49', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff80808129cab98a0129e86e35ca00e6', '1', '涉密移动硬盘', '', '402881ee264ad3d401264ad612270003', '402881ee264ea2a301264ed287a40003', '402881d4278a21a801278a3bc2ff0032', '2010-07-19 10:00:23', '402881d4278a21a801278a3bc2ff0032', '2010-07-19 10:00:23', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff8080813008010101300c7bbf6f0017', '1', '检查工具', '', 'ff80808129880e9a0129a17773e500ba', 'ff80808129880e9a0129a17773e500ba', 'SYSTEM_40282a81303ec7bd01303ed006060002', '2011-12-06 10:27:11', 'SYSTEM_ff80808129880e9a0129a17773e500ba', '2011-05-20 16:18:14', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff8080812d0326ec012d11fd412400a2', '1', '上网痕迹搜索系统', '', '402881e22654cd38012654fe76970019', '402881e22654cd38012654fe76970019', 'SYSTEM_40282a81303ec7bd01303ed006060002', '2011-12-06 10:27:22', 'SYSTEM_402881e22654cd38012654fe76970019', '2010-12-23 14:49:26', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff80808130f8ca620130fe9f368a006e', '1', '普通打印机', '非涉密计算机上连接的打印机。', 'ff80808129880e9a0129a159facd009a', 'ff80808129880e9a0129a159facd009a', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:45:05', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:45:05', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff8080812d0326ec012d1202c04a00b0', '1', '违规外联报警系统', '', '402881e22654cd38012654fe76970019', '402881e22654cd38012654fe76970019', 'SYSTEM_40282a81303ec7bd01303ed006060002', '2011-12-06 10:27:31', 'SYSTEM_402881e22654cd38012654fe76970019', '2010-12-23 14:55:26', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff8080812d0326ec012d1204c06e00b1', '1', '政府门户网站检查系统', '', '402881e22654cd38012654fe76970019', '402881e22654cd38012654fe76970019', 'SYSTEM_40282a81303ec7bd01303ed006060002', '2011-12-06 10:35:14', 'SYSTEM_402881e22654cd38012654fe76970019', '2010-12-23 14:57:37', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff8080812ebd754a012ed7cb286d0061', '1', '电子密码文件柜', '', 'ff80808129880e9a0129a159facd009a', 'ff80808129880e9a0129a159facd009a', 'SYSTEM_40282a81303ec7bd01303ed006060002', '2011-12-06 10:26:45', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-03-21 17:42:18', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff80808130f8ca620130fe998ce60066', '1', '普通文件柜', '\r\n', 'ff80808129880e9a0129a159facd009a', 'ff80808129880e9a0129a159facd009a', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:38:53', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:38:53', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff8080813008010101300c7da8d5001a', '1', '木马查杀工具', '', 'ff80808129880e9a0129a17773e500ba', 'ff80808129880e9a0129a17773e500ba', 'SYSTEM_40282a81303ec7bd01303ed006060002', '2011-12-06 10:27:00', 'SYSTEM_ff80808129880e9a0129a17773e500ba', '2011-05-20 16:20:19', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff80808130f8ca620130fe9816bc0061', '1', '加密U盘', '', 'ff80808129880e9a0129a159facd009a', 'ff80808129880e9a0129a159facd009a', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:37:18', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:37:18', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff80808130f8ca620130fe9858c90063', '1', '普通U盘', '', 'ff80808129880e9a0129a159facd009a', 'ff80808129880e9a0129a159facd009a', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:37:35', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:37:35', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff80808130f8ca620130fe99e3ec0068', '1', '电子密码文件柜', '', 'ff80808129880e9a0129a159facd009a', 'ff80808129880e9a0129a159facd009a', 'SYSTEM_ff80808129880e9a0129a154b16d0092', '2012-05-14 08:39:00', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:39:16', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff80808130f8ca620130fea3adf90073', '1', '涉密计算机', '各镇（街道）、区级各部门经区保密局批准确定的，并颁发了涉密计算机确认书的计算机。', 'ff80808129880e9a0129a159facd009a', 'ff80808129880e9a0129a159facd009a', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:49:57', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:49:57', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff80808130f8ca620130fea42ec00075', '1', '党政网计算机', '连接党政内网的计算机。', 'ff80808129880e9a0129a159facd009a', 'ff80808129880e9a0129a159facd009a', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:50:30', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:50:30', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff80808130f8ca620130fea543170077', '1', '各部门工作网上连接的计算机', '', 'ff80808129880e9a0129a159facd009a', 'ff80808129880e9a0129a159facd009a', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:51:41', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:51:41', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff80808130f8ca620130fea654a20079', '1', '连接互联网计算机', '各镇（街道）、区级各部门连接公众信息网的计算机。', 'ff80808129880e9a0129a159facd009a', 'ff80808129880e9a0129a159facd009a', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:52:51', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:52:51', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff80808130f8ca620130fea6fd3b007b', '1', '涉密信息载体销毁设备', '', 'ff80808129880e9a0129a159facd009a', 'ff80808129880e9a0129a159facd009a', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:53:34', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:53:34', null);
INSERT INTO `bm_technic_equipment_type` VALUES ('ff80808130f8ca620130feab858f007d', '1', '指纹鼠标', '', 'ff80808129880e9a0129a159facd009a', 'ff80808129880e9a0129a159facd009a', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:58:31', 'SYSTEM_ff80808129880e9a0129a159facd009a', '2011-07-06 16:58:31', null);
